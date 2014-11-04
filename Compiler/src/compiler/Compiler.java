package compiler;

import compiler.parser.Parser;
import compiler.scanner.Lexer;
import compiler.scanner.LexicalAnalysisReport;
import compiler.scanner.LexicalAnalyzer;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jose
 */
public class Compiler
{
    
    /**
     * The main method.
     * 
     * @param args The command line arguments. This is a list of files to analyze, separated by a space.
     */
    public static void main(String[] args)
    {
        for (String arg : args)
        {
            try (Reader reader = new FileReader(arg))
            {
                //runLexicalAnalysis(reader);
                runSyntacticAnalysis(reader);   
            }
            catch (FileNotFoundException ex)
            {
                System.err.println("ERROR: File Not Found.");
            }
            catch (IOException ex)
            {
                Logger.getLogger(Compiler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private static void runLexicalAnalysis(Reader reader)
    {
        LexicalAnalyzer analyzer = new LexicalAnalyzer();
        TokenList tokenList = analyzer.analyze(reader);
        LexicalAnalysisReport report = new LexicalAnalysisReport(tokenList);
        System.out.println(report);
    }
    
    private static void runSyntacticAnalysis(Reader reader)
    {
        try
        {
            Lexer scanner = new Lexer(reader);
            Parser parser = new Parser(scanner);
            Object result = parser.parse().value;
        }
        catch (Exception ex)
        {
            Logger.getLogger(Compiler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
