package at.jku.dke.task_app.sql.evaluation.analyzer;

import at.jku.dke.etutor.task_app.auth.AuthConstants;
import at.jku.dke.etutor.task_app.dto.ModifyTaskGroupDto;
import at.jku.dke.etutor.task_app.dto.TaskStatus;
import at.jku.dke.task_app.sql.DatabaseSetupExtension;
import at.jku.dke.task_app.sql.dto.ModifySqlTaskGroupDto;
import at.jku.dke.task_app.sql.evaluation.SqlEvaluationCriterion;
import at.jku.dke.task_app.sql.services.SqlRaTaskGroupService;
import at.jku.dke.task_app.sql.services.SqlSchemaService;
import jakarta.validation.ValidationException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(DatabaseSetupExtension.class)
@WithMockUser(authorities = AuthConstants.CRUD)
class SqlAnalyzerTest {
    @Autowired
    private SqlAnalyzer analyzer;

    @Autowired
    private SqlRaTaskGroupService taskGroupService;

    private final long id = 1;
    private String schema;

    @BeforeEach
    void setUp() {
        this.taskGroupService.create(1, new ModifyTaskGroupDto<>("sql", TaskStatus.APPROVED, new ModifySqlTaskGroupDto("""
            CREATE TABLE departments (
                id INT PRIMARY KEY,
                name VARCHAR(255)
            );

            CREATE TABLE employees (
                id INT PRIMARY KEY,
                name VARCHAR(255),
                salary DECIMAL(10,2),
                department_id INT,
                FOREIGN KEY (department_id) REFERENCES departments(id)
            );""", """
            INSERT INTO departments (id, name) VALUES (1, 'IT');
            INSERT INTO departments (id, name) VALUES (2, 'HR');
            INSERT INTO departments (id, name) VALUES (3, 'Sales');

            INSERT INTO employees (id, name, salary, department_id) VALUES (1, 'Max Mustermann', 1000.00, 1);
            INSERT INTO employees (id, name, salary, department_id) VALUES (2, 'Lisa Mustermann', 2000.00, 1);
            INSERT INTO employees (id, name, salary, department_id) VALUES (3, 'Jane Doe', 3000.00, 2);
            INSERT INTO employees (id, name, salary, department_id) VALUES (4, 'John Doe', 4000.00, 3);
            INSERT INTO employees (id, name, salary, department_id) VALUES (5, 'Max Musterfrau', 5000.00, 3);
            INSERT INTO employees (id, name, salary, department_id) VALUES (6, 'Lisa Musterfrau', 6000.00, 3);
            """, "INSERT INTO departments (id, name) VALUES (1, 'IT');")
        ));
        this.schema = this.taskGroupService.buildSchemaName(id) + SqlSchemaService.SUFFIX_DIAGNOSE;
    }

    @AfterEach
    void cleanUp() {
        this.taskGroupService.delete(this.id);
    }

    @Test
    void analyzeNullSubmission() {
        // Arrange
        final String submission = null;
        var config = new SqlAnalyzerConfig("SELECT * FROM departments;", this.schema);
        config.addCriterion(SqlEvaluationCriterion.CORRECT_SYNTAX);

        // Act
        var result = this.analyzer.analyze(submission, config);

        // Assert
        assertEquals(0, result.getCriterionAnalysis().size());
        assertNotNull(result.getAnalysisException());
        assertInstanceOf(ValidationException.class, result.getAnalysisException());
    }

    @Test
    void analyzeEmptySubmission() {
        // Arrange
        final String submission = "  \t ";
        var config = new SqlAnalyzerConfig("SELECT * FROM departments;", this.schema);
        config.addCriterion(SqlEvaluationCriterion.CORRECT_SYNTAX);

        // Act
        var result = this.analyzer.analyze(submission, config);

        // Assert
        assertEquals(0, result.getCriterionAnalysis().size());
        assertNotNull(result.getAnalysisException());
        assertInstanceOf(ValidationException.class, result.getAnalysisException());
    }

    @Test
    void analyzeMaliciousSubmission() {
        // Arrange
        final String submission = "ALTER USER etutor IDENTIFIED BY 'newPwd';";
        var config = new SqlAnalyzerConfig("SELECT * FROM departments;", this.schema);
        config.addCriterion(SqlEvaluationCriterion.CORRECT_SYNTAX);

        // Act
        var result = this.analyzer.analyze(submission, config);

        // Assert
        assertEquals(0, result.getCriterionAnalysis().size());
        assertNotNull(result.getAnalysisException());
        assertInstanceOf(ValidationException.class, result.getAnalysisException());
    }

    //#region --- Syntax ---
    @Test
    void analyzeInvalidSubmissionSyntax() {
        // Arrange
        final String submission = "SELECT * FROM not_existing_table;";
        var config = new SqlAnalyzerConfig("SELECT * FROM departments;", this.schema);
        config.addCriterion(SqlEvaluationCriterion.CORRECT_SYNTAX);

        // Act
        var result = this.analyzer.analyze(submission, config);

        // Assert
        assertEquals(1, result.getCriterionAnalysis().size());
        assertNull(result.getAnalysisException());

        var analysis = (SyntaxCriterionAnalysis) result.getCriterionAnalysis().get(SqlEvaluationCriterion.CORRECT_SYNTAX);
        assertFalse(analysis.isCriterionSatisfied());
        assertNotNull(analysis.getException());
    }

    @Test
    void analyzeInvalidSolutionSyntax() {
        // Arrange
        final String submission = "SELECT * FROM departments;";
        var config = new SqlAnalyzerConfig("SELECT * FROM not_existing_table;", this.schema);
        config.addCriterion(SqlEvaluationCriterion.CORRECT_SYNTAX);
        config.addCriterion(SqlEvaluationCriterion.CARTESIAN_PRODUCT);

        // Act
        var result = this.analyzer.analyze(submission, config);

        // Assert
        assertEquals(2, result.getCriterionAnalysis().size());

        var analysis = (AbstractSqlCriterionAnalysis) result.getCriterionAnalysis().get(SqlEvaluationCriterion.CARTESIAN_PRODUCT);
        assertFalse(analysis.isCriterionSatisfied());
        assertNotNull(analysis.getException());
    }
    //#endregion

    //#region --- Column Names ---
    @Test
    void analyzeSuperfluousColumnNames() {
        // Arrange
        final String submission = "SELECT name, salary FROM employees;";
        var config = new SqlAnalyzerConfig("SELECT name FROM employees;", this.schema);
        config.addCriterion(SqlEvaluationCriterion.CORRECT_SYNTAX);
        config.addCriterion(SqlEvaluationCriterion.CORRECT_COLUMNS);

        // Act
        var result = this.analyzer.analyze(submission, config);

        // Assert
        assertEquals(2, result.getCriterionAnalysis().size());
        assertNull(result.getAnalysisException());

        var analysis = (ColumnCriterionAnalysis) result.getCriterionAnalysis().get(SqlEvaluationCriterion.CORRECT_COLUMNS);
        assertFalse(analysis.isCriterionSatisfied());
        assertEquals(1, analysis.getSuperfluousColumns().size());
        assertEquals("SALARY", analysis.getSuperfluousColumns().getFirst());
    }

    @Test
    void analyzeMissingColumnNames() {
        // Arrange
        final String submission = "SELECT name FROM employees;";
        var config = new SqlAnalyzerConfig("SELECT name, salary FROM employees;", this.schema);
        config.addCriterion(SqlEvaluationCriterion.CORRECT_SYNTAX);
        config.addCriterion(SqlEvaluationCriterion.CORRECT_COLUMNS);

        // Act
        var result = this.analyzer.analyze(submission, config);

        // Assert
        assertEquals(2, result.getCriterionAnalysis().size());
        assertNull(result.getAnalysisException());

        var analysis = (ColumnCriterionAnalysis) result.getCriterionAnalysis().get(SqlEvaluationCriterion.CORRECT_COLUMNS);
        assertFalse(analysis.isCriterionSatisfied());
        assertEquals(1, analysis.getMissingColumns().size());
        assertEquals("SALARY", analysis.getMissingColumns().getFirst());
    }

    @Test
    void analyzeValidColumnNames() {
        // Arrange
        final String submission = "SELECT name, salary FROM employees;";
        var config = new SqlAnalyzerConfig("SELECT name, salary FROM employees;", this.schema);
        config.addCriterion(SqlEvaluationCriterion.CORRECT_SYNTAX);
        config.addCriterion(SqlEvaluationCriterion.CORRECT_COLUMNS);

        // Act
        var result = this.analyzer.analyze(submission, config);

        // Assert
        assertEquals(2, result.getCriterionAnalysis().size());
        assertNull(result.getAnalysisException());

        var analysis = (ColumnCriterionAnalysis) result.getCriterionAnalysis().get(SqlEvaluationCriterion.CORRECT_COLUMNS);
        assertTrue(analysis.isCriterionSatisfied());
        assertEquals(0, analysis.getMissingColumns().size());
        assertEquals(0, analysis.getSuperfluousColumns().size());
    }
    //#endregion

    //#region --- Tuples ---
    @Test
    void analyzeSuperfluousTuples() {
        // Arrange
        final String submission = "SELECT name, salary, id FROM employees";
        var config = new SqlAnalyzerConfig("SELECT name, salary FROM employees WHERE salary < 4000", this.schema);
        config.addCriterion(SqlEvaluationCriterion.CORRECT_SYNTAX);
        config.addCriterion(SqlEvaluationCriterion.CORRECT_COLUMNS);
        config.addCriterion(SqlEvaluationCriterion.CORRECT_TUPLES);

        // Act
        var result = this.analyzer.analyze(submission, config);

        // Assert
        assertEquals(3, result.getCriterionAnalysis().size());
        assertNull(result.getAnalysisException());

        var analysis = (TupleCriterionAnalysis) result.getCriterionAnalysis().get(SqlEvaluationCriterion.CORRECT_TUPLES);
        assertFalse(analysis.isCriterionSatisfied());
        assertEquals(3, analysis.getSuperfluousTuples().size());
    }

    @Test
    void analyzeMissingTuples() {
        // Arrange
        final String submission = "SELECT salary, name FROM employees WHERE salary < 4000;";
        var config = new SqlAnalyzerConfig("SELECT name, salary FROM employees;", this.schema);
        config.addCriterion(SqlEvaluationCriterion.CORRECT_SYNTAX);
        config.addCriterion(SqlEvaluationCriterion.CORRECT_COLUMNS);
        config.addCriterion(SqlEvaluationCriterion.CORRECT_TUPLES);

        // Act
        var result = this.analyzer.analyze(submission, config);

        // Assert
        assertEquals(3, result.getCriterionAnalysis().size());
        assertNull(result.getAnalysisException());

        var analysis = (TupleCriterionAnalysis) result.getCriterionAnalysis().get(SqlEvaluationCriterion.CORRECT_TUPLES);
        assertFalse(analysis.isCriterionSatisfied());
        assertEquals(3, analysis.getMissingTuples().size());
    }

    @Test
    void analyzeValidTuples() {
        // Arrange
        final String submission = "SELECT salary, name FROM employees WHERE salary <= 4500;";
        var config = new SqlAnalyzerConfig("SELECT name, salary FROM employees WHERE salary < 4500;", this.schema);
        config.addCriterion(SqlEvaluationCriterion.CORRECT_SYNTAX);
        config.addCriterion(SqlEvaluationCriterion.CORRECT_COLUMNS);
        config.addCriterion(SqlEvaluationCriterion.CORRECT_TUPLES);

        // Act
        var result = this.analyzer.analyze(submission, config);

        // Assert
        assertEquals(3, result.getCriterionAnalysis().size());
        assertNull(result.getAnalysisException());

        var analysis = (TupleCriterionAnalysis) result.getCriterionAnalysis().get(SqlEvaluationCriterion.CORRECT_TUPLES);
        assertTrue(analysis.isCriterionSatisfied());
        assertEquals(0, analysis.getMissingTuples().size());
        assertEquals(0, analysis.getSuperfluousTuples().size());
    }
    //#endregion

    //#region --- Order ---
    @Test
    void analyzeOrderIrrelevant() {
        // Arrange
        final String submission = "SELECT name, salary FROM employees ORDER BY salary DESC;";
        var config = new SqlAnalyzerConfig("SELECT name, salary FROM employees;", this.schema);
        config.addCriterion(SqlEvaluationCriterion.CORRECT_SYNTAX);
        config.addCriterion(SqlEvaluationCriterion.CORRECT_COLUMNS);
        config.addCriterion(SqlEvaluationCriterion.CORRECT_TUPLES);
        config.addCriterion(SqlEvaluationCriterion.CORRECT_ORDER);

        // Act
        var result = this.analyzer.analyze(submission, config);

        // Assert
        assertEquals(4, result.getCriterionAnalysis().size());
        assertNull(result.getAnalysisException());

        var analysis = (OrderCriterionAnalysis) result.getCriterionAnalysis().get(SqlEvaluationCriterion.CORRECT_ORDER);
        assertTrue(analysis.isCriterionSatisfied());
    }

    @Test
    void analyzeOrderInvalid() {
        // Arrange
        final String submission = "SELECT name, salary, id FROM employees ORDER BY salary;";
        var config = new SqlAnalyzerConfig("SELECT salary, name FROM employees ORDER BY salary DESC;", this.schema);
        config.addCriterion(SqlEvaluationCriterion.CORRECT_SYNTAX);
        config.addCriterion(SqlEvaluationCriterion.CORRECT_COLUMNS);
        config.addCriterion(SqlEvaluationCriterion.CORRECT_TUPLES);
        config.addCriterion(SqlEvaluationCriterion.CORRECT_ORDER);

        // Act
        var result = this.analyzer.analyze(submission, config);

        // Assert
        assertEquals(4, result.getCriterionAnalysis().size());
        assertNull(result.getAnalysisException());

        var analysis = (OrderCriterionAnalysis) result.getCriterionAnalysis().get(SqlEvaluationCriterion.CORRECT_ORDER);
        assertFalse(analysis.isCriterionSatisfied());
    }

    @Test
    void analyzeOrderValid() {
        // Arrange
        final String submission = "SELECT name, salary FROM employees ORDER BY salary DESC;";
        var config = new SqlAnalyzerConfig("SELECT salary, name FROM employees ORDER BY salary DESC;", this.schema);
        config.addCriterion(SqlEvaluationCriterion.CORRECT_SYNTAX);
        config.addCriterion(SqlEvaluationCriterion.CORRECT_COLUMNS);
        config.addCriterion(SqlEvaluationCriterion.CORRECT_TUPLES);
        config.addCriterion(SqlEvaluationCriterion.CORRECT_ORDER);

        // Act
        var result = this.analyzer.analyze(submission, config);

        // Assert
        assertEquals(4, result.getCriterionAnalysis().size());
        assertNull(result.getAnalysisException());

        var analysis = (OrderCriterionAnalysis) result.getCriterionAnalysis().get(SqlEvaluationCriterion.CORRECT_ORDER);
        assertTrue(analysis.isCriterionSatisfied());
    }
    //#endregion
}
