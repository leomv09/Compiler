package compiler.parser;

import java.util.LinkedList;

/**
 *
 * @author jose
 */
public class SemanticStack
{
    private static SemanticStack instance;
    private final LinkedList<SemanticRegistry> stack;
    
    private SemanticStack()
    {
        this.stack = new LinkedList();
    }
    
    public void push(SemanticRegistry obj)
    {
        this.stack.addFirst(obj);
    }
    
    public SemanticRegistry pop()
    {
        return this.stack.removeFirst();
    }
    
    public SemanticRegistry search(SemanticRegistryType type)
    {
        for (SemanticRegistry registry : this.stack)
        {
            if (registry.getType() == type)
            {
                return registry;
            }
        }
        
        return null;
    }
    
    public static SemanticStack getInstance()
    {
        if (instance == null)
        {
            instance = new SemanticStack();
        }
        
        return instance;
    }
}
