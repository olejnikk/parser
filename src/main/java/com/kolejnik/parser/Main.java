package com.kolejnik.parser;

import com.kolejnik.parser.expression.Node;
import com.kolejnik.parser.token.Token;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.print(">>> ");
            String input = null;
            try {
                input = br.readLine();
                if ("quit".equals(input)) {
                    br.close();
                    System.exit(0);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            Lexer lexer = new Lexer();
            List<Token> tokens = lexer.tokenize(input);
            Parser parser = new Parser();
            Node node = parser.parse(tokens);
            System.out.println(">>> " + node.compute());
        }


//        Lexer lexer = new Lexer();
//        List<Token> tokens = lexer.tokenize("((3-12)*123 + 3^1)+(132-12)*(44-12)");
//        for (Token token : tokens) {
//            System.out.println(token);
//        }
//
//        Parser parser = new Parser();
//        Node node = parser.parse(tokens);
//
//        System.out.println(">>> " + node.compute());
    }
}
