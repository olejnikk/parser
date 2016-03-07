package com.kolejnik.parser;

import com.kolejnik.parser.token.BracketToken;
import com.kolejnik.parser.token.NumberToken;
import com.kolejnik.parser.token.OperationToken;
import com.kolejnik.parser.token.Token;

import java.util.ArrayList;
import java.util.List;

public class Lexer {
    private static final char PLUS = '+';
    private static final char MINUS = '-';
    public static final char STAR = '*';
    public static final char SLASH = '/';
    public static final char DASH = '^';
    public static final char OPEN = '(';
    public static final char CLOSE = ')';

    public List<Token> tokenize(String expr) {
        List<Token> tokens = new ArrayList<>();
        StringBuilder sb;
        char[] charArray = expr.toCharArray();
        char c;
        int depth = 0;

        for (int i = 0; i < charArray.length; i++) {
            c = charArray[i];
            if (CharacterType.WHITESPACE.isTypeOf(c)) {
                continue;
            }
            if (CharacterType.OPERATOR.isTypeOf(c)) {
                OperationToken oToken = new OperationToken(String.valueOf(c));
                oToken.setDepth(depth);
                tokens.add(oToken);
                continue;
            }

            if (CharacterType.BRACKET.isTypeOf(c)) {
                BracketToken bToken = new BracketToken(String.valueOf(c));
                if (bToken.isOpen()) {
                    depth++;
                } else {
                    depth--;
                }
                continue;
            }

            sb = new StringBuilder();
            while (i < charArray.length && CharacterType.NUMBER.isTypeOf(charArray[i])) {
                c = charArray[i++];
                sb.append(c);
            }
            if (sb.length() > 0) {
                NumberToken nToken = new NumberToken(sb.toString());
                nToken.setDepth(depth);
                tokens.add(nToken);
                i--;
            }
        }
        return tokens;
    }

    private enum CharacterType {
        OPERATOR {
            @Override
            public boolean isTypeOf(char c) {
                return c == PLUS || c == MINUS || c == STAR || c == SLASH || c == DASH;
            }
        },
        BRACKET {
            @Override
            public boolean isTypeOf(char c) {
                return c == OPEN || c == CLOSE;
            }
        },
        NUMBER {
            @Override
            public boolean isTypeOf(char c) {
                int cInt = (int)c;
                return cInt >= 48 && cInt <= 57 || c == '.' || c ==',';
            }
        },
        WHITESPACE {
            @Override
            public boolean isTypeOf(char c) {
                return (int)c == 32;
            }
        };

        public abstract boolean isTypeOf(char c);
    }

}
