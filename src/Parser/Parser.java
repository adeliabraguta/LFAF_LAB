package Parser;

import Lexer.Token.TokenType;
import Lexer.Token;

import java.util.List;

public class Parser {
    private final List<Token> tokens;
    private int currentTokenIndex;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
        this.currentTokenIndex = 0;
    }

    public int parseExpression() throws Exception {
        int result = parseTerm();

        while (currentToken().getType() == TokenType.PLUS || currentToken().getType() == TokenType.MINUS) {
            TokenType operator = currentToken().getType();
            consumeToken(); // Consume the operator token

            int term = parseTerm();
            if (operator == TokenType.PLUS) {
                result += term;
            } else if (operator == TokenType.MINUS) {
                result -= term;
            } else {
                throw new Exception("Unexpected operator: " + operator);
            }
        }

        return result;
    }

    private int parseTerm() throws Exception {
        int result = parseFactor();

        while (currentToken().getType() == TokenType.MULTIPLY || currentToken().getType() == TokenType.DIVIDE) {
            TokenType operator = currentToken().getType();
            consumeToken(); // Consume the operator token

            int factor = parseFactor();
            if (operator == TokenType.MULTIPLY) {
                result *= factor;
            } else if (operator == TokenType.DIVIDE) {
                result /= factor;
            } else {
                throw new Exception("Unexpected operator: " + operator);
            }
        }

        return result;
    }

    private int parseFactor() throws Exception {
        if (currentToken().getType() == TokenType.NUMBER) {
            int value = Integer.parseInt(currentToken().getValue());
            consumeToken(); // Consume the number token
            return value;
        } else {
            throw new Exception("Invalid factor: " + currentToken().getValue());
        }
    }

    private Token currentToken() {
        return tokens.get(currentTokenIndex);
    }

    private void consumeToken() {
        currentTokenIndex++;
    }
}
