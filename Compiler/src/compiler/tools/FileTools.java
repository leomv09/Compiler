package compiler.tools;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author jose
 */
public class FileTools
{
    /**
     * Move a file to the current package.
     * 
     * @param filePath The path of the file.
     * @param packageName The destination package.
     */
    public static void moveFileToPackage(String filePath, String packageName)
    {
        File file = new File(filePath);
        
        if (file.exists())
        {
            String destinationPath = getDestinationPath(file, packageName);
            
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
     * @param packageName The destination package.
     * @return The absolute path of a file in the current package.
     */
    private static String getDestinationPath(File file, String packageName)
    {
        Path currentPath = Paths.get("");
            
        StringBuilder sb = new StringBuilder();
        sb.append(currentPath.toAbsolutePath().toString()).append(File.separator);
        sb.append("src").append(File.separator);
        sb.append(packageName.replace(".", File.separator)).append(File.separator);
        sb.append(file.getName());
        
        return sb.toString();
    }
    
}
