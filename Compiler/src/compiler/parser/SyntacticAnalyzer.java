package compiler.parser;

import compiler.scanner.Lexer;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jose
 */
public class SyntacticAnalyzer {
    
    private boolean generateCode;
    
    public SyntacticAnalyzer(boolean generateCode)
    {
        this.generateCode = generateCode;
    }
    
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
            Logger.getLogger(SyntacticAnalyzer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return fr;
    }
    
    public SyntacticAnalysisResult analyze(Reader reader)
    {
        SyntacticAnalysisResult result = null;
        
        try
        {
            Lexer scanner = new Lexer(reader);
            Parser parser = new Parser(scanner);
            parser.setGenerateCode(this.generateCode);
            parser.parse();
            result = parser.getResult();
        }
        catch (Exception ex)
        {
            Logger.getLogger(SyntacticAnalyzer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }
    
    public SyntacticAnalysisResult analyze(File file)
    {
        FileReader fr = createFileReader(file);
        SyntacticAnalysisResult result = this.analyze(fr);
        return result;
    }
    
    public SyntacticAnalysisResult analyze(String input)
    {
        StringReader sr = new StringReader(input);
        SyntacticAnalysisResult result = this.analyze(sr);
        return result;
    }

    public boolean isGenerateCode()
    {
        return generateCode;
    }

    public void setGenerateCode(boolean generateCode)
    {
        this.generateCode = generateCode;
    }
    
}
