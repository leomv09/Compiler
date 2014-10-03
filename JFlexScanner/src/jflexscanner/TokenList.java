/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jflexscanner;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Leo
 */
public class TokenList {
    
    private final Map <String, List<Yytoken>> listaTokens;
    
    public TokenList()
    {
        this.listaTokens = new HashMap();
    }
    
    public void addToken(Yytoken token)
    {
       String key = token.getToken();
       if(this.listaTokens.containsKey(key))
       {
           this.listaTokens.get(key).add(token);
       }
       else
       {
           List<Yytoken> listaToken = new LinkedList();
           listaToken.add(token);
           this.listaTokens.put(key, listaToken);
       }
    }
    
    public List<Yytoken> getTokenList(String token)
    {
        return this.listaTokens.get(token);
    }
    
    private void sortList(List<Yytoken> tokenLines)
    {
        //tokenLines.sort((Yytoken o1, Yytoken o2) -> o1.getLine() - o2.getLine()); JAVA 8 RULES!!
        tokenLines.sort(new Comparator<Yytoken>() {

            @Override
            public int compare(Yytoken o1, Yytoken o2)
            {
                int compareByToken = o1.getToken().compareTo(o2.getToken());
                
                if (compareByToken == 0)
                {
                    return o1.getLine() - o2.getLine();
                }
                else
                {
                    return compareByToken;
                }
            }
        });
    }
    
    private void getTokenLines(List<Yytoken> tokenLines)
    {
        sortList(tokenLines);
        int index = 0;
        int counter = 0;
        Yytoken currentToken = tokenLines.get(index);
        
        while(currentToken != null)
        {
            counter++;
            Yytoken nextToken = tokenLines.get(index + 1);
            if( nextToken != null)
            {
                if(currentToken == nextToken)
                {
                    
                }
            }
            
            
        }
        
        
    }
    
        
}
