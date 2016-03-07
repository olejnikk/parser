package com.kolejnik.parser.token;

import java.math.BigDecimal;

public class NumberToken extends Token {

    private BigDecimal value;

    public NumberToken(String text) {
        super(text);
        this.value = new BigDecimal(text);
    }

    public BigDecimal getValue() {
        return value;
    }
}
