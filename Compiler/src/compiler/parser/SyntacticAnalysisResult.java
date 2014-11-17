package compiler.parser;

import java.util.LinkedList;
import java.util.List;
import java_cup.runtime.Symbol;

/**
 *
 * @author jose
 */
public class SyntacticAnalysisResult
{
    private final List<SyntaxError> errors;
    
    public SyntacticAnalysisResult()
    {
        this.errors = new LinkedList<>();
    }
    
    public void addError(String message, Symbol symbol)
    {
        this.errors.add(new SyntaxError(message, symbol));
    }
    
    public List<SyntaxError> getErrors()
    {
        return this.errors;
    }
    
    public boolean haveErrors()
    {
        return !this.errors.isEmpty();
    }
}
