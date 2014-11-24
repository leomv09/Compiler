package compiler.parser;

/**
 *
 * @author jose
 */
public class IfSemanticRegistry extends SemanticRegistry
{
    private final String labelElse;
    private final String labelExit;
    
    public IfSemanticRegistry()
    {
        this.type = SemanticRegistryType.IF;
        this.labelElse = LabelGenerator.getInstance().getLabel("label-else");
        this.labelExit = LabelGenerator.getInstance().getLabel("label-exit");
    }

    public String getLabelElse()
    {
        return labelElse;
    }

    public String getLabelExit()
    {
        return labelExit;
    }
}
