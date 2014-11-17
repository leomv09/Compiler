package compiler.scanner;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Represent a container of {@link compiler.scanner.Token}.
 * 
 * @author Leo
 */
public class LexicalAnalysisResult {
    
    /**
     * Where the token info are stored.
     */
    private final Map<String, TokenInfo> tokenInfo;
    
    /**
     * Where the tokens are stored.
     */
    private final List<Token> tokenList;
    
    /**
     * Where the errors are stored.
     */
    private final List<Token> errors;
    
    /**
     * Instantiates a new TokenList.
     */
    public LexicalAnalysisResult()
    {
        this.tokenInfo = new HashMap();
        this.tokenList = new LinkedList();
        this.errors = new LinkedList();
    }
    
    /**
     * Add a new token.
     * 
     * @param token The new token.
     */
    public void addToken(Token token)
    {
        this.tokenList.add(token);
        
       // If the token has been read previously, add the current apparition.
       if (this.tokenInfo.containsKey(token.getToken()))
       {
           TokenInfo info = this.tokenInfo.get(token.getToken());
           info.addApparition(token);
       }
       // Else add a new token to the list.
       else
       {
           TokenInfo info = new TokenInfo(token);
           this.tokenInfo.put(token.getToken(), info);
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
     * @return The list of tokens.
     */
    public List<Token> getTokenList()
    {
        return this.tokenList;
    }

    /**
     * Get the tokens information.
     * 
     * @return The collection of tokens.
     */
    public Collection<TokenInfo> getTokensInfo()
    {
        return this.tokenInfo.values();
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
    
    /**
     * Get the last token read.
     * 
     * @return The last token read.
     */
    public Token getLastToken()
    {
        return this.tokenList.get(this.tokenList.size() - 1);
    }
    
    public boolean haveErrors()
    {
        return !this.errors.isEmpty();
    }

}
