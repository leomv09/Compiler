package compiler.scanner;

import compiler.Token;
import compiler.parser.ParserSym;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Lexical Scanner.
 * 
 * @author Leo
 */
public class LexicalAnalyzer {
    
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
            Logger.getLogger(LexicalAnalyzer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return fr;
    }
    
    /**
     * Analyze a reader.
     * 
     * @param reader
     * @return 
     */
    public LexicalAnalysisResult analyze(Reader reader)
    {
        Lexer scanner = new Lexer(reader);
        Token token = null;
        
        do
        {
            try
            {
                token = scanner.next_token();
            }
            catch (IOException ex)
            {
                Logger.getLogger(LexicalAnalyzer.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (token != null && token.sym != ParserSym.EOF);

        return scanner.getResult();
    }
    
    /**
     * Analyze a file.
     * 
     * @param file The file to analyze.
     * @return The list of tokens read.
     */
    public LexicalAnalysisResult analyze(File file)
    {
        FileReader fr = createFileReader(file);
        LexicalAnalysisResult tokens = this.analyze(fr);
        return tokens;
    }
    
    /**
     * Analyze a string.
     * 
     * @param input The string to analyze.
     * @return The list of tokens read.
     */
    public LexicalAnalysisResult analyze(String input)
    {
        StringReader sr = new StringReader(input);
        LexicalAnalysisResult tokens = this.analyze(sr);
        return tokens;
    }

}
