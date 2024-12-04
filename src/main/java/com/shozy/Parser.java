package com.shozy;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    Lexer lexer;

    public Parser(String input) {
        this.lexer = new Lexer(input);
    }

    public Statement parse() throws IllegalArgumentException {
        List<Token> tokens = new ArrayList<>();
        Token token;
        while ((token = lexer.nextToken()) != null && token.getTokenType() != Token.TokenType.EOF) {
            tokens.add(token);
        }
        return new Statement(tokens);
    }
}