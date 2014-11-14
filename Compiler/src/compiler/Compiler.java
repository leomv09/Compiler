package compiler;

import compiler.parser.SyntacticAnalysisReport;
import compiler.parser.SyntacticAnalysisResult;
import compiler.parser.SyntacticAnalyzer;
import compiler.scanner.LexicalAnalysisReport;
import compiler.scanner.LexicalAnalysisResult;
import compiler.scanner.LexicalAnalyzer;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
            try
            {
                //runLexicalAnalysis(arg);
                runSyntacticAnalysis(arg);
            } 
            catch (FileNotFoundException ex)
            {
                Logger.getLogger(Compiler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private static void runLexicalAnalysis(String filePath) throws FileNotFoundException
    {
        Reader reader = new FileReader(filePath);
        LexicalAnalyzer analyzer = new LexicalAnalyzer();
        LexicalAnalysisResult result = analyzer.analyze(reader);
        LexicalAnalysisReport report = new LexicalAnalysisReport(result);
        System.out.println(report);
    }
    
    private static void runSyntacticAnalysis(String filePath) throws FileNotFoundException
    {
        Reader reader = new FileReader(filePath);
        SyntacticAnalyzer analyzer = new SyntacticAnalyzer();
        SyntacticAnalysisResult result = analyzer.analyze(reader);
        SyntacticAnalysisReport report = new SyntacticAnalysisReport(result);
        System.out.println(report);
    }
    
}
