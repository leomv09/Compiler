package scanner.lexer;

import java.io.File;

/**
 * Class used to build the Lexer.java class from the Lexer.flex file.
 * 
 * @author Leo
 */
public class JFlexScanner {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        String path = "src/scanner/lexer/Lexer.flex";
        File file = new File(path);
        jflex.Main.generate(file);
    }
    
}
