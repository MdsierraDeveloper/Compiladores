package analizador;

public class AnalizadorLexico {

    private String entrada;
    private int posicion;
    private char caracterActual;

    public AnalizadorLexico(String entrada) {
        this.entrada = entrada;
        this.posicion = 0;
        this.caracterActual = entrada.charAt(posicion);
    }

    private void avanzar() {
        posicion++;
        if (posicion < entrada.length()) {
            caracterActual = entrada.charAt(posicion);
        } else {
            caracterActual = '\0';
        }
    }

    private void omitirEspacios() {
        while (caracterActual != '\0' && (Character.isWhitespace(caracterActual) || caracterActual == '\u00A0')) {
            avanzar();
        }
    }

    private String numeroEntero() {
        StringBuilder resultado = new StringBuilder();
        while (caracterActual != '\0' && Character.isDigit(caracterActual)) {
            resultado.append(caracterActual);
            avanzar();
        }
        return resultado.toString();
    }

    private String identificador() {
        StringBuilder resultado = new StringBuilder();
        while (caracterActual != '\0' && (Character.isLetter(caracterActual) || caracterActual == '_')) {
            resultado.append(caracterActual);
            avanzar();
        }
        return resultado.toString();
    }

    private String caracter() {
        StringBuilder resultado = new StringBuilder();
        if (caracterActual == '\'') {
            avanzar();
            if (caracterActual != '\0' && caracterActual != '\'') {
                resultado.append(caracterActual);
                avanzar();
                if (caracterActual == '\'') {
                    avanzar();
                    return resultado.toString();
                }
            }
        }
        throw new RuntimeException("Constante de carácter inválida");
    }

    private String cadena() {
        StringBuilder resultado = new StringBuilder();
        if (caracterActual == '\"') {
            avanzar();
            while (caracterActual != '\0' && caracterActual != '\"') {
                resultado.append(caracterActual);
                avanzar();
            }
            if (caracterActual == '\"') {
                avanzar();
                return resultado.toString();
            }
        }
        throw new RuntimeException("Constante de cadena inválida");
    }

    public Token obtenerSiguienteToken() {
        while (caracterActual != '\0') {
            omitirEspacios(); // Asegúrate de omitir espacios en cada iteración
            if (Character.isDigit(caracterActual)) {
                return new Token(Token.Tipo.INTEGER, numeroEntero());
            }
            if (Character.isLetter(caracterActual) || caracterActual == '_') {
                String id = identificador();
                switch (id) {
                    case "main":
                        return new Token(Token.Tipo.MAIN, id);
                    case "auto":
                        return new Token(Token.Tipo.AUTO, id);
                    case "break":
                        return new Token(Token.Tipo.BREAK, id);
                    case "case":
                        return new Token(Token.Tipo.CASE, id);
                    case "char":
                        return new Token(Token.Tipo.CHAR, id);
                    case "const":
                        return new Token(Token.Tipo.CONST, id);
                    case "continue":
                        return new Token(Token.Tipo.CONTINUE, id);
                    case "default":
                        return new Token(Token.Tipo.DEFAULT, id);
                    case "do":
                        return new Token(Token.Tipo.DO, id);
                    case "double":
                        return new Token(Token.Tipo.DOUBLE, id);
                    case "else":
                        return new Token(Token.Tipo.ELSE, id);
                    case "enum":
                        return new Token(Token.Tipo.ENUM, id);
                    case "extern":
                        return new Token(Token.Tipo.EXTERN, id);
                    case "float":
                        return new Token(Token.Tipo.FLOAT, id);
                    case "for":
                        return new Token(Token.Tipo.FOR, id);
                    case "goto":
                        return new Token(Token.Tipo.GOTO, id);
                    case "if":
                        return new Token(Token.Tipo.IF, id);
                    case "inline":
                        return new Token(Token.Tipo.INLINE, id);
                    case "int":
                        return new Token(Token.Tipo.INT, id);
                    case "long":
                        return new Token(Token.Tipo.LONG, id);
                    case "register":
                        return new Token(Token.Tipo.REGISTER, id);
                    case "restrict":
                        return new Token(Token.Tipo.RESTRICT, id);
                    case "return":
                        return new Token(Token.Tipo.RETURN, id);
                    case "short":
                        return new Token(Token.Tipo.SHORT, id);
                    case "signed":
                        return new Token(Token.Tipo.SIGNED, id);
                    case "sizeof":
                        return new Token(Token.Tipo.SIZEOF, id);
                    case "static":
                        return new Token(Token.Tipo.STATIC, id);
                    case "struct":
                        return new Token(Token.Tipo.STRUCT, id);
                    case "switch":
                        return new Token(Token.Tipo.SWITCH, id);
                    case "typedef":
                        return new Token(Token.Tipo.TYPEDEF, id);
                    case "union":
                        return new Token(Token.Tipo.UNION, id);
                    case "unsigned":
                        return new Token(Token.Tipo.UNSIGNED, id);
                    case "void":
                        return new Token(Token.Tipo.VOID, id);
                    case "volatile":
                        return new Token(Token.Tipo.VOLATILE, id);
                    case "while":
                        return new Token(Token.Tipo.WHILE, id);
                    default:
                        return new Token(Token.Tipo.IDENTIFIER, id);
                }
            }
            switch (caracterActual) {
                case '+':
                    avanzar();
                    if (caracterActual == '+') {
                        avanzar();
                        return new Token(Token.Tipo.PLUSPLUS, "++");
                    }
                    if (caracterActual == '=') {
                        avanzar();
                        return new Token(Token.Tipo.PLUSEQ, "+=");
                    }
                    return new Token(Token.Tipo.PLUS, "+");
                case '-':
                    avanzar();
                    if (caracterActual == '-') {
                        avanzar();
                        return new Token(Token.Tipo.MINUSMINUS, "--");
                    }
                    if (caracterActual == '=') {
                        avanzar();
                        return new Token(Token.Tipo.MINUSEQ, "-=");
                    }
                    return new Token(Token.Tipo.MINUS, "-");
                case '*':
                    avanzar();
                    if (caracterActual == '=') {
                        avanzar();
                        return new Token(Token.Tipo.STAREQ, "*=");
                    }
                    return new Token(Token.Tipo.STAR, "*");
                case '/':
                    avanzar();
                    if (caracterActual == '=') {
                        avanzar();
                        return new Token(Token.Tipo.SLASHEQ, "/=");
                    }
                    return new Token(Token.Tipo.SLASH, "/");
                case '%':
                    avanzar();
                    if (caracterActual == '=') {
                        avanzar();
                        return new Token(Token.Tipo.PERCENTEQ, "%=");
                    }
                    return new Token(Token.Tipo.PERCENT, "%");
                case '&':
                    avanzar();
                    if (caracterActual == '&') {
                        avanzar();
                        return new Token(Token.Tipo.AMPAMP, "&&");
                    }
                    if (caracterActual == '=') {
                        avanzar();
                        return new Token(Token.Tipo.AMPEQ, "&=");
                    }
                    return new Token(Token.Tipo.AMP, "&");
                case '|':
                    avanzar();
                    if (caracterActual == '|') {
                        avanzar();
                        return new Token(Token.Tipo.PIPEPIPE, "||");
                    }
                    if (caracterActual == '=') {
                        avanzar();
                        return new Token(Token.Tipo.PIPEEQ, "|=");
                    }
                    return new Token(Token.Tipo.PIPE, "|");
                case '^':
                    avanzar();
                    if (caracterActual == '=') {
                        avanzar();
                        return new Token(Token.Tipo.CARETEQ, "^=");
                    }
                    return new Token(Token.Tipo.CARET, "^");
                case '~':
                    avanzar();
                    return new Token(Token.Tipo.TILDE, "~");
                case '!':
                    avanzar();
                    if (caracterActual == '=') {
                        avanzar();
                        return new Token(Token.Tipo.EXCLEQ, "!=");
                    }
                    return new Token(Token.Tipo.EXCL, "!");
                case '=':
                    avanzar();
                    if (caracterActual == '=') {
                        avanzar();
                        return new Token(Token.Tipo.EQEQ, "==");
                    }
                    return new Token(Token.Tipo.EQ, "=");
                case '<':
                    avanzar();
                    if (caracterActual == '=') {
                        avanzar();
                        return new Token(Token.Tipo.LTEQ, "<=");
                    }
                    if (caracterActual == '<') {
                        avanzar();
                        if (caracterActual == '=') {
                            avanzar();
                            return new Token(Token.Tipo.LTLTEQ, "<<=");
                        }
                        return new Token(Token.Tipo.LTLT, "<<");
                    }
                    return new Token(Token.Tipo.LT, "<");
                case '>':
                    avanzar();
                    if (caracterActual == '=') {
                        avanzar();
                        return new Token(Token.Tipo.GTEQ, ">=");
                    }
                    if (caracterActual == '>') {
                        avanzar();
                        if (caracterActual == '=') {
                            avanzar();
                            return new Token(Token.Tipo.GTGTEQ, ">>=");
                        }
                        return new Token(Token.Tipo.GTGT, ">>");
                    }
                    return new Token(Token.Tipo.GT, ">");
                case '{':
                    avanzar();
                    return new Token(Token.Tipo.LBRACE, "{");
                case '}':
                    avanzar();
                    return new Token(Token.Tipo.RBRACE, "}");
                case '[':
                    avanzar();
                    return new Token(Token.Tipo.LSQUARE, "[");
                case ']':
                    avanzar();
                    return new Token(Token.Tipo.RSQUARE, "]");
                case '(':
                    avanzar();
                    return new Token(Token.Tipo.LPAR, "(");
                case ')':
                    avanzar();
                    return new Token(Token.Tipo.RPAR, ")");
                case ';':
                    avanzar();
                    return new Token(Token.Tipo.SEMI, ";");
                case ':':
                    avanzar();
                    if (caracterActual == ':') {
                        avanzar();
                        return new Token(Token.Tipo.COLONCOLON, "::");
                    }
                    return new Token(Token.Tipo.COLON, ":");
                case ',':
                    avanzar();
                    return new Token(Token.Tipo.COMMA, ",");
                case '.':
                    avanzar();
                    if (caracterActual == '.' && entrada.charAt(posicion + 1) == '.') {
                        avanzar();
                        avanzar();
                        return new Token(Token.Tipo.ELLIPSIS, "...");
                    }
                    return new Token(Token.Tipo.DOT, ".");
                case '?':
                    avanzar();
                    return new Token(Token.Tipo.QUESTION, "?");
                case '\'':
                    return new Token(Token.Tipo.SQUOTE, caracter());
                case '\"':
                    avanzar();
                    StringBuilder cadena = new StringBuilder();
                    while (caracterActual != '\0' && caracterActual != '\"') {
                        cadena.append(caracterActual);
                        avanzar();
                    }
                    if (caracterActual == '\"') {
                        avanzar();
                        return new Token(Token.Tipo.STRING, cadena.toString());
                    }
                    throw new RuntimeException("Constante de cadena inválida");
                case '@':
                    avanzar();
                    return new Token(Token.Tipo.AT, "@");
                case '#':
                    avanzar();
                    return new Token(Token.Tipo.HASH, "#");
                case '$':
                    avanzar();
                    return new Token(Token.Tipo.DOLLAR, "$");
                case '_':
                    avanzar();
                    return new Token(Token.Tipo.UNDERSCORE, "_");
                default:
                    avanzar();
                    return new Token(Token.Tipo.INVALID, String.valueOf(caracterActual));
            }
        }
        return new Token(Token.Tipo.EOF, "");
    }
}
