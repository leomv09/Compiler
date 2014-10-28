package compiler.tools.jflex;

import compiler.tools.FileTools;
import java.io.File;

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
        String path = "src/compiler/tools/jflex/Lexer.flex";
        File file = new File(path);
        jflex.Main.generate(file);
        FileTools.moveFileToPackage("src/compiler/tools/jflex/Lexer.java", "compiler.scanner");
    }
    
}
