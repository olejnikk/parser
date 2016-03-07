package com.kolejnik.parser;

import com.kolejnik.parser.expression.Node;
import com.kolejnik.parser.token.NumberToken;
import com.kolejnik.parser.token.OperationToken;
import com.kolejnik.parser.token.Token;

import java.util.List;

public class Parser {

    public Node parse(List<Token> tokens) {
        int currPosition = 0;
        OperationToken currOperation = null;

        for (int i = tokens.size() - 1; i >=0; i--) {
            Token token = tokens.get(i);
            if (token instanceof NumberToken) {
                if (tokens.size() == 1) {
                    Node node = new Node();
                    node.setValue(((NumberToken) token).getValue());
                    return node;
                }
                continue;
            }

            OperationToken oToken = (OperationToken) token;
            if (isPriorOperation(oToken, currOperation)) {
                currOperation = oToken;
                currPosition = i;
            }
        }

        if (currOperation == null) {
            return null;
        }
        Node node = new Node();
        node.setOperation(currOperation.getOperation());
        node.setLeft(parse(tokens.subList(0, currPosition)));
        node.setRight(parse(tokens.subList(currPosition + 1, tokens.size())));
        return node;
    }

    private boolean isPriorOperation(OperationToken oToken, OperationToken currOperation) {
        return currOperation == null
                || oToken.getDepth() < currOperation.getDepth()
                || (oToken.getDepth() == currOperation.getDepth()
                && oToken.getOperation().getPriority() < currOperation.getOperation().getPriority());
    }
}
