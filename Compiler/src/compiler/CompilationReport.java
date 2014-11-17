package compiler;

import compiler.parser.SyntacticAnalysisResult;
import compiler.parser.SyntaxError;
import compiler.scanner.LexicalAnalysisResult;
import compiler.scanner.Token;
import java.text.DateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 *
 * @author jose
 */
public class CompilationReport implements Report
{
    private final LexicalAnalysisResult r1;
    private final SyntacticAnalysisResult r2;
    private final SemanticAnalysisResult r3;
    
    /**
     * Date of the report.
     */
    private final Date date;
    
    /**
     * Date format for the report header.
     */
    private DateFormat reportDateFormat;
    
    /**
     * Headers of the report.
     */
    private String reportHeaders;
    
    /**
     * Where the analysis errors will be saved in the report.
     */
    private String reportErrors;
    
    /**
     * The full report content.
     */
    private String reportContent;
    
    public CompilationReport(LexicalAnalysisResult r1, SyntacticAnalysisResult r2, SemanticAnalysisResult r3)
    {
        this.r1 = r1;
        this.r2 = r2;
        this.r3 = r3;
        
        this.date = new Date();
        this.reportDateFormat = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.MEDIUM);
        this.generateReport();
    }
    
    private void generateReportHeaders()
    {
        StringBuilder sb = new StringBuilder();
        int errorCount = this.r1.getErrors().size() + this.r2.getErrors().size() + this.r3.getErrors().size();
        
        sb.append("┌ DATE: ").append(this.reportDateFormat.format(this.date)).append(System.lineSeparator());
        sb.append("└ ERRORS: ").append(errorCount).append(System.lineSeparator());
        
        this.reportHeaders = sb.toString();
    }
    
    private void generateReportErrors()
    {
        StringBuilder sb = new StringBuilder();
        
        if (r1.haveErrors())
        {
            sb.append("-- LEXICAL ERRORS").append(System.lineSeparator()).append(System.lineSeparator());
            sb.append(this.generatedLexicalErrors()).append(System.lineSeparator());
        }
        if (r2.haveErrors())
        {
            sb.append("-- SYNTACTIC ERRORS").append(System.lineSeparator()).append(System.lineSeparator());
            sb.append(this.generatedSyntacticErrors()).append(System.lineSeparator());
        }
        if (r3.haveErrors())
        {
            sb.append("-- SEMANTIC ERRORS").append(System.lineSeparator()).append(System.lineSeparator());
            sb.append(this.generatedSemanticErrors());
        }
        
        if (sb.length() == 0)
        {
            this.reportErrors = "None";
        }
        else
        {
            this.reportErrors = sb.toString();
        }
    }
    
    private String generatedLexicalErrors()
    {
        Collection<Token> errors = this.r1.getErrors();
        String result;
        
        if (!errors.isEmpty())
        {
            StringBuilder sb = new StringBuilder();
            int index = 1;
            
            for (Token token : errors)
            {
                sb.append(index++).append(". ");
                sb.append("Token: ").append(token.getToken());
                sb.append(" at line ").append(token.getLine());
                sb.append(", column ").append(token.getColumn());
                sb.append(System.lineSeparator());
            }

            result = sb.toString();
        }
        else
        {
            result = "None" + System.lineSeparator();
        }
        
        return result;
    }
    
    private String generatedSyntacticErrors()
    {
        List<SyntaxError> errors = this.r2.getErrors();
        String result;
        
        if (!errors.isEmpty())
        {
            StringBuilder sb = new StringBuilder();
            SyntaxError error;
            
            for (int i=0; i<errors.size(); i++)
            {
                error = errors.get(i);
                sb.append(i+1).append(". ").append(error).append(System.lineSeparator());
            }
            
            result = sb.toString();
        }
        else
        {
            result = "None" + System.lineSeparator();
        }
        
        return result;
    }
    
    private String generatedSemanticErrors()
    {
        List<String> errors = this.r3.getErrors();
        String result;
        
        if (!errors.isEmpty())
        {
            StringBuilder sb = new StringBuilder();
            String error;
            
            for (int i=0; i<errors.size(); i++)
            {
                error = errors.get(i);
                sb.append(i+1).append(". ").append(error).append(System.lineSeparator());
            }
            
            result = sb.toString();
        }
        else
        {
            result = "None" + System.lineSeparator();
        }
        
        return result;
    }
    
    /**
     * Generate the full report.
     */
    private void generateReportContent()
    {
        StringBuilder sb = new StringBuilder();
        
        sb.append("# COMPILATION REPORT #").append(System.lineSeparator());
        sb.append("======================").append(System.lineSeparator()).append(System.lineSeparator());
        sb.append(this.reportHeaders).append(System.lineSeparator());
        
        sb.append("* ERROR LIST").append(System.lineSeparator());
        sb.append("------------").append(System.lineSeparator()).append(System.lineSeparator());
        sb.append(this.reportErrors);
        
        this.reportContent = sb.toString();
    }
    
    /**
     * Generate the content of the report.
     */
    public final void generateReport()
    {
        this.generateReportHeaders();
        this.generateReportErrors();
        this.generateReportContent();
    }
    
    /**
     * Set a custom format for the date in the report.
     * 
     * @param dateFormat DateFormat for formatting the date.
     */
    public void setReportDateFormat(DateFormat dateFormat)
    {
        this.reportDateFormat = dateFormat;
    }
    
    @Override
    public String getReportHeaders()
    {
        return this.reportHeaders;
    }

    @Override
    public String getReportErrors()
    {
        return this.reportErrors;
    }

    @Override
    public String getReportResults()
    {
        return "";
    }

    @Override
    public String getReportContent()
    {
        return this.reportContent;
    }

    @Override
    public String toString()
    {
        return this.getReportContent();
    }
    
}
