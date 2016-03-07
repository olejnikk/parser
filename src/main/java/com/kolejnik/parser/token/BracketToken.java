package com.kolejnik.parser.token;

import com.kolejnik.parser.Lexer;

public class BracketToken extends Token {
    public BracketToken(String text) {
        super(text);
    }

    public boolean isOpen() {
        return Lexer.OPEN == text.charAt(0);
    }

    public boolean isClose() {
        return Lexer.CLOSE == text.charAt(0);
    }
}
