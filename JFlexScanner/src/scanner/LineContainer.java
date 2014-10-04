package scanner;

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
     * Amount of repetitions of the token on the same line.
     */
    private int repetitions;
    
    /**
     * Instantiates a new LineContainer.
     * 
     * @param line The number of the line.
     */
    public LineContainer(int line)
    {
        this.line = line;
        this.repetitions = 1;
    }
    
    /**
     * Increments the amount of repetitions of a token in a line.
     */
    public void incrementRepetitions()
    {
        this.repetitions++;
    }
    
    /**
     * Gets the amount of repetitions of a token in the line.
     * 
     * @return The amount of repetitions of a token in the line.
     */
    public int getRepetitions()
    {
        return this.repetitions;
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
    
}
