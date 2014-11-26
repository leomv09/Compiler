package compiler.parser;

/**
 *
 * @author jose
 */
public class TableRow
{
    protected final String identifier;
    protected final int type;
    protected boolean constant;
    protected Object value;
    
    public static Class getDataType(int type)
    {
        Class dataType;

        switch (type)
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
            default:
                dataType = Object.class;
                break;
        }

        return dataType;
    }

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
            throw new Exception("Constant values cannot be changed.");
        }
        else if (!this.getDataType().isInstance(value))
        {
            throw new Exception("Incompatible type for " + this.identifier
                    + " [EXPECTED=" + this.getDataType().getSimpleName()
                    + " - GOT=" + value.getClass().getSimpleName() + "].");
        }
        else
        {
            this.value = value;
        }
    }
    
    public Class getDataType()
    {
        return TableRow.getDataType(this.type);
    }
    
    public String getTypeName()
    {
        if (this.type > 0)
        {
            return ParserSym.terminalNames[this.type];
        }
        else
        {
            return "CONSTANT";
        }
    }

    @Override
    public String toString()
    {
        return identifier + " => type:" + this.getTypeName() + " value:" + value;
    }
}