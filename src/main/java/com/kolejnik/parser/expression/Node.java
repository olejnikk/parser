package com.kolejnik.parser.expression;

import java.math.BigDecimal;

public class Node {
    private Node left;
    private Node right;
    private Operation operation;
    private BigDecimal value;

    public BigDecimal compute() {
        if (value != null) {
            return value;
        }

        if (left == null && operation.equals(Operation.SUB)) {
            left = new Node();
            left.setValue(BigDecimal.ZERO);
        }

        return operation.compute(left.compute(), right.compute());
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}
