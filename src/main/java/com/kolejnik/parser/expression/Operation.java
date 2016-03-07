package com.kolejnik.parser.expression;

import java.math.BigDecimal;

public enum Operation {
    ADD(1, "+") {
        @Override
        public BigDecimal compute(BigDecimal a, BigDecimal b) {
            return a.add(b);
        }
    },
    SUB(1, "-") {
        @Override
        public BigDecimal compute(BigDecimal a, BigDecimal b) {
            return a.subtract(b);
        }
    },
    MUL(2, "*") {
        @Override
        public BigDecimal compute(BigDecimal a, BigDecimal b) {
            return a.multiply(b);
        }
    },
    DIV(2, "/") {
        @Override
        public BigDecimal compute(BigDecimal a, BigDecimal b) {
            return a.divide(b);
        }
    },
    POW(3, "^") {
        @Override
        public BigDecimal compute(BigDecimal a, BigDecimal b) {
            return a.pow(b.intValue());
        }
    };

    private int priority;
    private String symbol;

    public static Operation getBySymbol(String symbol) {
        for (Operation operation : Operation.values()) {
            if (operation.symbol.equals(symbol)) {
                return operation;
            }
        }
        throw new EnumConstantNotPresentException(Operation.class, symbol);
    }

    public int getPriority() {
        return priority;
    }

    public abstract BigDecimal compute(BigDecimal a, BigDecimal b);

    Operation(int priority, String symbol) {
        this.priority = priority;
        this.symbol = symbol;
    }
}
