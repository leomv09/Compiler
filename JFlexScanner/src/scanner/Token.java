package scanner;

import java_cup.runtime.Symbol;

/**
 * Represent a Token read by the scanner.
 * 
 * @author Leo
 */
public class Token extends Symbol{
    
    /**
     * The token category or type.
     */
    private final String type;
    
    /**
     * Instantiates a new Token.
     * 
     * @param token The token itself.
     * @param sym The token category or type.
     * @param line The line in which the token was read.
     * @param column The column in which the token was read.
     * @param type The name of token category or type
     */
    public Token (int sym, String type, String token, int line, int column)
    {
        super(sym, line, column, token);
        this.type = type;
    }

    /**
     * Get the token.
     * 
     * @return The token.
     */
    public String getToken()
    {
        return this.value.toString();
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
        return this.left;
    }

    /**
     * Get the token column.
     * 
     * @return The token column.
     */
    public int getColumn()
    {
        return this.right;
    }

    @Override
    public String toString()
    {
        return "Token {" + "token=" + this.value + ", type=" + type + ", line=" + this.left + ", column=" + this.right + '}';
    }
    
}
