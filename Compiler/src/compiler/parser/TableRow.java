package compiler.parser;

/**
 *
 * @author jose
 */
public class TableRow
{
    private final String identifier;
    private final int type;
    private boolean constant;
    protected Object value;

    public TableRow(String identifier, int type)
    {
        this.identifier = identifier;
        this.type = type;
        this.value = null;
    }
    
    public TableRow(String identifier, int type, Object value)
    {
        this(identifier, type);
        this.value = value;
    }
    
    public TableRow(String identifier, int type, Object value, boolean constant)
    {
        this(identifier, type, value);
        this.constant = constant;
    }
    
    public String getIdentifier()
    {
        return identifier;
    }

    public int getType()
    {
        return type;
    }

    public boolean isConstant()
    {
        return constant;
    }

    public void setConstant(boolean constant)
    {
        this.constant = constant;
    }

    public Object getValue()
    {
        return value;
    }

    public void setValue(Object value) throws Exception
    {
        if (!this.isConstant())
        {
            this.value = value;
        }
        else
        {
            throw new Exception("Can not change value of constant.");
        }
    }

    @Override
    public String toString()
    {
        return "TableRow{" + "identifier=" + identifier + ", type=" + type + ", value=" + value + '}';
    }
}