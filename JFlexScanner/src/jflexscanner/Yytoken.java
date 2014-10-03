/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jflexscanner;


/**
 *
 * @author Leo
 */
public class Yytoken {
    
    //Atributo
    private final String token;
    private final String tipoToken;
    private final int line;
    private final int column;
    
     //Constructor
    public Yytoken (String token, String tipoToken, int line, int column)
    {
        this.token = token;
        this.tipoToken = tipoToken;
        this.line = line;
        this.column = column;
    }

    public String getToken() {
        return token;
    }

    public String getTipoToken() {
        return tipoToken;
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    } 
    
}
