package scanner;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Represent a container of {@link jflexscanner.Token}.
 * 
 * @author Leo
 */
public class TokenList {
    
    /**
     * Where the tokens are stored.
     */
    private final List<Token> tokenList;
    
    /**
     * Where the token lines are stored.
     */
    private final Map<String, List<LineContainer>> tokensLines;
    
    /**
     * Instantiates a new TokenList.
     */
    public TokenList()
    {
        this.tokenList = new LinkedList();
        this.tokensLines = new HashMap();
    }
    
    /**
     * Add a new token.
     * 
     * @param token The new token.
     */
    public void addToken(Token token)
    {
       this.tokenList.add(token);
    }
    
    /**
     * Find a LineContainer in a list that contains a specific line number.
     * 
     * @param linesList The list of LineContainer's.
     * @param line The line number to search.
     * 
     * @return The LineContainer if found, else null.
     */
    private LineContainer findLine(List<LineContainer> linesList, int line)
    {
        LineContainer currentContainer = null;
        
        for (LineContainer lineContainer : linesList)
        {
            if (lineContainer.getLine() == line)
            {
                currentContainer = lineContainer;
                break; 
            }
        }
        
        return currentContainer;
    }
    
    /**
     * Add a new list of LineContainer to the tokensLines and assign it to the given token.
     * 
     * @param token The token for which the list is created.
     */
    private void addTokenToMap(Token token)
    {
        LineContainer line = new LineContainer(token.getLine());
        List<LineContainer> lines = new LinkedList();
        lines.add(line);
        this.tokensLines.put(token.getToken(), lines);
    }
    
    /**
     * Adds a line to the lines container list of a token that is already in the map.
     * 
     * @param token The token that is being processed.
     */
    private void addLineToToken(Token token)
    {
        List<LineContainer> linesList = this.tokensLines.get(token.getToken()); //Obtains the linesList of the current token.
        LineContainer line = new LineContainer(token.getLine()); //New line container is created.
        linesList.add(line);
    }
    
    /**
     * Increment the counter for a line of a given token.
     * 
     * @param token The token for which the line counter is incremented.
     */
    private void incrementLineToToken(Token token)
    {
        List<LineContainer> linesList = this.tokensLines.get(token.getToken()); //Obtains the linesList of the current token.
        LineContainer line = findLine(linesList, token.getLine());
        line.incrementRepetitions();
    }
    
    /**
     * Iterate over all tokens and generate the tokenLines map.
     */
    public void generateTokenLines()
    {
        Collections.sort(this.tokenList);
        this.tokensLines.clear();
        
        int index = 0;
        Token previousToken = null;
        Token currentToken = null;
        
        while (index < this.tokenList.size())
        {
            previousToken = currentToken;
            currentToken = this.tokenList.get(index++);
            
            // If the token already existed.
            if (currentToken.equals(previousToken))
            {
                // If the current token line is equal to the previus token line then increment the counter.
                if (currentToken.getLine() == previousToken.getLine())
                {
                    incrementLineToToken(currentToken);
                }
                // Else add a LineContainer with the current line to the list.
                else
                {
                    addLineToToken(currentToken);
                }
            }
            // Else add the token to the map and create a new list of lines.
            else
            {
                addTokenToMap(currentToken);
            }
        }
    }

    /**
     * Get the first token readed of a certain token name.
     * 
     * @param tokenName The name of the token.
     * @return The first token readed of the given name.
     */
    public Token getFirstToken(String tokenName)
    {
        Token token = null;
        
        for (Token currentToken : this.tokenList)
        {
            if (currentToken.getToken().equals(tokenName))
            {
                token = currentToken;
                break;
            }
        }
        
        return token;
    }
    
    /**
     * Get the list of tokens readed.
     * 
     * @return The list of tokens readed.
     */
    public List<Token> getTokens()
    {
        return tokenList;
    }

    /**
     * Get the map of token lines.
     * 
     * @return The map of token lines.
     */
    public Map<String, List<LineContainer>> getTokensLines()
    {
        return tokensLines;
    }

}
