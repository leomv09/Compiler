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
    
    public Object lookupSymbol(String identifier)
    {
        return table.get(identifier);
    }
}
