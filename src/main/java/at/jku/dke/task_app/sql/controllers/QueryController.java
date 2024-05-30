package at.jku.dke.task_app.sql.controllers;

import at.jku.dke.task_app.sql.data.repositories.SqlRaTaskGroupQueryRepository;
import at.jku.dke.task_app.sql.evaluation.SqlDataSource;
import at.jku.dke.task_app.sql.services.SqlSchemaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

/**
 * Controller for providing query results.
 */
@RestController
@RequestMapping({"/query"})
@Tag(name = "Query", description = "Load query results")
public class QueryController {

    private static final Logger LOG = LoggerFactory.getLogger(QueryController.class);
    private final SqlDataSource dataSource;
    private final SqlRaTaskGroupQueryRepository queryRepository;

    /**
     * Creates a new instance of class {@link QueryController}.
     *
     * @param dataSource      The data source.
     * @param queryRepository The query repository.
     */
    public QueryController(SqlDataSource dataSource, SqlRaTaskGroupQueryRepository queryRepository) {
        this.dataSource = dataSource;
        this.queryRepository = queryRepository;
    }

    /**
     * Executes the requested query and returns the result.
     *
     * @param id The query identifier.
     * @return The query result as HTML table.
     */
    @GetMapping(value = "{id}", produces = MediaType.TEXT_HTML_VALUE)
    @Cacheable("queryResult")
    public ResponseEntity<String> getQueryResult(@PathVariable UUID id) {
        // Prepare
        LOG.info("Requesting result for: {}", id);
        var query = this.queryRepository.findByIdWithTaskGroup(id).orElse(null);
        if (query == null) {
            return ResponseEntity.notFound().build();
        }

        var schema = query.getTaskGroup().getSchemaName() + SqlSchemaService.SUFFIX_DIAGNOSE;

        // Begin with HTML
        StringBuilder builder = new StringBuilder("<!DOCTYPE html>").append(System.lineSeparator())
            .append("<html>")
            .append("<head>")
            .append("<meta charset=\"UTF-8\">")
            .append("<title>Table: ").append(query.getTableName()).append("</title><style>")
            .append("html{line-height:1.15;-webkit-text-size-adjust:100%;font-family:sans-serif}body{margin:1rem}h1{font-size:2em;margin:.67em 0}.pure-table{border-collapse:collapse;border-spacing:0;empty-cells:show;border:1px solid #cbcbcb}.pure-table td,.pure-table th{border-left:1px solid #cbcbcb;border-width:0 0 0 1px;font-size:inherit;margin:0;overflow:visible;padding:.5em 1em}.pure-table thead{background-color:#e0e0e0;color:#000;text-align:left;vertical-align:bottom}.pure-table td{background-color:transparent}.pure-table-bordered td{border-bottom:1px solid #cbcbcb}.pure-table-bordered tbody>tr:last-child>td{border-bottom-width:0}")
            .append("</style></head>")
            .append("<body>")
            .append("<h1>").append(query.getTableName()).append("</h1>")
            .append("<table class=\"pure-table pure-table-bordered\">");

        // Execute
        try (Connection conn = this.dataSource.connect(schema)) {
            try (Statement stmt = conn.createStatement()) {
                var rs = stmt.executeQuery(query.getQuery());
                ResultSetMetaData rsmd = rs.getMetaData();

                // Table head
                builder.append("<thead>").append("<tr>");
                for (int i = 1; i < rsmd.getColumnCount(); i++) {
                    builder.append("<th>").append(rsmd.getColumnName(i)).append("</th>");
                }
                builder.append("</tr>").append("</thead>");

                // Table body
                builder.append("<tbody>");
                while (rs.next()) {
                    builder.append("<tr>");
                    for (int i = 1; i < rsmd.getColumnCount(); i++) {
                        builder.append("<td>").append(rs.getString(i)).append("</td>");
                    }
                    builder.append("</tr>");
                }
                builder.append("</tbody>");
            }
        } catch (SQLException ex) {
            LOG.error("Query could not be executed", ex);
            builder.append("<tr>").append("<td style=\"color: red\">").append("The query could not be executed. Please contact system administrator.").append(" Code: ").append(ex.getSQLState()).append("</td>").append("</tr>");
        }

        // Finish HTML
        builder
            .append("</table>").append(System.lineSeparator())
            .append("</body>").append(System.lineSeparator())
            .append("</html>").append(System.lineSeparator());

        // Return
        return ResponseEntity.ok()
            .contentType(MediaType.TEXT_HTML)
            .body(builder.toString());
    }
}
