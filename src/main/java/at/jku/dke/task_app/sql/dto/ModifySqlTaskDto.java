package at.jku.dke.task_app.sql.dto;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * This class represents a data transfer object for modifying a sql/ra task.
 *
 * @param solution                  The solution (if SQL task then SQL query, else relational algebra expression).
 * @param wrongOrderPenalty         The penalty for wrong sorting (-1 means full deduction of points).
 * @param superfluousColumnsPenalty The superfluous columns penalty (-1 means full deduction of points).
 */
public record ModifySqlTaskDto(@NotNull String solution, @NotNull BigDecimal wrongOrderPenalty, @NotNull BigDecimal superfluousColumnsPenalty) implements Serializable {
}
