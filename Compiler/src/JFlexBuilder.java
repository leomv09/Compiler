import java.util.logging.Level;
import java.util.logging.Logger;
import jflex.SilentExit;

/**
 * Class used to build the Lexer.java class from the Lexer.flex file.
 * 
 * @author Leo
 */
public class JFlexBuilder {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        String path = "src/Lexer.flex";
        args = new String[] {"-d", "src/compiler/scanner", path};
        
        try
        {
            jflex.Main.generate(args);
        }
        catch (SilentExit ex)
        {
            Logger.getLogger(JFlexBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
