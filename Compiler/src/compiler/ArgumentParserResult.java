package compiler;

import java.io.File;
import java.util.List;
import net.sourceforge.argparse4j.inf.Namespace;

/**
 *
 * @author jose
 */
public class ArgumentParserResult
{
    private final String action;
    private final Boolean verbose;
    private final List<File> files;

    public ArgumentParserResult(Namespace ns)
    {
        this.action = (String) ns.get("action");
        this.verbose = (Boolean) ns.get("verbose");
        this.files = (List<File>) ns.get("files");
    }
    
    public String getAction()
    {
        return action;
    }

    public Boolean isVerbose()
    {
        return verbose;
    }
    
    public List<File> getFiles()
    {
        return files;
    }
    
}
