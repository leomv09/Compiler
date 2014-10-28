package compiler;

import java.util.Collection;
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
    private final Map<String, TokenInfo> tokens;
    
    /**
     * Where the errors are stored.
     */
    private final List<Token> errors;
    
    /**
     * Instantiates a new TokenList.
     */
    public TokenList()
    {
        this.tokens = new HashMap();
        this.errors = new LinkedList();
    }
    
    /**
     * Add a new token.
     * 
     * @param token The new token.
     */
    public void addToken(Token token)
    {
       // If the token has been read previously, add the current apparition.
       if (this.tokens.containsKey(token.getToken()))
       {
           TokenInfo info = this.tokens.get(token.getToken());
           info.addApparition(token);
       }
       // Else add a new token to the list.
       else
       {
           TokenInfo info = new TokenInfo(token);
           this.tokens.put(token.getToken(), info);
       }
    }
    
    /**
     * Add a new error
     * 
     * @param token The new token error.
     */
    public void addError(Token token)
    {
        this.errors.add(token);
    }

    /**
     * Get the tokens.
     * 
     * @return The collection of tokens.
     */
    public Collection<TokenInfo> getTokens()
    {
        return this.tokens.values();
    }
    
    /**
     * Get the errors.
     * 
     * @return The list of errors.
     */
    public List<Token> getErrors()
    {
        return this.errors;
    }

}
