package compiler.parser;

/**
 *
 * @author jose
 */
public class ArrayTableRow extends TableRow
{
    private final int size;
    private final int arrayType;

    public ArrayTableRow(String identifier, int type, int arrayType, int size)
    {
        super(identifier, type);
        this.arrayType = arrayType;
        this.size = size;
    }

    public int getSize()
    {
        return size;
    }

    public int getArrayType()
    {
        return arrayType;
    }
}
