package at.jku.dke.task_app.sql.services;

import at.jku.dke.etutor.task_app.dto.ModifyTaskGroupDto;
import at.jku.dke.etutor.task_app.dto.TaskStatus;
import at.jku.dke.task_app.sql.config.JdbcConnectionParameters;
import at.jku.dke.task_app.sql.data.entities.SqlRaTaskGroup;
import at.jku.dke.task_app.sql.data.repositories.SqlRaTaskGroupQueryRepository;
import at.jku.dke.task_app.sql.data.repositories.SqlRaTaskGroupRepository;
import at.jku.dke.task_app.sql.dto.ModifySqlTaskGroupDto;
import at.jku.dke.task_app.sql.dto.SchemaInfoDto;
import at.jku.dke.task_app.sql.dto.TableDto;
import jakarta.validation.ValidationException;
import org.junit.jupiter.api.Test;
import org.springframework.context.MessageSource;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class SqlRaTaskGroupServiceTest {

    @Test
    void createTaskGroup() {
        // Arrange
        ModifyTaskGroupDto<ModifySqlTaskGroupDto> dto = new ModifyTaskGroupDto<>("sql", TaskStatus.APPROVED,
            new ModifySqlTaskGroupDto("ddl", "diagnose", "submit"));
        SqlRaTaskGroupService service = new TestService(null, null, null, null, null);

        // Act
        var taskGroup = service.createTaskGroup(3, dto);

        // Assert
        assertEquals(dto.additionalData().ddlStatements(), taskGroup.getDdlStatements());
        assertEquals(dto.additionalData().diagnoseDmlStatements(), taskGroup.getDiagnoseDmlStatements());
        assertEquals(dto.additionalData().submitDmlStatements(), taskGroup.getSubmitDmlStatements());
        assertEquals("task_group_3", taskGroup.getSchemaName());
    }

    @Test
    void createTaskGroupInvalidType() {
        // Arrange
        ModifyTaskGroupDto<ModifySqlTaskGroupDto> dto = new ModifyTaskGroupDto<>("xquery", TaskStatus.APPROVED,
            new ModifySqlTaskGroupDto("ddl", "diagnose", "submit"));
        SqlRaTaskGroupService service = new TestService(null, null, null, null, null);

        // Act & Assert
        assertThrows(ResponseStatusException.class, () -> service.createTaskGroup(3, dto));
    }

    @Test
    void updateTaskGroup() {
        // Arrange
        var repo = mock(SqlRaTaskGroupQueryRepository.class);
        ModifyTaskGroupDto<ModifySqlTaskGroupDto> dto = new ModifyTaskGroupDto<>("sql", TaskStatus.APPROVED,
            new ModifySqlTaskGroupDto("ddl", "diagnose", "submit"));
        SqlRaTaskGroupService service = new TestService(null, repo, null, null, null);
        var taskGroup = new SqlRaTaskGroup();

        // Act
        service.updateTaskGroup(taskGroup, dto);

        // Assert
        assertEquals(dto.additionalData().ddlStatements(), taskGroup.getDdlStatements());
        assertEquals(dto.additionalData().diagnoseDmlStatements(), taskGroup.getDiagnoseDmlStatements());
        assertEquals(dto.additionalData().submitDmlStatements(), taskGroup.getSubmitDmlStatements());
        assertNotNull(taskGroup.getSchemaDescription());
        verify(repo, times(1)).deleteByTaskGroupAndIdNotIn(any(), any());
    }

    @Test
    void updateTaskGroupCreateInDiagnose() {
        // Arrange
        ModifyTaskGroupDto<ModifySqlTaskGroupDto> dto = new ModifyTaskGroupDto<>("sql", TaskStatus.APPROVED,
            new ModifySqlTaskGroupDto("ddl", "diagnose create table x", "submit"));
        SqlRaTaskGroupService service = new TestService(null, null, null, null, null);
        var taskGroup = new SqlRaTaskGroup();

        // Act & Assert
        assertThrows(ValidationException.class, () -> service.updateTaskGroup(taskGroup, dto));
    }

    @Test
    void updateTaskGroupCreateInSubmit() {
        // Arrange
        ModifyTaskGroupDto<ModifySqlTaskGroupDto> dto = new ModifyTaskGroupDto<>("sql", TaskStatus.APPROVED,
            new ModifySqlTaskGroupDto("ddl", "diagnose", "submit create table x"));
        SqlRaTaskGroupService service = new TestService(null, null, null, null, null);
        var taskGroup = new SqlRaTaskGroup();

        // Act & Assert
        assertThrows(ValidationException.class, () -> service.updateTaskGroup(taskGroup, dto));
    }

    @Test
    void updateTaskGroupInsertInDdl() {
        // Arrange
        ModifyTaskGroupDto<ModifySqlTaskGroupDto> dto = new ModifyTaskGroupDto<>("sql", TaskStatus.APPROVED,
            new ModifySqlTaskGroupDto("ddl insert into", "diagnose", "submit"));
        SqlRaTaskGroupService service = new TestService(null, null, null, null, null);
        var taskGroup = new SqlRaTaskGroup();

        // Act & Assert
        assertThrows(ValidationException.class, () -> service.updateTaskGroup(taskGroup, dto));
    }

    @Test
    void updateTaskGroupInvalidType() {
        // Arrange
        ModifyTaskGroupDto<ModifySqlTaskGroupDto> dto = new ModifyTaskGroupDto<>("xquery", TaskStatus.APPROVED,
            new ModifySqlTaskGroupDto("ddl", "diagnose", "submit"));
        SqlRaTaskGroupService service = new TestService(null, null, null, null, null);
        var taskGroup = new SqlRaTaskGroup();

        // Act & Assert
        assertThrows(ResponseStatusException.class, () -> service.updateTaskGroup(taskGroup, dto));
    }

    @Test
    void mapToReturnData() {
        // Arrange
        MessageSource ms = mock(MessageSource.class);
        SqlRaTaskGroupService service = new TestService(null, null, null, null, null);
        var taskGroup = new SqlRaTaskGroup();
        taskGroup.setSchemaDescription(new SchemaInfoDto(List.of(
            new TableDto("test1", List.of(), List.of(), null)
        )));

        // Act
        var result = service.mapToReturnData(taskGroup, true);

        // Assert
        assertNotNull(result);
//        verify(ms).getMessage("defaultTaskGroupDescription", new Object[]{taskGroup.getMinNumber(), taskGroup.getMaxNumber()}, Locale.GERMAN);
//        verify(ms).getMessage("defaultTaskGroupDescription", new Object[]{taskGroup.getMinNumber(), taskGroup.getMaxNumber()}, Locale.ENGLISH);
    }

    private static class TestService extends SqlRaTaskGroupService {
        public TestService(SqlRaTaskGroupRepository repository, SqlRaTaskGroupQueryRepository queryRepository, MessageSource messageSource, JdbcConnectionParameters jdbcConnectionParameters, String sqlUrl) {
            super(repository, queryRepository, messageSource, jdbcConnectionParameters, sqlUrl);
        }

        @Override
        protected SqlSchemaService createSchemaService() throws SQLException {
            var tmp = mock(SqlSchemaService.class);
            when(tmp.getSchemaInfoDto(anyString())).thenReturn(new SchemaInfoDto(List.of()));
            when(tmp.create(any(), any(), any(), any())).thenReturn(new SchemaInfoDto(List.of()));
            return tmp;
        }
    }
}
