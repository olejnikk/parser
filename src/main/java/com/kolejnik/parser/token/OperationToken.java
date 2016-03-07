package com.kolejnik.parser.token;

import com.kolejnik.parser.expression.Operation;

public class OperationToken extends Token {

    private Operation operation;

    public OperationToken(String text) {
        super(text);
        this.operation = Operation.getBySymbol(text);
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }
}
