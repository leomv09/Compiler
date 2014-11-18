package compiler.parser;

/**
 *
 * @author jose
 */
public class TableRow
{
    protected String identifier;
    protected int type;
    protected Object value;

    public TableRow(String identifier, int type)
    {
        this.identifier = identifier;
        this.type = type;
    }
    
    public String getIdentifier()
    {
        return identifier;
    }

    public void setIdentifier(String identifier)
    {
        this.identifier = identifier;
    }

    public int getType()
    {
        return type;
    }

    public void setType(int type)
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