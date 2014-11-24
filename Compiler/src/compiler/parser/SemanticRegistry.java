package compiler.parser;

/**
 *
 * @author jose
 */
public class SemanticRegistry
{
    protected String name;
    protected SemanticRegistryType type;
    protected Object value;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public SemanticRegistryType getType()
    {
        return type;
    }

    public void setType(SemanticRegistryType type)
    {
        this.type = type;
    }

    public Object getValue()
    {
        return value;
    }

    public void setValue(Object value)
    {
        this.value = value;
    }
}
