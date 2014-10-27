package scanner.lexer;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
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
        String path = "src/scanner/lexer/Cup.cup";
        String[] cupArgs = {path};
        
        try
        {
            java_cup.Main.main(cupArgs);
            moveFile("Parser.java");
            moveFile("ParserSym.java");
        } 
        catch (Exception ex)
        {
            Logger.getLogger(CupBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void moveFile(String path)
    {
        File file = new File(path);
        
        if (file.exists())
        {
            Path currentPath = Paths.get("");
            
            StringBuilder sb = new StringBuilder();
            sb.append(currentPath.toAbsolutePath().toString()).append(File.separator);
            sb.append("src").append(File.separator);
            sb.append("scanner").append(File.separator);
            sb.append("lexer").append(File.separator);
            sb.append(file.getName());
            String destinationPath = sb.toString();
            
            File oldFile = new File(destinationPath);
            oldFile.delete();
            file.renameTo(new File(destinationPath));
        }
    }
    
}
