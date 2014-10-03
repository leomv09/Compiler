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
    
    private LineContainer findLine(List<LineContainer> linesList, int line)
    {
        LineContainer currentContainer = null;
        for(int i = 0; i < linesList.size(); i++)
        {
            currentContainer = linesList.get(i);
            if(linesList.get(i).getLine() == line)
            {
                break; 
            }
        }
        return currentContainer;
    }
    
    /*Adds a line to the lines container list of a token that is already in the map.
    * currentToken: The token that is being processed.
    * tokensList: The map that contains all the tokens that have been processed.
    */
    private void addLineToTokenInMap(Yytoken currentToken, Map<String, List<LineContainer>> tokensList)
    {
        List<LineContainer> linesList = tokensList.get(currentToken.getToken());//Obtains the linesList of the current token.
        LineContainer oldLine = findLine(linesList, currentToken.getLine());
        if(currentToken.getLine() == oldLine.getLine())//This means that the same token is in the same line.
        {
            oldLine.incrementRepetitions();//So, the repetitions of that line of that specific token is incremented.
        }
        else
        {//This means that the token is already in the map, but in a different line.
            LineContainer Newline = new LineContainer(currentToken.getLine());//New line container is created.
            linesList.add(Newline);//The new line container is added to the list of containers.
        }
        
    }
    
    private void getTokenLines(List<Yytoken> tokenLines)
    {
        sortList(tokenLines);
        
        int index = 0;
        
        Yytoken previousToken = null;
        Yytoken currentToken = tokenLines.get(index);
        
        Map<String, List<LineContainer>> tokensList = new HashMap();
        
    while(currentToken != null)
        {
            if(currentToken == previousToken)
            {
               addLineToTokenInMap(currentToken,tokensList);
            }
            else
            {
                List<LineContainer> linesList = tokensList.get(currentToken.getToken());
                if(linesList != null)//It means that the current token is already in the map.
                {
                    addLineToTokenInMap(currentToken,tokensList);
                }
                else//It means that the current token is not in the map.
                {
                    LineContainer Newline = new LineContainer(currentToken.getLine());//New line container is created.
                    List<LineContainer> lines = null;//A new list of line containers is created.
                    lines.add(Newline);//The new line is added to the list of lines.
                    tokensList.put(currentToken.getToken(), lines);// The new token is put into the map.
                    previousToken = currentToken;
                    currentToken = tokenLines.get(index++);
                }
                    
                    
            }
            
        }
        
        
    }
    
        
}
