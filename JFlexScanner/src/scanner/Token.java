package scanner;

import java.util.Objects;


/**
 * Represent a Token read by the scanner.
 * 
 * @author Leo
 */
public class Token implements Comparable<Token> {
    
    /**
     * The token itself.
     */
    private final String token;
    
    /**
     * The token category or type.
     */
    private final String type;
    
    /**
     * The line in which the token was read.
     */
    private final int line;
    
    /**
     * The column in which the token was read.
     */
    private final int column;
    
    /**
     * Instantiates a new Token.
     * 
     * @param token The token itself.
     * @param type The token category or type.
     * @param line The line in which the token was read.
     * @param column The column in which the token was read.
     */
    public Token (String token, String type, int line, int column)
    {
        this.token = token;
        this.type = type;
        this.line = line;
        this.column = column;
    }

    /**
     * Get the token.
     * 
     * @return The token.
     */
    public String getToken()
    {
        return token;
    }

    /**
     * Get the token type.
     * 
     * @return The token type.
     */
    public String getType()
    {
        return type;
    }

    /**
     * Get the token line.
     * 
     * @return The token line.
     */
    public int getLine()
    {
        return line;
    }

    /**
     * Get the token column.
     * 
     * @return The token column.
     */
    public int getColumn()
    {
        return column;
    }

    @Override
    public int hashCode()
    {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.token);
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Token other = (Token) obj;
        return Objects.equals(this.token, other.token);
    }

    @Override
    public int compareTo(Token o) {
        int compareByToken = this.token.compareTo(o.getToken());
        
        if (compareByToken == 0)
        {
            return this.line - o.getLine();
        }
        else
        {
            return compareByToken;
        }
    }

    @Override
    public String toString()
    {
        return "Token {" + "token=" + token + ", type=" + type + ", line=" + line + ", column=" + column + '}';
    }
    
}
