package compiler.parser;

/**
 *
 * @author jose
 */
public class SymbolTable
{
    private final SymbolTableScope globalScope;
    private SymbolTableScope currentScope;
    
    public SymbolTable()
    {
        this.globalScope = new SymbolTableScope(null);
        this.currentScope = this.globalScope;
    }
    
    public SymbolTableScope pushScope()
    { 
        this.currentScope = new SymbolTableScope(currentScope); 
        return this.currentScope; 
    }
    
    public SymbolTableScope popScope()
    { 
        this.currentScope = currentScope.getParentScope(); 
        return this.currentScope; 
    }
    
    public void declareSymbol(String identifier, TableRow row)
    { 
        if (currentScope.lookupSymbol(identifier) == null)
        {
            this.currentScope.enterSymbol(identifier, row);
        }
        else
        {
            System.err.println("Error: redclaring symbol " + identifier + ".");  
        }
    } 
    
    public Object lookupSymbol(String identifier)
    {
        SymbolTableScope lookupScope = this.currentScope;
        Object value = lookupScope.lookupSymbol(identifier);
 
        while (value == null)
        {
            lookupScope = lookupScope.getParentScope(); 
            if (lookupScope == null)
            { 
                System.err.println("Error (lookup): symbol '" + identifier + "' not declared."); 
                return null; 
            } 
            value = lookupScope.lookupSymbol(identifier); 
        }

        return value; 
    } 
    
}
