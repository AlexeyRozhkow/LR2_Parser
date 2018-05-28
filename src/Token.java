public class Token {
    private Lexeme lexeme;
    private String value;
    Token(Lexeme lexeme, String value) {
        this.lexeme = lexeme;
        this.value = value;
    }

    Lexeme getLexeme() {
        return lexeme;
    }

    @Override
    public String toString() {
        return "Token [ " +
                "Lexeme " + lexeme +
                ", Value: '" + value + "' ] ";
    }
}
