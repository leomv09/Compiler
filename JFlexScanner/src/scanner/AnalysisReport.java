package scanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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
     * List of tokens with errors read.
     */
    private final ArrayList<Token> errorList;
    
    /**
     * File read by the analyzer.
     */
    private final File file;
    
    /**
     * Date of the report.
     */
    private final Date date;
    
    /**
     * Date format for the report header.
     */
    private DateFormat reportDateFormat;
    
    /**
     * Date format for the generated file name.
     */
    private DateFormat fileDateFormat;
    
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
     * @param file File read by the analyzer.
     * @param tokenList List of tokens read.
     * @param errorList List of tokens with errors.
     */
    public AnalysisReport(File file, TokenList tokenList, ArrayList<Token> errorList)
    {
        this.date = new Date();
        this.file = file;
        this.tokenList = tokenList;
        this.errorList = errorList;
        this.reportDateFormat = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.MEDIUM);
        this.fileDateFormat = new SimpleDateFormat("dd-MM-yy_HH:mm:ss:S");
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
        int tokenSize = this.tokenList.getTokens().size();
        
        sb.append("┌ FILE: ").append(this.file.getAbsolutePath()).append("\n");
        sb.append("├ DATE: ").append(this.reportDateFormat.format(this.date)).append("\n");
        sb.append("└ TOKENS: ").append(tokenSize).append("\n");
        
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
        StringBuilder sb = new StringBuilder();
        Collection<TokenInfo> tokens = this.tokenList.getTokens();
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
    
    /**
     * Generate the part of the report where the scanning errors will be saved.
     */
    private void generateReportErrors()
    {
        System.out.println("Generating error report...");
        StringBuilder sb = new StringBuilder();
 
        for(Token token : this.errorList)
        {
            sb.append("Error: ").append("Token: ").append(token.getToken()).append(", at ").append(" line: ").append(token.getLine()).append(", column: ").append(token.getColumn());
        }

        System.out.println(sb.toString());
        this.reportErrors = sb.toString();
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
        if (this.reportTokens.isEmpty())
        {
            sb.append("None").append("\n\n");
        }
        else
        {
            sb.append(this.reportTokens).append("\n");
        }
        
        sb.append("* ERROR LIST").append("\n");
        sb.append("------------").append("\n\n");
        if (this.reportErrors.isEmpty())
        {
            sb.append("None");
        }
        else
        {
            sb.append(this.reportErrors);
        }
        
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
     * @param filePath The path of the file.
     */
    public void writeToFile(String filePath)
    {
        try
        {
            try (PrintWriter out = new PrintWriter(filePath)) {
                out.println(this.toString());
                out.close();
            }
        }
        catch (FileNotFoundException ex)
        {
            System.out.println("Error al escribir en el archivo.");
            Logger.getLogger(AnalysisReport.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Write the report to a file with a predefined name.
     */
    public void writeToFile()
    {
        String fileName = "Scanner_Report_" + this.fileDateFormat.format(this.date)+".txt";
        this.writeToFile(fileName);
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
     * Set a custom format for the date in the generated file.
     * 
     * @param dateFormat DateFormat for formatting the date.
     */
    public void setFileDateFormat(DateFormat dateFormat)
    {
        this.fileDateFormat = dateFormat;
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
