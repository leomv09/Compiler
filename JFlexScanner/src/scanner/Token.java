package scanner;

import java_cup.runtime.Symbol;
import scanner.lexer.ParserSym;

/**
 * Represent a Token read by the scanner.
 * 
 * @author Leo
 */
public class Token extends Symbol {
    
    /**
     * Instantiates a new Token.
     * 
     * @param token The token itself.
     * @param sym The token category or type.
     * @param line The line in which the token was read.
     * @param column The column in which the token was read.
     */
    public Token (int sym, Object token, int line, int column)
    {
        super(sym, line, column, token);
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
        return ParserSym.terminalNames[this.sym];
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
        return "Token {" + "token=" + this.value + ", type=" + this.getType() + ", line=" + this.left + ", column=" + this.right + '}';
    }
    
}
