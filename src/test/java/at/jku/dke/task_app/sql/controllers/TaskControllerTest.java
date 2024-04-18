package at.jku.dke.task_app.sql.controllers;

import at.jku.dke.etutor.task_app.auth.AuthConstants;
import at.jku.dke.etutor.task_app.dto.TaskStatus;
import at.jku.dke.task_app.sql.ClientSetupExtension;
import at.jku.dke.task_app.sql.DatabaseSetupExtension;
import at.jku.dke.task_app.sql.data.entities.SqlRaTask;
import at.jku.dke.task_app.sql.data.entities.SqlRaTaskGroup;
import at.jku.dke.task_app.sql.data.repositories.SqlRaTaskGroupRepository;
import at.jku.dke.task_app.sql.data.repositories.SqlRaTaskRepository;
import at.jku.dke.task_app.sql.dto.ModifySqlTaskDto;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.math.BigDecimal;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith({DatabaseSetupExtension.class, ClientSetupExtension.class})
class TaskControllerTest {
// TODO
//    @LocalServerPort
//    private int port;
//
//    @Autowired
//    private SqlRaTaskRepository repository;
//
//    @Autowired
//    private SqlRaTaskGroupRepository groupRepository;
//
//    private long taskId;
//    private long taskGroupId;
//
//    @BeforeEach
//    void initDb() {
//        this.repository.deleteAll();
//
//        var group = this.groupRepository.save(new SqlRaTaskGroup(1L, TaskStatus.APPROVED, 1, 10));
//        this.taskGroupId = group.getId();
//        this.taskId = this.repository.save(new SqlRaTask(1L, BigDecimal.TWO, TaskStatus.APPROVED, group, 5)).getId();
//    }
//
//    //#region --- GET ---
//    @Test
//    void getShouldReturnOk() {
//        given()
//            .port(port)
//            .header(AuthConstants.AUTH_TOKEN_HEADER_NAME, ClientSetupExtension.CRUD_API_KEY)
//            .accept(ContentType.JSON)
//            // WHEN
//            .when()
//            .get("/api/task/{id}", this.taskId)
//            // THEN
//            .then()
//            .log().ifValidationFails()
//            .statusCode(200)
//            .contentType(ContentType.JSON)
//            .body("solution", equalTo(5));
//    }
//
//    @Test
//    void getShouldReturnNotFound() {
//        given()
//            .port(port)
//            .header(AuthConstants.AUTH_TOKEN_HEADER_NAME, ClientSetupExtension.CRUD_API_KEY)
//            .accept(ContentType.JSON)
//            // WHEN
//            .when()
//            .get("/api/task/{id}", this.taskId + 1)
//            // THEN
//            .then()
//            .log().ifValidationFails()
//            .statusCode(404);
//    }
//
//    @Test
//    void getShouldReturnForbidden() {
//        given()
//            .port(port)
//            .header(AuthConstants.AUTH_TOKEN_HEADER_NAME, ClientSetupExtension.SUBMIT_API_KEY)
//            .accept(ContentType.JSON)
//            // WHEN
//            .when()
//            .get("/api/task/{id}", this.taskId)
//            // THEN
//            .then()
//            .log().ifValidationFails()
//            .statusCode(403);
//    }
//    //#endregion
//
//    //#region --- CREATE ---
//    @Test
//    void createShouldReturnCreated() {
//        given()
//            .port(port)
//            .header(AuthConstants.AUTH_TOKEN_HEADER_NAME, ClientSetupExtension.CRUD_API_KEY)
//            .contentType(ContentType.JSON)
//            .body(new at.jku.dke.etutor.task_app.dto.ModifyTaskDto<>(this.taskGroupId, BigDecimal.TEN, "sql", TaskStatus.APPROVED, new ModifySqlTaskDto(6)))
//            // WHEN
//            .when()
//            .post("/api/task/{id}", this.taskId + 2)
//            // THEN
//            .then()
//            .log().ifValidationFails()
//            .statusCode(201)
//            .contentType(ContentType.JSON)
//            .header("Location", containsString("/api/task/" + (this.taskId + 2)))
//            .body("descriptionDe", any(String.class))
//            .body("descriptionEn", any(String.class));
//    }
//
//    @Test
//    void createShouldReturnBadRequestOnInvalidBody() {
//        given()
//            .port(port)
//            .header(AuthConstants.AUTH_TOKEN_HEADER_NAME, ClientSetupExtension.CRUD_API_KEY)
//            .contentType(ContentType.JSON)
//            .body(new at.jku.dke.etutor.task_app.dto.ModifyTaskDto<>(this.taskGroupId, BigDecimal.TEN, "", TaskStatus.APPROVED, new ModifySqlTaskDto(6)))
//            // WHEN
//            .when()
//            .post("/api/task/{id}", this.taskId + 2)
//            // THEN
//            .then()
//            .log().ifValidationFails()
//            .statusCode(400);
//    }
//
//    @Test
//    void createShouldReturnBadRequestOnEmptyBody() {
//        given()
//            .port(port)
//            .header(AuthConstants.AUTH_TOKEN_HEADER_NAME, ClientSetupExtension.CRUD_API_KEY)
//            .contentType(ContentType.JSON)
//            // WHEN
//            .when()
//            .post("/api/task/{id}", this.taskId + 2)
//            // THEN
//            .then()
//            .log().ifValidationFails()
//            .statusCode(400);
//    }
//
//    @Test
//    void createShouldReturnForbidden() {
//        given()
//            .port(port)
//            .header(AuthConstants.AUTH_TOKEN_HEADER_NAME, ClientSetupExtension.SUBMIT_API_KEY)
//            .contentType(ContentType.JSON)
//            .body(new at.jku.dke.etutor.task_app.dto.ModifyTaskDto<>(this.taskGroupId, BigDecimal.TEN, "sql", TaskStatus.APPROVED, new ModifySqlTaskDto(6)))
//            // WHEN
//            .when()
//            .post("/api/task/{id}", this.taskId + 2)
//            // THEN
//            .then()
//            .log().ifValidationFails()
//            .statusCode(403);
//    }
//    //#endregion
//
//    //#region --- UPDATE ---
//    @Test
//    void updateShouldReturnOk() {
//        given()
//            .port(port)
//            .header(AuthConstants.AUTH_TOKEN_HEADER_NAME, ClientSetupExtension.CRUD_API_KEY)
//            .contentType(ContentType.JSON)
//            .body(new at.jku.dke.etutor.task_app.dto.ModifyTaskDto<>(this.taskGroupId, BigDecimal.TEN, "sql", TaskStatus.APPROVED, new ModifySqlTaskDto(9)))
//            // WHEN
//            .when()
//            .put("/api/task/{id}", this.taskId)
//            // THEN
//            .then()
//            .log().ifValidationFails()
//            .statusCode(200)
//            .contentType(ContentType.JSON)
//            .body("descriptionDe", any(String.class))
//            .body("descriptionEn", any(String.class));
//    }
//
//    @Test
//    void updateShouldReturnNotFound() {
//        given()
//            .port(port)
//            .header(AuthConstants.AUTH_TOKEN_HEADER_NAME, ClientSetupExtension.CRUD_API_KEY)
//            .contentType(ContentType.JSON)
//            .body(new at.jku.dke.etutor.task_app.dto.ModifyTaskDto<>(this.taskGroupId, BigDecimal.TEN, "sql", TaskStatus.APPROVED, new ModifySqlTaskDto(9)))
//            // WHEN
//            .when()
//            .put("/api/task/{id}", this.taskId + 1)
//            // THEN
//            .then()
//            .log().ifValidationFails()
//            .statusCode(404);
//    }
//
//    @Test
//    void updateShouldReturnBadRequestOnInvalidBody() {
//        given()
//            .port(port)
//            .header(AuthConstants.AUTH_TOKEN_HEADER_NAME, ClientSetupExtension.CRUD_API_KEY)
//            .contentType(ContentType.JSON)
//            .body(new at.jku.dke.etutor.task_app.dto.ModifyTaskDto<>(this.taskGroupId, BigDecimal.TEN, "sql", TaskStatus.APPROVED, new ModifySqlTaskDto(9)))
//            // WHEN
//            .when()
//            .put("/api/task/{id}", this.taskId)
//            // THEN
//            .then()
//            .log().ifValidationFails()
//            .statusCode(400);
//    }
//
//    @Test
//    void updateShouldReturnBadRequestOnEmptyBody() {
//        given()
//            .port(port)
//            .header(AuthConstants.AUTH_TOKEN_HEADER_NAME, ClientSetupExtension.CRUD_API_KEY)
//            .contentType(ContentType.JSON)
//            // WHEN
//            .when()
//            .put("/api/task/{id}", this.taskId)
//            // THEN
//            .then()
//            .log().ifValidationFails()
//            .statusCode(400);
//    }
//
//    @Test
//    void updateShouldReturnForbidden() {
//        given()
//            .port(port)
//            .header(AuthConstants.AUTH_TOKEN_HEADER_NAME, ClientSetupExtension.SUBMIT_API_KEY)
//            .contentType(ContentType.JSON)
//            .body(new at.jku.dke.etutor.task_app.dto.ModifyTaskDto<>(this.taskGroupId, BigDecimal.TEN, "sql", TaskStatus.APPROVED, new ModifySqlTaskDto(9)))
//            // WHEN
//            .when()
//            .put("/api/task/{id}", this.taskId)
//            // THEN
//            .then()
//            .log().ifValidationFails()
//            .statusCode(403);
//    }
//    //#endregion
//
//    //#region --- DELETE ---
//    @Test
//    void deleteShouldReturnNoContent() {
//        given()
//            .port(port)
//            .header(AuthConstants.AUTH_TOKEN_HEADER_NAME, ClientSetupExtension.CRUD_API_KEY)
//            // WHEN
//            .when()
//            .delete("/api/task/{id}", this.taskId)
//            // THEN
//            .then()
//            .log().ifValidationFails()
//            .statusCode(204);
//    }
//
//    @Test
//    void deleteShouldReturnNoContentOnNotFound() {
//        given()
//            .port(port)
//            .header(AuthConstants.AUTH_TOKEN_HEADER_NAME, ClientSetupExtension.CRUD_API_KEY)
//            // WHEN
//            .when()
//            .delete("/api/task/{id}", this.taskId + 1)
//            // THEN
//            .then()
//            .log().ifValidationFails()
//            .statusCode(204);
//    }
//
//    @Test
//    void deleteShouldReturnForbidden() {
//        given()
//            .port(port)
//            .header(AuthConstants.AUTH_TOKEN_HEADER_NAME, ClientSetupExtension.SUBMIT_API_KEY)
//            // WHEN
//            .when()
//            .delete("/api/task/{id}", this.taskId)
//            // THEN
//            .then()
//            .log().ifValidationFails()
//            .statusCode(403);
//    }
//    //#endregion
//
//    @Test
//    void mapToDto() {
//        // Arrange
//        var task = new SqlRaTask(42);
//
//        // Act
//        var result = new TaskController(null).mapToDto(task);
//
//        // Assert
//        assertEquals(42, result.solution());
//    }

}
