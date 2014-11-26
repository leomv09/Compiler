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
    
    public Class getReturnDataType()
    {
        return TableRow.getDataType(this.getReturnType());
    }
    
    private String getParamInfo()
    {
        StringBuilder str = new StringBuilder();
        str.append("Parameters: ").append(System.lineSeparator());
        str.append("[").append(System.lineSeparator());
        for(TableRow row : this.parameters)
        {
            str.append(row).append(System.lineSeparator());
        }
        str.append("]");
        return str.toString();
    }
    
    @Override
    public String toString()
    {
        StringBuilder str = new StringBuilder();
        str.append("TableRow {" + "identifier=").append(identifier).append(", type=").append(this.getTypeName()).append(", value=").append(value).append('}').append(System.lineSeparator());
        str.append(this.getParamInfo());
        return str.toString();
    }
}
