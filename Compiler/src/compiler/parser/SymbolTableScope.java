package compiler.parser;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author jose
 */
public class SymbolTableScope
{
    private final SymbolTableScope parent;
    private final Map<String, TableRow> table;

    public SymbolTableScope()
    {
        this.parent = null;
        this.table = new HashMap();
    }
    
    public SymbolTableScope(SymbolTableScope parent)
    {
        this.parent = parent;
        this.table = new HashMap();
    }
    
    public SymbolTableScope getParentScope()
    {
        return this.parent;
    }
    
    public void enterSymbol(String identifier, TableRow row)
    {
        this.table.put(identifier, row);
    }
    
    public TableRow lookupSymbol(String identifier)
    {
        return this.table.get(identifier);
    }
    
    public boolean haveSymbol(String identifier)
    {
        return this.table.containsKey(identifier);
    }
}
