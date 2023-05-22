package Lexer;

public class Token {
    public enum TokenType {
        NUMBER,
        PLUS,
        MINUS,
        MULTIPLY,
        DIVIDE
    }

    private TokenType type;
    private String value;

    public Token(TokenType type, String value) {
        this.type = type;
        this.value = value;
    }

    public TokenType getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public String toString() {
        return type + ": " + value;
    }
}
