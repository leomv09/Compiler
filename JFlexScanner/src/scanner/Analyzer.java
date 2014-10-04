package scanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Lexical Scanner.
 * 
 * @author Leo
 */
public class Analyzer {
    
    /**
     * Create a file reader for a given file.
     * 
     * @param file The file that will be read.
     * @return The file reader.
     */
    private FileReader createFileReader(File file)
    {
        FileReader fr = null;
        
        try
        {
            fr = new FileReader(file);
        } 
        catch (FileNotFoundException ex)
        {
            Logger.getLogger(Analyzer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return fr;
    }
    
    /**
     * Scan a source code file.
     * 
     * @param filePath The path of the file.
     */
    public void analyze(String filePath)
    {
        File file = new File(filePath);
        FileReader fr = createFileReader(file);
        
        Lexer lexScanner = new Lexer(fr);
        Token token = null;
        
        do
        {
            try
            {
                token = lexScanner.nextToken();
            } 
            catch (IOException ex)
            {
                Logger.getLogger(Analyzer.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if (token != null)
            {
                System.out.println(token);
            }
        } while (token != null);
        
        AnalysisReport report = new AnalysisReport(lexScanner.getTokenList());
        report.writeToFile("output.txt");
    }
    
    /**
     * The main method.
     * 
     * @param args The command line arguments.
     */
    public static void main(String[] args)
    {
        Analyzer analyzer = new Analyzer();
        analyzer.analyze("samples/code1.txt");
    }

}
