package scanner;

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
public class AnalysisReport {
    
    /**
     * List of tokens read.
     */
    private final TokenList tokenList;
    
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
    public AnalysisReport(TokenList tokenList)
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
        int tokenCount = this.tokenList.getTokens().size();
        int errorCount = this.tokenList.getErrors().size();
        
        sb.append("┌ DATE: ").append(this.reportDateFormat.format(this.date)).append("\n");
        sb.append("├ TOKENS: ").append(tokenCount).append("\n");
        sb.append("└ ERRORS: ").append(errorCount).append("\n");
        
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
        sb.append("Token: ").append(token.getToken()).append("\n");
        sb.append("Type: ").append(token.getType()).append("\n");
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
        
        sb.append("\n");
    }
    
    /**
     * Generate the part of the report where the tokens will be saved.
     */
    private void generateReportTokens()
    {
        Collection<TokenInfo> tokens = this.tokenList.getTokens();
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
                    sb.append("\n");
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
                sb.append("\n");
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
        
        sb.append("# SCANNER REPORT #").append("\n");
        sb.append("==================").append("\n\n");
        sb.append(this.reportHeaders).append("\n");
        
        sb.append("* TOKEN LIST").append("\n");
        sb.append("------------").append("\n\n");
        sb.append(this.reportTokens).append("\n");
        
        sb.append("* ERROR LIST").append("\n");
        sb.append("------------").append("\n\n");
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
          Logger.getLogger(AnalysisReport.class.getName()).log(Level.SEVERE, null, ex);
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
