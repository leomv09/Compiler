package compiler.tools.cup;

import compiler.tools.FileTools;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class used to build the Parser.java and the ParserSym.java class from the Cup.cup file.
 * 
 * @author jose
 */
public class CupBuilder
{
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        String path = "src/compiler/tools/cup/Cup.cup";
        String[] cupArgs = {path};
        
        try
        {
            java_cup.Main.main(cupArgs);
            FileTools.moveFileToPackage("Parser.java", "compiler.parser");
            FileTools.moveFileToPackage("ParserSym.java", "compiler.parser");
        } 
        catch (Exception ex)
        {
            Logger.getLogger(CupBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
            
}
