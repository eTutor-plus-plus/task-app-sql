package at.jku.dke.task_app.sql.evaluation.grading;

import at.jku.dke.task_app.sql.evaluation.SqlEvaluationCriterion;

import java.math.BigDecimal;

/**
 * Represents a grading entry.
 *
 * @param criterion   The failed criterion.
 * @param minusPoints The deduction points (must be positive or -1).
 */
public record GradingEntry(SqlEvaluationCriterion criterion, BigDecimal minusPoints) {
    public GradingEntry {
        if (minusPoints == null || !(minusPoints.compareTo(BigDecimal.ZERO) > 0 || minusPoints.compareTo(BigDecimal.ONE.negate()) == 0))
            throw new IllegalArgumentException("Minus points must not be less than -1.");
    }
}
