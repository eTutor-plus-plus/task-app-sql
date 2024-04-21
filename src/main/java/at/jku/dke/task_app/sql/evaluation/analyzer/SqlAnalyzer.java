package at.jku.dke.task_app.sql.evaluation.analyzer;

import at.jku.dke.task_app.sql.evaluation.SqlDataSource;
import at.jku.dke.task_app.sql.evaluation.SqlEvaluationCriterion;
import jakarta.validation.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Executes SQL queries and analyzes the result.
 */
@Service
public class SqlAnalyzer {
    private static final Logger LOG = LoggerFactory.getLogger(SqlAnalyzer.class);
    private static final String INTERNAL_ERROR = "This is an internal system error. ";
    private static final String CONTACT_ADMIN = "Please contact the system administrator. ";

    private final SqlDataSource dataSource;

    /**
     * Creates a new instance of class {@link SqlAnalyzer}.
     *
     * @param dataSource The JDBC data source provider.
     */
    public SqlAnalyzer(SqlDataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Analyzes the submission.
     * <p>
     * This method analyzes in order from the strongest to the weakest criterion.
     * Only the result of the submission is analyzed for correctness, not the submission query.
     *
     * @param submission The submission.
     * @param config     The analyzer configuration.
     * @return The analysis result.
     */
    public SqlAnalysis analyze(String submission, SqlAnalyzerConfig config) {
        var analysis = new SqlAnalysis();

        // Validate submission
        if (submission == null || submission.isBlank()) {
            LOG.warn("Submission is null or empty, this should not happen because submission should be validated in controller");
            analysis.setAnalysisException(new ValidationException("Submission is empty"));
            return analysis;
        }
        if (!this.queryOnlyContainsSelect(submission)) {
            LOG.warn("Possible malicious SQL statement detected: {}", submission);
            analysis.setAnalysisException(new ValidationException("Query does not contain SELECT"));
            return analysis;
        }
        if (submission.endsWith(";"))
            submission = submission.substring(0, submission.length() - 1);

        // Analyze other criteria from strongest to weakest and abort as soon a criterion is not satisfied
        LOG.info("Analyzing submission {}", submission);
        try (Connection conn = this.dataSource.connect(config.getSchema())) {
            // Syntax
            try {
                analysis.setQueryResult(this.executeQuery(conn, submission));
                if (config.containsCriterion(SqlEvaluationCriterion.CORRECT_SYNTAX))
                    analysis.addCriterionAnalysis(SqlEvaluationCriterion.CORRECT_SYNTAX, new SyntaxCriterionAnalysis(null));
            } catch (SQLException ex) {
                if (config.containsCriterion(SqlEvaluationCriterion.CORRECT_SYNTAX))
                    analysis.addCriterionAnalysis(SqlEvaluationCriterion.CORRECT_SYNTAX, new SyntaxCriterionAnalysis(ex));
                return analysis;
            }

            // Cartesian product
            if (config.containsCriterion(SqlEvaluationCriterion.CARTESIAN_PRODUCT)) {
                var criterionAnalysis = this.analyzeCartesianProduct(conn, submission, config.getSolution());
                analysis.addCriterionAnalysis(SqlEvaluationCriterion.CARTESIAN_PRODUCT, criterionAnalysis);
                if (!criterionAnalysis.isCriterionSatisfied())
                    return analysis;
            }

            // Correct column names
            if (config.containsCriterion(SqlEvaluationCriterion.CORRECT_COLUMNS)) {
                var criterionAnalysis = this.analyzeColumnNames(conn, submission, config.getSolution());
                analysis.addCriterionAnalysis(SqlEvaluationCriterion.CORRECT_COLUMNS, criterionAnalysis);
                if (!criterionAnalysis.isCriterionSatisfied() && criterionAnalysis.hasMissingColumns())  // only abort if missing columns, if only superfluous columns, continue with analysis
                    return analysis;
            }

            // Correct tuples
            if (config.containsCriterion(SqlEvaluationCriterion.CORRECT_TUPLES)) {
                var criterionAnalysis = this.analyzeTuples(conn, submission, config.getSolution());
                analysis.addCriterionAnalysis(SqlEvaluationCriterion.CORRECT_TUPLES, criterionAnalysis);
                if (!criterionAnalysis.isCriterionSatisfied())
                    return analysis;
            }

            // Correct order
            if (config.containsCriterion(SqlEvaluationCriterion.CORRECT_ORDER)) {
                var criterionAnalysis = this.analyzeOrder(conn, submission, config.getSolution());
                analysis.addCriterionAnalysis(SqlEvaluationCriterion.CORRECT_ORDER, criterionAnalysis);
                if (!criterionAnalysis.isCriterionSatisfied())
                    return analysis;
            }
        } catch (SQLException ex) {
            LOG.error("Failed to connect to database", ex);
            analysis.setAnalysisException(ex);
            return analysis;
        }

        return analysis;
    }

    /**
     * Executes the specified query to analyze the syntax and retrieve the query columns.
     *
     * @param conn  The database connection.
     * @param query The query to execute.
     * @return The query result.
     * @throws IllegalArgumentException If the execution of the query failed. The inner exception contains the SQL error.
     * @throws SQLException             If the connection to the database could not be established.
     */
    private QueryResult executeQuery(Connection conn, String query) throws SQLException, IllegalArgumentException {
        LOG.info("Executing query '{}'", query);
        try (Statement stmt = conn.createStatement()) {
            var result = new QueryResult();
            ResultSet rs = stmt.executeQuery(query);

            // load column names
            ResultSetMetaData rsmd = rs.getMetaData();
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                result.addColumn(rsmd.getColumnLabel(i));
            }

            // load data
            while (rs.next()) {
                var tuple = new ArrayList<String>(rsmd.getColumnCount());
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    tuple.add(rs.getString(i));
                }
                result.addTuple(tuple);
            }

            return result;
        } catch (SQLException ex) {
            LOG.warn("Query could not be executed", ex);
            throw ex;
        }
    }

    /**
     * Analyzes if the submitted query produces a cartesian product.
     *
     * @param conn       The database connection.
     * @param submission The submitted query.
     * @param solution   The solution query.
     * @return The criterion analysis result.
     * @see SqlEvaluationCriterion#CARTESIAN_PRODUCT
     */
    private CartesianProductCriterionAnalysis analyzeCartesianProduct(Connection conn, String submission, String solution) {
        LOG.debug("Checking for cartesian product");
        var analysis = new CartesianProductCriterionAnalysis();

        // Execute solution
        int solutionCount = -1;
        var querySolution = "SELECT COUNT(*) FROM (" + solution + ") AS solutionQuery";
        LOG.debug("Executing solution query {} for cartesian product checking", querySolution);
        try (var stmt = conn.createStatement()) {
            var rs = stmt.executeQuery(querySolution);
            if (rs.next())
                solutionCount = rs.getInt(1);
        } catch (SQLException ex) {
            LOG.error("Failed to execute solution query for cartesian product checking", ex);
            analysis.setException(new AnalysisException("An error occurred while analyzing cartesian product. " + INTERNAL_ERROR + CONTACT_ADMIN, ex));
            return analysis;
        }

        // Execute solution
        int submissionCount = -1;
        var querySubmission = "SELECT COUNT(*) FROM (" + submission + ") AS submissionQuery";
        LOG.debug("Executing submission query {} for cartesian product checking", querySubmission);
        try (var stmt = conn.createStatement()) {
            var rs = stmt.executeQuery(querySubmission);
            if (rs.next())
                submissionCount = rs.getInt(1);
        } catch (SQLException ex) {
            LOG.error("Failed to execute submission query for cartesian product checking", ex);
            analysis.setException(new AnalysisException("An error occurred while analyzing cartesian product. " + INTERNAL_ERROR + CONTACT_ADMIN, ex));
            return analysis;
        }

        analysis.setCartesianProductSuspected(submissionCount > (4 * solutionCount + 50)); // WHY?????????

        return analysis;
    }

    /**
     * Analyzes the column names.
     * <p>
     * Compares the column names of solution and submission and finds missing and superfluous columns.
     *
     * @param conn       The database connection.
     * @param submission The submitted query.
     * @param solution   The solution query.
     * @return The criterion analysis result.
     * @see SqlEvaluationCriterion#CORRECT_COLUMNS
     */
    private ColumnCriterionAnalysis analyzeColumnNames(Connection conn, String submission, String solution) {
        LOG.debug("Analysing column names");
        var analysis = new ColumnCriterionAnalysis();

        List<String> submissionColumns;
        List<String> solutionColumns;

        // Execute queries
        try (var stmt = conn.createStatement()) {
            LOG.debug("Executing solution query {} for analysing column name", solution);
            var rsSolution = stmt.executeQuery(solution);
            solutionColumns = new ArrayList<>(rsSolution.getMetaData().getColumnCount());
            for (int i = 1; i <= rsSolution.getMetaData().getColumnCount(); i++) {
                solutionColumns.add(rsSolution.getMetaData().getColumnName(i).toUpperCase());
            }

            LOG.debug("Executing submission query {} for analysing column name", submission);
            var rsSubmission = stmt.executeQuery(submission);
            submissionColumns = new ArrayList<>(rsSubmission.getMetaData().getColumnCount());
            for (int i = 1; i <= rsSubmission.getMetaData().getColumnCount(); i++) {
                submissionColumns.add(rsSubmission.getMetaData().getColumnName(i).toUpperCase());
            }
        } catch (SQLException ex) {
            LOG.error("Failed to retrieve column names for analysing column name", ex);
            analysis.setException(new AnalysisException("An error occurred while analysing column names. " + INTERNAL_ERROR + CONTACT_ADMIN, ex));
            return analysis;
        }

        // Compare
        solutionColumns.stream()
            .filter(c -> !submissionColumns.contains(c))
            .forEach(analysis::addMissingColumn);

        submissionColumns.stream()
            .filter(c -> !solutionColumns.contains(c))
            .forEach(analysis::addSuperfluousColumn);

        return analysis;
    }

    /**
     * Analyzes the tuples. This method must only be called if the submission has no missing columns.
     * <p>
     * Brings the solution and submission columns in the same order and then finds missing and superfluous tuples.
     *
     * @param conn       The database connection.
     * @param submission The submitted query.
     * @param solution   The solution query.
     * @return The criterion analysis result.
     * @see SqlEvaluationCriterion#CORRECT_TUPLES
     */
    private TupleCriterionAnalysis analyzeTuples(Connection conn, String submission, String solution) {
        LOG.debug("Analysing tuples");
        var analysis = new TupleCriterionAnalysis();
        List<List<String>> solutionTuples = new ArrayList<>();
        List<List<String>> submissionTuples = new ArrayList<>();

        try (var stmt = conn.createStatement()) {
            // Get solution column names
            LOG.debug("Executing solution query for getting column names for analysing tuples");
            var rsColSolution = stmt.executeQuery(solution);
            for (int i = 1; i <= rsColSolution.getMetaData().getColumnCount(); i++) {
                analysis.addColumnLabel(rsColSolution.getMetaData().getColumnName(i));
            }

            String columns = String.join(", ", analysis.getColumnLabels());
            LOG.debug("Querying columns {}", columns);

            // Execute solution
            String query = "SELECT " + columns + System.lineSeparator() + "FROM (" + solution + ") AS solutionQuery" + System.lineSeparator() + "ORDER BY " + columns;
            LOG.debug("Executing {} for analysing tuples", query);
            var rsSolution = stmt.executeQuery(query);
            addTuplesToList(rsSolution, solutionTuples);

            // Execute submission
            query = "SELECT " + columns + System.lineSeparator() + "FROM (" + submission + ") AS submissionQuery" + System.lineSeparator() + "ORDER BY " + columns;
            LOG.debug("Executing {} for analysing tuples", query);
            var rsSubmission = stmt.executeQuery(query);
            addTuplesToList(rsSubmission, submissionTuples);

            // Compare
            solutionTuples.stream()
                .filter(t -> !submissionTuples.contains(t))
                .forEach(analysis::addMissingTuple);
            submissionTuples.stream()
                .filter(t -> !solutionTuples.contains(t))
                .forEach(analysis::addSuperfluousTuple);
        } catch (SQLException ex) {
            LOG.error("Failed to query tuples for analysing tuples", ex);
            analysis.setException(new AnalysisException("An error occurred while analysing tuples. " + INTERNAL_ERROR + CONTACT_ADMIN, ex));
            return analysis;
        }

        return analysis;
    }

    /**
     * Analyzes the tuple ordering. This method must only be called if {@link #analyzeTuples(Connection, String, String)} succeeded.
     * <p>
     * First checks whether the solution contains an ORDER BY clause, then iterates over the tuples and compares the columns of the solution's result set
     * with the columns of the submission's result set.
     *
     * @param conn       The database connection.
     * @param submission The submitted query.
     * @param solution   The solution query.
     * @return The criterion analysis result.
     * @see SqlEvaluationCriterion#CORRECT_ORDER
     */
    private OrderCriterionAnalysis analyzeOrder(Connection conn, String submission, String solution) {
        LOG.debug("Analysing tuple order");
        var analysis = new OrderCriterionAnalysis();
        if (!this.queryContainsOrderByClause(solution))
            return analysis;

        // analyse
        try (var stmtSolution = conn.createStatement();
             var stmtSubmission = conn.createStatement()) {
            // Execute solution
            LOG.debug("Executing solution query {} for analysing ordering", solution);
            var rsSolution = stmtSolution.executeQuery(solution);

            // Execute submission
            LOG.debug("Executing submission query {} for analysing ordering", submission);
            var rsSubmission = stmtSubmission.executeQuery(submission);

            // Check order
            var solutionMetadata = rsSolution.getMetaData();
            var colCnt = solutionMetadata.getColumnCount();
            while (rsSolution.next()) {
                rsSubmission.next();
                for (int i = 1; i <= colCnt; i++) {
                    String value = rsSolution.getString(i);
                    if (value == null) {
                        if (rsSubmission.getString(solutionMetadata.getColumnName(i)) != null) {
                            analysis.setOrderingIncorrect(true);
                            break;
                        }
                    } else if (!value.equals(rsSubmission.getString(solutionMetadata.getColumnName(i)))) {
                        analysis.setOrderingIncorrect(true);
                        break;
                    }
                }

                if (analysis.isOrderingIncorrect())
                    break;
            }
        } catch (SQLException ex) {
            LOG.error("Failed to query tuples for analysing order", ex);
            analysis.setException(new AnalysisException("An error occurred while analysing order. " + INTERNAL_ERROR + CONTACT_ADMIN, ex));
            return analysis;
        }

        return analysis;
    }

    //#region --- HELPER ---

    /**
     * Reads all tuples from the result set and adds them to the list.
     *
     * @param rs     The result set.
     * @param tuples The tuple list to which add the tuples.
     * @throws SQLException If the result set could not be iterated.
     */
    private void addTuplesToList(ResultSet rs, List<List<String>> tuples) throws SQLException {
        int colCount = rs.getMetaData().getColumnCount();
        while (rs.next()) {
            var tuple = new ArrayList<String>(colCount);
            for (int i = 1; i <= colCount; i++) {
                String value = rs.getString(i);
                tuple.add(value == null ? "NULL" : value.toUpperCase());
            }
            tuples.add(tuple);
        }
    }

    /**
     * Returns whether the given query contains an ORDER BY clause.
     * <p>
     * Checks whether ORDER BY really is the last clause of the statement.
     *
     * @param query The query.
     * @return {@code true} if the query contains ORDER BY; {@code false} otherwise.
     */
    private boolean queryContainsOrderByClause(String query) {
        String normalizedQuery = query.toLowerCase().replace(" ", "");
        int orderByIndex = normalizedQuery.lastIndexOf("orderby");
        if (orderByIndex < 0)
            return false;

        int afterOrderByIndex = normalizedQuery.lastIndexOf("select", orderByIndex);
        if (afterOrderByIndex < 0)
            afterOrderByIndex = normalizedQuery.lastIndexOf("from", orderByIndex);
        if (afterOrderByIndex < 0)
            afterOrderByIndex = normalizedQuery.lastIndexOf("where", orderByIndex);
        if (afterOrderByIndex < 0)
            afterOrderByIndex = normalizedQuery.lastIndexOf("groupby", orderByIndex);
        if (afterOrderByIndex < 0)
            afterOrderByIndex = normalizedQuery.lastIndexOf("having", orderByIndex);

        return orderByIndex > afterOrderByIndex;
    }

    /**
     * Returns whether the given query only contains SELECT statements and no ALTER, ... statements.
     *
     * @param query The query to check.
     * @return {@code true} if the query only contains SELECT; {@code false} otherwise.
     */
    private boolean queryOnlyContainsSelect(String query) {
        String normalizedQuery = query.toLowerCase().replace("\n", " ").replace("\r", " ").replace("\t", " ");
        if (normalizedQuery.contains("alter "))
            return false;
        if (normalizedQuery.contains("create "))
            return false;
        if (normalizedQuery.contains("drop "))
            return false;
        if (normalizedQuery.contains("set "))
            return false;
        if (normalizedQuery.contains("insert into "))
            return false;
        if (normalizedQuery.contains("update "))
            return false;
        //noinspection RedundantIfStatement
        if (normalizedQuery.contains("delete from "))
            return false;
        return true;
    }

    //#endregion
}
