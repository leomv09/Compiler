package compiler.parser;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author jose
 */
public class FunctionTableRow extends TableRow
{
    private List<TableRow> parameters;
    
    public FunctionTableRow(String identifier, int type, int returnType)
    {
        super(identifier, type);
        this.value = returnType;
        this.parameters = new LinkedList();
    }

    public void setParameters(List<TableRow> parameters)
    {
        this.parameters = parameters;
    }

    public List<TableRow> getParameters()
    {
        return parameters;
    }
    
    public int getReturnType()
    {
        return (int) this.value;
    }
}
