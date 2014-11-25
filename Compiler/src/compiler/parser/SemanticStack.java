package compiler.parser;

import java.util.LinkedList;

/**
 *
 * @author jose
 */
public class SemanticStack
{
    private final LinkedList<SemanticRegistry> stack;
    
    public SemanticStack()
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
}
