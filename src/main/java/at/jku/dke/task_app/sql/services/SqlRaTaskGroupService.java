package at.jku.dke.task_app.sql.services;

import at.jku.dke.etutor.task_app.dto.ModifyTaskGroupDto;
import at.jku.dke.etutor.task_app.dto.TaskGroupModificationResponseDto;
import at.jku.dke.etutor.task_app.services.BaseTaskGroupService;
import at.jku.dke.task_app.sql.config.JdbcConnectionParameters;
import at.jku.dke.task_app.sql.data.entities.SqlRaTaskGroup;
import at.jku.dke.task_app.sql.data.entities.SqlRaTaskGroupQuery;
import at.jku.dke.task_app.sql.data.repositories.SqlRaTaskGroupQueryRepository;
import at.jku.dke.task_app.sql.data.repositories.SqlRaTaskGroupRepository;
import at.jku.dke.task_app.sql.dto.ModifySqlTaskGroupDto;
import at.jku.dke.task_app.sql.dto.SchemaInfoDto;
import at.jku.dke.task_app.sql.dto.TableDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * This class provides methods for managing {@link SqlRaTaskGroup}s.
 */
@Service
public class SqlRaTaskGroupService extends BaseTaskGroupService<SqlRaTaskGroup, ModifySqlTaskGroupDto> {

    private final MessageSource messageSource;
    private final ObjectMapper objectMapper;
    private final JdbcConnectionParameters jdbcConnectionParameters;
    private final SqlRaTaskGroupQueryRepository queryRepository;

    /**
     * Creates a new instance of class {@link SqlRaTaskGroupService}.
     *
     * @param repository               The task group repository.
     * @param messageSource            The message source.
     * @param jdbcConnectionParameters The JDBC connection details.
     * @param objectMapper             The JSON object mapper.
     * @param queryRepository          The task group query repository.
     */
    public SqlRaTaskGroupService(SqlRaTaskGroupRepository repository, SqlRaTaskGroupQueryRepository queryRepository, MessageSource messageSource, ObjectMapper objectMapper, JdbcConnectionParameters jdbcConnectionParameters) {
        super(repository);
        this.messageSource = messageSource;
        this.objectMapper = objectMapper;
        this.jdbcConnectionParameters = jdbcConnectionParameters;
        this.queryRepository = queryRepository;
    }

    //#region --- CREATE ---
    @Override
    protected SqlRaTaskGroup createTaskGroup(long id, ModifyTaskGroupDto<ModifySqlTaskGroupDto> modifyTaskGroupDto) {
        if (!modifyTaskGroupDto.taskGroupType().equals("sql"))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid task group type.");
        this.validateStatements(modifyTaskGroupDto);

        var taskGroup = new SqlRaTaskGroup();
        taskGroup.setId(id);
        taskGroup.setDdlStatements(modifyTaskGroupDto.additionalData().ddlStatements());
        taskGroup.setDiagnoseDmlStatements(modifyTaskGroupDto.additionalData().diagnoseDmlStatements());
        taskGroup.setSubmitDmlStatements(modifyTaskGroupDto.additionalData().submitDmlStatements());
        taskGroup.setSchemaName(this.buildSchemaName(id));
        return taskGroup;
    }

    @Override
    protected void afterCreate(SqlRaTaskGroup taskGroup, ModifyTaskGroupDto<ModifySqlTaskGroupDto> dto) {
        try (var service = new SqlSchemaService(this.jdbcConnectionParameters)) {
            var result = service.create(taskGroup.getSchemaName(), taskGroup.getDdlStatements(), taskGroup.getDiagnoseDmlStatements(), taskGroup.getSubmitDmlStatements());
            result = this.createTaskGroupQueries(taskGroup, result);

            try {
                taskGroup.setSchemaDescription(this.objectMapper.writeValueAsString(result));
                this.repository.save(taskGroup);
            } catch (JsonProcessingException ex) {
                LOG.error("Could not serialize task group schema description", ex);
            }
        } catch (SQLException ex) {
            this.repository.delete(taskGroup);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Could not create database tables for task group: " + ex.getMessage());
        }
    }

    /**
     * Creates task group queries for the specified schema info.
     *
     * @param taskGroup  The task group.
     * @param schemaInfo The schema info.
     * @return New schema info with task group queries.
     */
    private SchemaInfoDto createTaskGroupQueries(SqlRaTaskGroup taskGroup, SchemaInfoDto schemaInfo) {
        List<TableDto> tables = new ArrayList<>();

        LOG.info("Creating task group queries for task group {}", taskGroup.getId());
        for (var table : schemaInfo.tables()) {
            var tgq = this.queryRepository.save(new SqlRaTaskGroupQuery(taskGroup, table.name(), String.format("SELECT * FROM %s;", table.name())));
            tables.add(new TableDto(table.name(), table.columns(), table.foreignKeys(), tgq.getId()));
        }
        return new SchemaInfoDto(tables);
    }
    //#endregion

    //#region --- UPDATE ---
    @Override
    protected void updateTaskGroup(SqlRaTaskGroup taskGroup, ModifyTaskGroupDto<ModifySqlTaskGroupDto> modifyTaskGroupDto) {
        if (!modifyTaskGroupDto.taskGroupType().equals("sql"))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid task group type.");
        this.validateStatements(modifyTaskGroupDto);

        var dbDidNotChange = modifyTaskGroupDto.additionalData().ddlStatements().equals(taskGroup.getDdlStatements()) &&
                             modifyTaskGroupDto.additionalData().diagnoseDmlStatements().equals(taskGroup.getDiagnoseDmlStatements()) &&
                             modifyTaskGroupDto.additionalData().submitDmlStatements().equals(taskGroup.getSubmitDmlStatements());

        if (!dbDidNotChange) {
            try (var service = new SqlSchemaService(this.jdbcConnectionParameters)) {
                var result = service.create(taskGroup.getSchemaName(), modifyTaskGroupDto.additionalData().ddlStatements(), modifyTaskGroupDto.additionalData().diagnoseDmlStatements(), modifyTaskGroupDto.additionalData().submitDmlStatements());
                result = this.updateTaskGroupQueries(taskGroup, result);

                try {
                    taskGroup.setSchemaDescription(this.objectMapper.writeValueAsString(result));
                } catch (JsonProcessingException ex) {
                    LOG.error("Could not serialize task group schema description", ex);
                }
            } catch (SQLException ex) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Could not update database tables for task group: " + ex.getMessage());
            }
        }

        taskGroup.setDdlStatements(modifyTaskGroupDto.additionalData().ddlStatements());
        taskGroup.setDiagnoseDmlStatements(modifyTaskGroupDto.additionalData().diagnoseDmlStatements());
        taskGroup.setSubmitDmlStatements(modifyTaskGroupDto.additionalData().submitDmlStatements());
    }

    /**
     * Updates task group queries for the specified schema info.
     *
     * @param taskGroup  The task group.
     * @param schemaInfo The schema info.
     * @return New schema info with task group queries.
     */
    private SchemaInfoDto updateTaskGroupQueries(SqlRaTaskGroup taskGroup, SchemaInfoDto schemaInfo) {
        List<TableDto> tables = new ArrayList<>();

        for (var table : schemaInfo.tables()) {
            var query = this.queryRepository.findByTableNameIgnoreCaseAndTaskGroup_Id(table.name(), taskGroup.getId())
                .orElseGet(() -> this.queryRepository.save(new SqlRaTaskGroupQuery(taskGroup, table.name(), String.format("SELECT * FROM %s;", table.name()))));
            tables.add(new TableDto(table.name(), table.columns(), table.foreignKeys(), query.getId()));
        }

        this.queryRepository.deleteByTaskGroupAndIdNotIn(taskGroup, tables.stream().map(TableDto::queryId).toList());
        return new SchemaInfoDto(tables);
    }
    //#endregion

    //#region --- DELETE ---

    @Override
    protected void afterDelete(long id) {
        final String schemaPrefix = buildSchemaName(id);
        try (var service = new SqlSchemaService(this.jdbcConnectionParameters)) {
            service.deleteSchemas(schemaPrefix);
            service.commit();
        } catch (SQLException ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Could not delete database tables of task group: " + ex.getMessage());
        }
    }

    //#endregion

    @Override
    protected TaskGroupModificationResponseDto mapToReturnData(SqlRaTaskGroup taskGroup, boolean create) {
        // TODO
        return new TaskGroupModificationResponseDto(
            this.messageSource.getMessage("defaultTaskGroupDescription", new Object[]{}, Locale.GERMAN),
            this.messageSource.getMessage("defaultTaskGroupDescription", new Object[]{}, Locale.ENGLISH));
    }

    /**
     * Builds the schema name for the specified identifier.
     *
     * @param id The task group identifier.
     * @return The schema name.
     */
    public String buildSchemaName(long id) {
        return "task_group_" + id;
    }

    /**
     * Validates the statements to prevent potentially malicious code.
     *
     * @param modifyTaskGroupDto The DTO.
     * @throws jakarta.validation.ValidationException If the statements are invalid.
     */
    private void validateStatements(ModifyTaskGroupDto<ModifySqlTaskGroupDto> modifyTaskGroupDto) {
        // TODO (siehe auch etutorpp??)
    }
}
