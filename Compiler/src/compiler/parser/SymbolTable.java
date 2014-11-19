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
    
    public void declareSymbol(TableRow row) throws Exception
    {
        String identifier = row.getIdentifier();
        
        if (!currentScope.haveSymbol(identifier))
        {
            this.currentScope.enterSymbol(identifier, row);
        }
        else
        {
            throw new Exception("Error: redclaring symbol " + identifier + ".");
        }
    } 
    
    public TableRow lookupSymbol(String identifier) throws Exception
    {
        SymbolTableScope lookupScope = this.currentScope;
        TableRow value = lookupScope.lookupSymbol(identifier);
 
        while (value == null)
        {
            lookupScope = lookupScope.getParentScope(); 
            if (lookupScope == null)
            {
                throw new Exception("Symbol " + identifier + " not declared.");
            }
            value = lookupScope.lookupSymbol(identifier); 
        }

        return value; 
    }
}
