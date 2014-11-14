package compiler.scanner;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represent a report of the scanning process.
 * 
 * @author jose
 */
public class LexicalAnalysisReport {
    
    /**
     * List of tokens read.
     */
    private final LexicalAnalysisResult tokenList;
    
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
     * Where the analysis tokens will be saved in the report.
     */
    private String reportTokens;
    
    /**
     * Where the analysis errors will be saved in the report.
     */
    private String reportErrors;
    
    /**
     * The full report content.
     */
    private String reportContent;
    
    /**
     * Instantiates a new AnalysisReport.
     * 
     * @param tokenList List of tokens read.
     */
    public LexicalAnalysisReport(LexicalAnalysisResult tokenList)
    {
        this.date = new Date();
        this.tokenList = tokenList;
        this.reportDateFormat = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.MEDIUM);
        this.generateReport();
    }
    
    /**
     * Generate the report header.
     * 
     * @param tokenCount The amount of tokens read.
     */
    private void generateReportHeaders()
    {
        StringBuilder sb = new StringBuilder();
        int tokenCount = this.tokenList.getTokensInfo().size();
        int errorCount = this.tokenList.getErrors().size();
        
        sb.append("┌ DATE: ").append(this.reportDateFormat.format(this.date)).append(System.lineSeparator());
        sb.append("├ TOKENS: ").append(tokenCount).append(System.lineSeparator());
        sb.append("└ ERRORS: ").append(errorCount).append(System.lineSeparator());
        
        this.reportHeaders = sb.toString();
    }
    
    /**
     * Generate the information of a certain token.
     * 
     * @param sb The string builder used to construct the report.
     * @param token The token that is being processed.
     */
    private void addTokenInfo(StringBuilder sb, TokenInfo token)
    {
        sb.append("Token: ").append(token.getToken()).append(System.lineSeparator());
        sb.append("Type: ").append(token.getType()).append(System.lineSeparator());
    }
    
    /**
     * Generate the information about the token lines.
     * 
     * @param sb The string builder used to construct the report.
     * @param lineList The list of lines of a certain token.
     */
    private void addTokenLinesInfo(StringBuilder sb, List<LineContainer> lineList)
    {
        Iterator<LineContainer> iterator = lineList.iterator();
        LineContainer line;
        int repetitions;
        
        sb.append("Lines: ");
        
        while (iterator.hasNext())
        {
            line = iterator.next();
            repetitions = line.getRepetitions();
            
            sb.append(line.getLine());
            if (repetitions > 1)
            {
                sb.append("(").append(repetitions).append(")");
            }
            if (iterator.hasNext())
            {
                sb.append(", ");
            }
        }
        
        sb.append(System.lineSeparator());
    }
    
    /**
     * Generate the part of the report where the tokens will be saved.
     */
    private void generateReportTokens()
    {
        Collection<TokenInfo> tokens = this.tokenList.getTokensInfo();
        if (!tokens.isEmpty())
        {
            StringBuilder sb = new StringBuilder();
            Iterator<TokenInfo> iterator = tokens.iterator();
            TokenInfo token;

            while (iterator.hasNext())
            {
                token = iterator.next();
                addTokenInfo(sb, token);
                addTokenLinesInfo(sb, token.getLines());

                if (iterator.hasNext())
                {
                    sb.append(System.lineSeparator());
                }
            }

            this.reportTokens = sb.toString();
        }
        else
        {
            this.reportTokens = "None";
        }
    }
    
    /**
     * Generate the part of the report where the scanning errors will be saved.
     */
    private void generateReportErrors()
    {
        Collection<Token> errors = this.tokenList.getErrors();
        if (!errors.isEmpty())
        {
            StringBuilder sb = new StringBuilder();

            for (Token token : errors)
            {
                sb.append("Token: ").append(token.getToken());
                sb.append(" at line ").append(token.getLine());
                sb.append(", column ").append(token.getColumn());
                sb.append(System.lineSeparator());
            }

            this.reportErrors = sb.toString();
        }
        else
        {
            this.reportErrors = "None";
        }
    }
    
    /**
     * Generate the full report.
     */
    private void generateReportContent()
    {
        StringBuilder sb = new StringBuilder();
        
        sb.append("# SCANNER REPORT #").append(System.lineSeparator());
        sb.append("==================").append(System.lineSeparator()).append(System.lineSeparator());
        sb.append(this.reportHeaders).append(System.lineSeparator());
        
        sb.append("* TOKEN LIST").append(System.lineSeparator());
        sb.append("------------").append(System.lineSeparator()).append(System.lineSeparator());
        sb.append(this.reportTokens).append(System.lineSeparator());
        
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
        this.generateReportTokens();
        this.generateReportErrors();
        this.generateReportContent();
    }
    
    /**
     * Write the report to a file.
     * 
     * @param filePath The path where the file will be saved.
     */
    public void writeToFile(String filePath)
    {
        Writer writer;

        try
        {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath), "utf-8"));
            writer.write(this.reportContent);
            writer.close();
        }
        catch (IOException ex)
        {
          Logger.getLogger(LexicalAnalysisReport.class.getName()).log(Level.SEVERE, null, ex);
        } 
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

    /**
     * Gets the report headers.
     * 
     * @return The report headers.
     */
    public String getReportHeaders()
    {
        return reportHeaders;
    }

    /**
     * Gets the report tokens.
     * 
     * @return The report tokens.
     */
    public String getReportTokens()
    {
        return reportTokens;
    }

    /**
     * Gets the report errors.
     * 
     * @return The report errors.
     */
    public String getReportErrors()
    {
        return reportErrors;
    }

    /**
     * Gets the full report content.
     * 
     * @return The report content.
     */
    public String getReportContent()
    {
        return reportContent;
    }
    
    @Override
    public String toString()
    {
        return this.reportContent;
    }

}
