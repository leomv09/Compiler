package scanner;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Represent a container of {@link jflexscanner.Token}.
 * 
 * @author Leo
 */
public class TokenList {
    
    /**
     * Where the token lines are stored.
     */
    private final Map<String, TokenInfo> tokens;
    
    /**
     * Instantiates a new TokenList.
     */
    public TokenList()
    {
        this.tokens = new HashMap();
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
     * Get the map of tokens.
     * 
     * @return The map of token lines.
     */
    public Collection<TokenInfo> getTokens()
    {
        return tokens.values();
    }

}
