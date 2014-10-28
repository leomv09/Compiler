package compiler;

import java.util.LinkedList;
import java.util.List;

/**
 * Class that helps to manage the line's count of a token.
 * For each token in the map, it has a line container list that has all the line containers.
 * The line containers contains the line number and the repetitions of a same token in the same line.
 * 
 * @author Leo 
 */
public class LineContainer {
    
    /**
     * Line in which the token is located.
     */
    private final int line;
    
    /**
     * Columns where the token appear in the line.
     */
    private final List<Integer> columns;
    
    /**
     * Instantiates a new LineContainer.
     * 
     * @param line The number of the line.
     */
    public LineContainer(int line)
    {
        this.line = line;
        this.columns = new LinkedList();
    }
    
    /**
     * Add a column to the current line.
     * 
     * @param column Column where the token appear in the line.
     */
    public void addColumn(int column)
    {
        this.columns.add(column);
    }
    
    /**
     * Gets the amount of repetitions of a token in the line.
     * 
     * @return The amount of repetitions of a token in the line.
     */
    public int getRepetitions()
    {
        return this.columns.size();
    }
    
    /**
     * Gets the line in which the token was found.
     * 
     * @return The line in which the token was found.
     */
    public int getLine()
    {
        return this.line;
    }
    
    /**
     * Gets the columns in which the token was found.
     * 
     * @return The columns in which the token was found.
     */
    public List<Integer> getColumns()
    {
        return this.columns;
    }
    
}
