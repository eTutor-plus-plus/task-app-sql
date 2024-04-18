package at.jku.dke.task_app.sql.dto;

import at.jku.dke.task_app.sql.data.entities.SqlRaTask;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link SqlRaTask} for relational algebra tasks.
 *
 * @param solution                  The SQL or relational algebra solution.
 * @param sqlSolution               The relational algebra converted to SQL (only for relevant for relational algebra).
 * @param wrongOrderPenalty         The penalty for wrong sorting (-1 means full deduction of points).
 * @param superfluousColumnsPenalty The superfluous columns penalty (-1 means full deduction of points).
 */
public record TaskDto(@NotNull String solution, String sqlSolution, @NotNull BigDecimal wrongOrderPenalty,
                      @NotNull BigDecimal superfluousColumnsPenalty) implements Serializable {
}
