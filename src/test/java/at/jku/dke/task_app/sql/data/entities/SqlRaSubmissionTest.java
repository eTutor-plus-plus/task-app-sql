package at.jku.dke.task_app.sql.data.entities;

import at.jku.dke.etutor.task_app.dto.SubmissionMode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SqlRaSubmissionTest {

    @Test
    void testConstructor1() {
        // Arrange
        var expected = "test";

        // Act
        var submission = new SqlRaSubmission(expected);
        var actual = submission.getSubmission();

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    void testConstructor2() {
        // Arrange
        var user = "test-user";
        var assignment = "a1";
        var task = new SqlRaTask();
        var lang = "de";
        var feedbackLevel = 2;
        var mode = SubmissionMode.SUBMIT;
        var input = "test";

        // Act
        var submission = new SqlRaSubmission(user, assignment, task, lang, feedbackLevel, mode, input);

        // Assert
        assertEquals(user, submission.getUserId());
        assertEquals(assignment, submission.getAssignmentId());
        assertEquals(task, submission.getTask());
        assertEquals(lang, submission.getLanguage());
        assertEquals(feedbackLevel, submission.getFeedbackLevel());
        assertEquals(mode, submission.getMode());
        assertEquals(input, submission.getSubmission());
    }

    @Test
    void testGetSetSubmission() {
        // Arrange
        var submission = new SqlRaSubmission();
        var expected = "test";

        // Act
        submission.setSubmission(expected);
        var actual = submission.getSubmission();

        // Assert
        assertEquals(expected, actual);
    }

}
