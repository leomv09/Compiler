package compiler;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author jose
 */
public class SemanticAnalysisResult 
{
    private final List<String> errors;
    
    public SemanticAnalysisResult()
    {
        this.errors = new LinkedList();
    }
    
    public void addError(String error)
    {
        this.errors.add(error);
    }
    
    public List<String> getErrors()
    {
        return this.errors;
    }
    
    public boolean haveErrors()
    {
        return !this.errors.isEmpty();
    }
    
}
