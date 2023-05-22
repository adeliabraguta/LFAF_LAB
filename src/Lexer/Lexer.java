package Lexer;

import Lexer.Token.TokenType;

import java.util.ArrayList;
import java.util.List;

public class Lexer {
    private String input;
    private int currentPos;

    public Lexer(String input) {
        this.input = input;
        this.currentPos = 0;
    }

    public List<Token> tokenize() throws Exception {
        List<Token> tokens = new ArrayList<>();

        while (currentPos < input.length()) {
            char currentChar = input.charAt(currentPos);

            if (Character.isDigit(currentChar)) {
                String number = "";
                while (currentPos < input.length() && Character.isDigit(input.charAt(currentPos))) {
                    number += input.charAt(currentPos++);
                }
                tokens.add(new Token(TokenType.NUMBER, number));
            } else if (currentChar == '+') {
                tokens.add(new Token(TokenType.PLUS, "+"));
                currentPos++;
            } else if (currentChar == '-') {
                tokens.add(new Token(TokenType.MINUS, "-"));
                currentPos++;
            } else if (currentChar == '*') {
                tokens.add(new Token(TokenType.MULTIPLY, "*"));
                currentPos++;
            } else if (currentChar == '/') {
                tokens.add(new Token(TokenType.DIVIDE, "/"));
                currentPos++;
            } else {
                throw new Exception("Invalid character: " + currentChar);
            }
        }

        return tokens;
    }
}

