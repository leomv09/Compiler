package scanner;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author jose
 */
public class TokenInfo {
 
    /**
     * The token itself.
     */
    private final String token;
    
    /**
     * The token category or type.
     */
    private final String type;
    
    /**
     * List of lines where the token has appeared.
     */
    private final List<LineContainer> lines;
    
    /**
     * Instantiates a new TokenInfo.
     * 
     * @param token The token.
     */
    public TokenInfo(Token token)
    {
        this.token = token.getToken();
        this.type = token.getType();
        this.lines = new LinkedList();
        this.addApparition(token);
    }
    
    /**
     * Find a LineContainer by a line number.
     * 
     * @param line Line number.
     * @return LineContainer if found, else null.
     */
    private LineContainer findLine(int line)
    {
        for (LineContainer container : this.lines)
        {
            if (container.getLine() == line)
            {
                return container;
            }
        }
        
        return null;
    }
    
    /**
     * Add a new token apparition.
     * If no other token has been read in the token line, then a new LineContainer is added.
     * Else the current token column is added to the LineContainer.
     * 
     * @param token 
     */
    public final void addApparition(Token token)
    {
        // Find if already has a token in that line.
        LineContainer line = this.findLine(token.getLine());
        
        // If no other token has been readed in that line, add the current line.
        if (line == null)
        {
            line = new LineContainer(token.getLine());
            this.lines.add(line);
        }
        
        // Add the current column to the line.
        line.addColumn(token.getColumn());
    }
    
    /**
     * Get the list of LineContainer where the token has been read.
     * 
     * @return The list of LineContainer where the token has been read.
     */
    public List<LineContainer> getLines()
    {
        return this.lines;
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
    
}
