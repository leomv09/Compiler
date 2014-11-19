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
        this.globalScope = new SymbolTableScope();
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
    
    public void declareSymbol(String identifier, TableRow row) throws Exception
    {
        if (!currentScope.haveSymbol(identifier))
        {
            this.currentScope.enterSymbol(identifier, row);
        }
        else
        {
            throw new Exception("Error: redclaring symbol " + identifier + ".");
        }
    } 
    
    public TableRow lookupSymbol(String identifier)
    {
        SymbolTableScope lookupScope = this.currentScope;
        TableRow value = lookupScope.lookupSymbol(identifier);
 
        while (value == null)
        {
            lookupScope = lookupScope.getParentScope(); 
            if (lookupScope == null)
            {
                return null; 
            }
            value = lookupScope.lookupSymbol(identifier); 
        }

        return value; 
    }
}
