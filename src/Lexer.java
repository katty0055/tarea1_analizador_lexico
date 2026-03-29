import java.util.*;
import java.util.regex.*;

public class Lexer {
    
    // Expresiones regulares para cada token 
    private static final Pattern LITERAL_CADENA = Pattern.compile("\".*?\"");  // ".*"
    private static final Pattern LITERAL_NUM = Pattern.compile("[0-9]+(\\.[0-9]+)?((e|E)(\\+|\\-)?[0-9]+)?");
    private static final Pattern PR_TRUE = Pattern.compile("true|TRUE");
    private static final Pattern PR_FALSE = Pattern.compile("false|FALSE"); 
    private static final Pattern PR_NULL = Pattern.compile("null|NULL");

    /**
     * Guarda el token reconocido y cuántos caracteres ocupó
     */
    private static class TokenMatch {
        private final Token token;
        private final int length;

        TokenMatch(Token token, int length) {
            this.token = token;
            this.length = length;
        }
    }

    /**
     * Analiza una línea de texto y devuelve los tokens encontrados.
     */
    public List<Token> analyzeLine(String line, int lineNumber) {
        List<Token> tokens = new ArrayList<>();
        int i = 0;
        int length = line.length();
        
        while (i < length) {
            char currentChar = line.charAt(i);
            
            // Saltar espacios en blanco
            if (Character.isWhitespace(currentChar)) {
                i++;
                continue;
            }
            
            // tokens de un carácter
            Token singleCharToken = getSingleCharToken(currentChar);
            if (singleCharToken != null) {
                tokens.add(singleCharToken);
                i++;
                continue;
            }
            
            // tokens de más de un carácter
            TokenMatch match = matchComplexToken(line, i);
            if (match != null) {
                tokens.add(match.token);
                i += match.length;
                continue;
            }
            
            // Error léxico
            if (lineNumber > 0) {
                System.err.println("Error léxico en línea " + lineNumber
                        + ", columna " + (i + 1)
                        + ": carácter no reconocido '" + currentChar + "'");
            } else {
                System.err.println("Error léxico: carácter no reconocido '" + currentChar
                        + "' en posición " + (i + 1));
            }
            return new ArrayList<>();
        }
        
        return tokens;
    }
    
    /**
     * Obtiene token para caracteres individuales
     */
    private Token getSingleCharToken(char c) {
        switch (c) {
            case '[': return Token.L_CORCHETE;
            case ']': return Token.R_CORCHETE;
            case '{': return Token.L_LLAVE;
            case '}': return Token.R_LLAVE;
            case ',': return Token.COMA;
            case ':': return Token.DOS_PUNTOS;
            default: return null;
        }
    }
    
    /**
     * Devuelve el patrón asociado a un token
     */
    private Pattern getPatternForToken(Token token) {
        switch (token) {
            case LITERAL_CADENA: return LITERAL_CADENA;
            case LITERAL_NUM:    return LITERAL_NUM;
            case PR_TRUE:        return PR_TRUE;
            case PR_FALSE:       return PR_FALSE;
            case PR_NULL:        return PR_NULL;
            default:             return null;
        }
    }
    
    /**
     * Devuelve token + longitud
     */
    private TokenMatch matchComplexToken(String line, int startPos) {
        String remaining = line.substring(startPos);

        Token[] orderedTokens = {
            Token.LITERAL_CADENA,
            Token.PR_TRUE,
            Token.PR_FALSE,
            Token.PR_NULL,
            Token.LITERAL_NUM
        };

        for (Token token : orderedTokens) {
            Pattern pattern = getPatternForToken(token);
            if (pattern != null) {
                Matcher matcher = pattern.matcher(remaining);
                if (matcher.lookingAt()) {
                    return new TokenMatch(token, matcher.end());
                }
            }
        }

        return null;
    }

    /**
     * Convierte la lista de tokens al formato de salida
     */
    public String tokensToString(String line, List<Token> tokens) {
        if (tokens.isEmpty()) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        int i = 0;

        while (i < line.length() && Character.isWhitespace(line.charAt(i))) {
            sb.append(line.charAt(i));
            i++;
        }

        for (int j = 0; j < tokens.size(); j++) {
            if (j > 0) {
                sb.append(" ");
            }
            sb.append(tokens.get(j).name());
        }

        return sb.toString();
    }
}