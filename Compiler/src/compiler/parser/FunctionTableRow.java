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
    
    public FunctionTableRow(String identifier, int type, int returnValue)
    {
        super(identifier, type);
        this.value = returnValue;
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
}
