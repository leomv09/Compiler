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
        this.constant = false;
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
        if (this.isConstant())
        {
            throw new Exception("Can not change value of constant.");
        }
        else if (!this.getDataType().isInstance(value))
        {
            throw new Exception("Incompatible type for " + this.identifier
                    + " [EXPECTED=" + this.getDataType().getSimpleName());
                    //+ " - GOT=" + value.getClass().getSimpleName() + "].");
        }
        else
        {
            this.value = value;
        }
    }
    
    public Class getDataType()
    {
        Class dataType = null;

        switch (this.type)
        {
            case ParserSym.INT:
            case ParserSym.LONGINT:
            case ParserSym.SHORTINT:
                dataType = Number.class;
                break;
            case ParserSym.REAL:
                dataType = Double.class;
                break;
            case ParserSym.STRING:
                dataType = String.class;
                break;
            case ParserSym.BYTE:
                dataType = Byte.class;
                break;
            case ParserSym.CHAR:
                dataType = Character.class;
                break;
            case ParserSym.BOOLEAN:
                dataType = Boolean.class;
                break;
        }

        return dataType;
    }
    
    public String getTypeName()
    {
        return ParserSym.terminalNames[this.type];
    }

    @Override
    public String toString()
    {
        return "TableRow{" + "identifier=" + identifier + ", type=" + type + ", value=" + value + '}';
    }
}