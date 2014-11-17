package compiler;

import compiler.parser.SyntacticAnalysisReport;
import compiler.parser.SyntacticAnalysisResult;
import compiler.parser.SyntacticAnalyzer;
import compiler.scanner.LexicalAnalysisReport;
import compiler.scanner.LexicalAnalysisResult;
import compiler.scanner.LexicalAnalyzer;
import java.io.File;

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
        ArgumentParser parser = new ArgumentParser();
        ArgumentParserResult res = parser.parse(args);
        
        switch (res.getAction())
        {
            case "scan" :
                runLexicalAnalysis(res);
                break;
            case "parse" :
                runSyntacticAnalysis(res);
                break;
            case "compile" :
                runCompilationProcess(res);
                break;
        }
    }
    
    private static void runLexicalAnalysis(ArgumentParserResult res)
    {
        LexicalAnalyzer analyzer = new LexicalAnalyzer();
        LexicalAnalysisResult result;
        
        for (File file : res.getFiles())
        {
            result = analyzer.analyze(file);
            
            if (res.isVerbose())
            {
                LexicalAnalysisReport report = new LexicalAnalysisReport(result);
                System.out.println(report);
            }
        }
    }
    
    private static void runSyntacticAnalysis(ArgumentParserResult res)
    {
        SyntacticAnalyzer analyzer = new SyntacticAnalyzer(false);
        SyntacticAnalysisResult result;
        
        for (File file : res.getFiles())
        {    
            result = analyzer.analyze(file);
            
            if (res.isVerbose())
            {
                SyntacticAnalysisReport report = new SyntacticAnalysisReport(result);
                System.out.println(report);
            }
        }
    }
    
    private static void runCompilationProcess(ArgumentParserResult res)
    {
        SyntacticAnalyzer analyzer = new SyntacticAnalyzer(true);
        SyntacticAnalysisResult result;
        
        for (File file : res.getFiles())
        {
            result = analyzer.analyze(file);
            
            if (res.isVerbose())
            {
                SyntacticAnalysisReport report = new SyntacticAnalysisReport(result);
                System.out.println(report);
            }
        }
    }
    
}