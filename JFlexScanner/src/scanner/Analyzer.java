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

        //AnalysisReport report = new AnalysisReport(file, lexScanner.getTokenList(), lexScanner.getErrorList());
        //report.writeToFile();

        return scanner.getTokenList();
    }
    
    /**
     * Analyze a file.
     * 
     * @param file The file to analyze.
     * @param savingFilePath The path where the report will be saved.
     * @return The list of tokens read.
     */
    public AnalysisReport analyze(File file, String savingFilePath)
    {
        System.out.println("Analyzing \"" + file.getAbsolutePath() + "\"\n");
        FileReader fr = createFileReader(file);
        TokenList tokens = this.analyze(fr);
        AnalysisReport report = new AnalysisReport(tokens);
        System.out.println(report);
        report.writeToFile(savingFilePath);
        return report;
    }
    
    /**
     * Analyze a string.
     * 
     * @param input The string to analyze.
     * @param savingFilePath The path where the file will be saved.
     * @return The list of tokens read.
     */
    public AnalysisReport analyze(String input, String savingFilePath)
    {
        System.out.println("Analyzing Input String\n");
        StringReader sr = new StringReader(input);
        TokenList tokens = this.analyze(sr);
        AnalysisReport report = new AnalysisReport(tokens);
        System.out.println(report);
        report.writeToFile(savingFilePath);
        return report;
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
