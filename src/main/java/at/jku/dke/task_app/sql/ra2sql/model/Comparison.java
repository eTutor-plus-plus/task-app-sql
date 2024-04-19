package at.jku.dke.task_app.sql.ra2sql.model;

/**
 * Represents a comparison.
 */
public class Comparison {
    private String leftValue;
    private String rightValue;
    private ComparisonOperator operator;
    private ComparisonValueType leftValueType;
    private ComparisonValueType rightValueType;

    /**
     * Creates a new instance of class {@link Comparison}.
     */
    public Comparison() {
        this.leftValue = "";
        this.rightValue = "";
        this.operator = ComparisonOperator.EQUAL;
        this.rightValueType = ComparisonValueType.LITERAL;
        this.leftValueType = ComparisonValueType.LITERAL;
    }

    /**
     * Gets the left value.
     *
     * @return The left value.
     */
    public String getLeftValue() {
        return leftValue;
    }

    /**
     * Sets the left value.
     *
     * @param leftValue The left value.
     * @return {@code true} if the value was set; {@code false} otherwise.
     */
    public boolean setLeftValue(String leftValue) {
        if (leftValue != null && !leftValue.isEmpty()) {
            this.leftValue = leftValue;
            return true;
        }
        return false;
    }

    /**
     * Gets the right value.
     *
     * @return The right value.
     */
    public String getRightValue() {
        return rightValue;
    }

    /**
     * Sets the right value.
     *
     * @param rightValue The right value.
     * @return {@code true} if the value was set; {@code false} otherwise.
     */
    public boolean setRightValue(String rightValue) {
        if (rightValue != null && !rightValue.isEmpty()) {
            this.rightValue = rightValue;
            return true;
        }
        return false;
    }

    /**
     * Gets the operator.
     *
     * @return The operator.
     */
    public ComparisonOperator getOperator() {
        return operator;
    }

    /**
     * Sets the operator.
     *
     * @param operator The operator.
     * @return {@code true} if the value was set; {@code false} otherwise.
     */
    public boolean setOperator(ComparisonOperator operator) {
        if (operator == null)
            return false;
        this.operator = operator;
        return true;
    }

    /**
     * Gets the left value type.
     *
     * @return The left value type.
     */
    public ComparisonValueType getLeftValueType() {
        return leftValueType;
    }

    /**
     * Sets the left value type.
     *
     * @param leftValueType The left value type.
     * @return {@code true} if the value was set; {@code false} otherwise.
     */
    public boolean setLeftValueType(ComparisonValueType leftValueType) {
        if (leftValueType == null)
            return false;
        this.leftValueType = leftValueType;
        return true;
    }

    /**
     * Gets the right value type.
     *
     * @return The right value type.
     */
    public ComparisonValueType getRightValueType() {
        return rightValueType;
    }

    /**
     * Sets the right value type.
     *
     * @param rightValueType The right value type.
     * @return {@code true} if the value was set; {@code false} otherwise.
     */
    public boolean setRightValueType(ComparisonValueType rightValueType) {
        if (rightValueType == null)
            return false;
        this.rightValueType = rightValueType;
        return true;
    }

    @Override
    public String toString() {
        return this.leftValue + ' ' + this.operator + ' ' + this.rightValue;
    }
}
