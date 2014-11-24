package compiler.parser;

/**
 *
 * @author jose
 */
public class LabelGenerator
{
    private static LabelGenerator instance;
    private int count;
    
    private LabelGenerator()
    {
        this.count = 0;
    }
    
    public String getLabel(String labelName)    
    {
        return labelName + count++;
    }
    
    public static LabelGenerator getInstance()
    {
        if (instance == null)
        {
            instance = new LabelGenerator();
        }
        
        return instance;
    }
}
