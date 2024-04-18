package at.jku.dke.task_app.sql.data.entities;

import at.jku.dke.etutor.task_app.data.entities.BaseTaskInGroup;
import at.jku.dke.task_app.sql.data.converters.TaskTypeConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.math.BigDecimal;

/**
 * Represents an SQL/RELALG task.
 */
@Entity
@Table(name = "task")
public class SqlRaTask extends BaseTaskInGroup<SqlRaTaskGroup> {
    @Convert(converter = TaskTypeConverter.class)
    @Column(name = "type", nullable = false, columnDefinition = "task_type not null")
    private TaskType type;

    @Column(name = "solution", nullable = false)
    private String solution;

    @Column(name = "relalg_solution")
    private String relationalAlgebraSolution;

    @Column(name = "wrong_order_penalty", precision = 5, scale = 2, nullable = false)
    private BigDecimal wrongOrderPenalty;

    @Column(name = "superfluous_columns_penalty", precision = 5, scale = 2, nullable = false)
    private BigDecimal superfluousColumnsPenalty;

    /**
     * Creates a new instance of class {@link SqlRaTask}.
     */
    public SqlRaTask() {
    }

    /**
     * Gets the type.
     *
     * @return The type.
     */
    public TaskType getType() {
        return type;
    }

    /**
     * Sets the type.
     *
     * @param type The type.
     */
    public void setType(TaskType type) {
        this.type = type;
    }

    /**
     * Gets the solution.
     *
     * @return The solution.
     */
    public String getSolution() {
        return solution;
    }

    /**
     * Sets the solution.
     *
     * @param solution The solution.
     */
    public void setSolution(String solution) {
        this.solution = solution;
    }

    /**
     * Gets the relational algebra solution.
     *
     * @return The relational algebra solution.
     */
    public String getRelationalAlgebraSolution() {
        return relationalAlgebraSolution;
    }

    /**
     * Sets the relational algebra solution.
     *
     * @param relationalAlgebraSolution The relational algebra solution.
     */
    public void setRelationalAlgebraSolution(String relationalAlgebraSolution) {
        this.relationalAlgebraSolution = relationalAlgebraSolution;
    }

    /**
     * Gets the wrong order penalty.
     *
     * @return The wrong order penalty.
     */
    public BigDecimal getWrongOrderPenalty() {
        return wrongOrderPenalty;
    }

    /**
     * Sets the wrong order penalty.
     *
     * @param wrongOrderPenalty The wrong order penalty.
     */
    public void setWrongOrderPenalty(BigDecimal wrongOrderPenalty) {
        this.wrongOrderPenalty = wrongOrderPenalty;
    }

    /**
     * Gets the superfluous columns penalty.
     *
     * @return The superfluous columns penalty.
     */
    public BigDecimal getSuperfluousColumnsPenalty() {
        return superfluousColumnsPenalty;
    }

    /**
     * Sets the superfluous columns penalty.
     *
     * @param superfluousColumnsPenalty The superfluous columns penalty.
     */
    public void setSuperfluousColumnsPenalty(BigDecimal superfluousColumnsPenalty) {
        this.superfluousColumnsPenalty = superfluousColumnsPenalty;
    }
}
