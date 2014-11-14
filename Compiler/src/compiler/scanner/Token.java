package compiler.scanner;

import compiler.parser.ParserSym;
import java_cup.runtime.Symbol;

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
     * @param type The token category or type.
     * @param line The line in which the token was read.
     * @param column The column in which the token was read.
     */
    public Token (int type, Object token, int line, int column)
    {
        super(type, line + 1, column, token);
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
        StringBuilder sb = new StringBuilder();
        
        sb.append("Token {");
        sb.append("token=").append(this.getToken());
        sb.append(", type=").append(this.getType());
        sb.append(", line=").append(this.getLine());
        sb.append(", column=").append(this.getColumn());
        sb.append("}");
        
        return sb.toString();
    }
    
}
