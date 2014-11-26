package compiler.parser;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author jose
 */
public class SymbolTable
{
    private final SymbolTableScope globalScope;
    private SymbolTableScope currentScope;
    private final List<SymbolTableScope> scopes;
    
    public SymbolTable()
    {
        this.scopes = new LinkedList();
        this.globalScope = new SymbolTableScope();
        this.currentScope = this.globalScope;
        this.scopes.add(this.globalScope);
    }
    
    public SymbolTableScope pushScope(String name)
    {
        this.currentScope = new SymbolTableScope(name, currentScope);
        this.scopes.add(this.currentScope);
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

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("--- SYMBOL TABLE ---").append(System.lineSeparator());
        
        for (SymbolTableScope scope : this.scopes)
        {
            sb.append(scope).append(System.lineSeparator());
        }
        
        return sb.toString();
    }
    
    
}
