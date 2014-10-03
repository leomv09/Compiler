/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jflexscanner;

/**
 *
 * @author Leo
 * Class that helps to manage the line's count of a token.
 * For each token in the map, it has a line container list that has all the line containers.
 * The line containers contains the line number and the repetitions of a same token in the same line.
 * 
 */
public class LineContainer {
    
    private final int line;// Line in which the token is located.
    private int repetitions;//Amount of repetitions of the token on the same line.
    
    public LineContainer(int Line)
    {
        this.line = Line;
        this.repetitions = 1;
    }
    
    //Increments the amount of repetitions of a token in a line.
    public void incrementRepetitions()
    {
        this.repetitions++;
    }
    
    //Returns the amount of repetitions of a token in a line.
    public int getRepetitions()
    {
        return this.repetitions;
    }
    
    //Returns the line in which the token was found.
    public int getLine()
    {
        return this.line;
    }
    
    
    
}
