package scanner.lexer;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    
    /**
     * Move a file to the current package.
     * 
     * @param filePath The path of the file.
     */
    private static void moveFile(String filePath)
    {
        File file = new File(filePath);
        
        if (file.exists())
        {
            String destinationPath = getDestinationPath(file);
            
            File newFile = new File(destinationPath);
            
            if (newFile.exists())
            {
                newFile.delete();
            }
            
            file.renameTo(newFile);
        }
    }
    
    /**
     * Get the absolute path of a file in the current package.
     * Example:
     *  file = ../../Foo.java
     *  package = java.util
     * 
     *   returns = java/util/Foo.java
     * 
     * @param file The file to obtain the path.
     * @return The absolute path of a file in the current package.
     */
    private static String getDestinationPath(File file)
    {
        Path currentPath = Paths.get("");
        String packageName = CupBuilder.class.getPackage().getName();
            
        StringBuilder sb = new StringBuilder();
        sb.append(currentPath.toAbsolutePath().toString()).append(File.separator);
        sb.append("src").append(File.separator);
        sb.append(packageName.replace(".", File.separator)).append(File.separator);
        sb.append(file.getName());
        
        return sb.toString();
    }
            
}
