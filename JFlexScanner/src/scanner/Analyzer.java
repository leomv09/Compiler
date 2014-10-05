package scanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import scanner.lexer.Lexer;

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
     * Analyze a reader.
     * 
     * @param reader
     * @return 
     */
    public TokenList analyze(Reader reader)
    {
        Lexer scanner = new Lexer(reader);
        Token token = null;
        
        do
        {
            try
            {
                token = scanner.nextToken();
            } 
            catch (IOException ex)
            {
                Logger.getLogger(Analyzer.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if (token != null)
            {
                // Do something with token here.
            }
        } while (token != null);
        
        System.out.println();

        return scanner.getTokenList();
    }
    
    /**
     * Analyze a file.
     * 
     * @param file The file to analyze.
     * @return The list of tokens read.
     */
    public TokenList analyze(File file)
    {
        FileReader fr = createFileReader(file);
        TokenList tokens = this.analyze(fr);
        return tokens;
    }
    
    /**
     * Analyze a string.
     * 
     * @param input The string to analyze.
     * @return The list of tokens read.
     */
    public TokenList analyze(String input)
    {
        StringReader sr = new StringReader(input);
        TokenList tokens = this.analyze(sr);
        return tokens;
    }
    
    
    
    /**
     * The main method.
     * 
     * @param args The command line arguments. This is a list of files to analyze, separated by a space.
     */
    public static void main(String[] args)
    {
        Analyzer analyzer = new Analyzer();
        File file;
        
        // Analize each file given in the command line arguments.
        for (String arg : args)
        {
            file = new File(arg);
            //analyzer.analyze(file);
        }
    }

}
