package analizador;

public class Token {
    public enum Tipo {
        // Palabras reservadas
        AUTO, BREAK, CASE, CHAR, CONST, CONTINUE, DEFAULT, DO, DOUBLE, ELSE, ENUM, EXTERN, FLOAT, FOR, GOTO, IF, INLINE, INT, LONG, REGISTER, RESTRICT, RETURN, SHORT, SIGNED, SIZEOF, STATIC, STRUCT, SWITCH, MAIN, TYPEDEF, UNION, UNSIGNED, VOID, VOLATILE, WHILE,
        // Operadores y caracteres especiales
        LBRACE, RBRACE, LSQUARE, RSQUARE, LPAR, RPAR, SEMI, COLON, COMMA, DOT, AMP, AMPAMP, AMPEQ, STAR, STAREQ, PLUS, PLUSEQ, PLUSPLUS, MINUS, MINUSEQ, MINUSMINUS, TILDE, EXCL, EXCLEQ, SLASH, SLASHEQ, PERCENT, PERCENTEQ, LT, LTLT, LTLTEQ, LTEQ, EQ, EQEQ, GT, GTGT, GTGTEQ, GTEQ, CARET, CARETEQ, PIPE, PIPEPIPE, PIPEEQ, QUESTION, COLONCOLON, ELLIPSIS, DQUOTE, SQUOTE, AT, HASH, DOLLAR, UNDERSCORE, INVALID,
        // Tipos de tokens
        INTEGER, IDENTIFIER, STRING, CHARACTER, EOF
    }

    private Tipo tipo;
    private String valor;

    public Token(Tipo tipo, String valor) {
        this.tipo = tipo;
        this.valor = valor;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public String getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return "Token: " + tipo + " '" + valor+"'";
    }
}
