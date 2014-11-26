package compiler.parser;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author jose
 */
public class SymbolTableScope
{
    private final SymbolTableScope parent;
    private final Map<String, TableRow> table;
    private String scopeName;

    public SymbolTableScope()
    {
        this.parent = null;
        this.table = new HashMap();
        this.scopeName = null;
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
    
    @Override
    public String toString()
    {
        Iterator<Entry<String, TableRow>> iterator = this.table.entrySet().iterator();
        StringBuilder sb = new StringBuilder();
        TableRow row;
        
        while (iterator.hasNext())
        {
            row = iterator.next().getValue();
            sb.append(row).append(System.lineSeparator());
        }
        
        return sb.toString();
    }
}
