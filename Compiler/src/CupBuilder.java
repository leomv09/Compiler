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
        String path = "src/Parser.cup";
        args = new String[] {"-destdir", "src/compiler/parser", "-nowarn", path};
        
        try
        {
            java_cup.Main.main(args);
        } 
        catch (Exception ex)
        {
            Logger.getLogger(CupBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
            
}
