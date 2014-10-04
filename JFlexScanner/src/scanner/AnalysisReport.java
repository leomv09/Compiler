package scanner;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Represent a report of the compilation process.
 * 
 * @author jose
 */
public class AnalysisReport {
    
    /**
     * List of tokens read.
     */
    private final TokenList tokenList;
    
    /**
     * Format for the date.
     */
    private DateFormat dateFormat;
    
    /**
     * Content of the report.
     */
    private String reportContent;
    
    /**
     * Instantiates a new AnalysisReport.
     * 
     * @param tokenList List of tokens read.
     */
    public AnalysisReport(TokenList tokenList)
    {
        this.tokenList = tokenList;
        this.dateFormat = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.MEDIUM);
        generateReport();
    }
    
    /**
     * Generate the report header.
     * 
     * @param sb The string builder used to construct the report.
     * @param tokenCount The amount of tokens read.
     */
    private void addHeaders(StringBuilder sb, int tokenCount)
    {
        Date now = new Date();
        sb.append("# COMPILATION REPORT").append("\n");
        sb.append("# DATE: ").append(this.dateFormat.format(now)).append("\n");
        sb.append("# TOTAL TOKENS: ").append(tokenCount).append("\n");
        sb.append("\n");
    }
    
    /**
     * Generate the information of a certain token.
     * 
     * @param sb The string builder used to construct the report.
     * @param token The token that is being processed.
     */
    private void addTokenInfo(StringBuilder sb, Token token)
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
        sb.append("Lines: ");
        
        LineContainer line;
        for (int i=0; i<lineList.size(); i++)
        {
            line = lineList.get(i);
            int counter = line.getRepetitions();
            
            sb.append(line.getLine());
            if (counter > 1)
            {
                sb.append("(").append(counter).append(")");
            }
            if (i+1 < lineList.size())
            {
                sb.append(", ");
            }
        }
    }
    
    /**
     * Generate the content of the report.
     */
    public final void generateReport()
    {
        StringBuilder sb = new StringBuilder();
        List<Token> tokens = this.tokenList.getTokens();
        Map<String, List<LineContainer>> lines = this.tokenList.getTokensLines();
        
        addHeaders(sb, tokens.size());
        
        for (Map.Entry<String, List<LineContainer>> entry : lines.entrySet())
        {
            String key = entry.getKey();
            List<LineContainer> lineList = entry.getValue();
            Token token = this.tokenList.getFirstToken(key);
            
            addTokenInfo(sb, token);
            addTokenLinesInfo(sb, lineList);
            sb.append("\n\n");
        }
        
        this.reportContent = sb.toString();
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
                out.println(this.reportContent);
            }
        }
        catch (FileNotFoundException ex)
        {
            Logger.getLogger(AnalysisReport.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Set a custom format for the date.
     * 
     * @param dateFormat DateFormat for formatting the date.
     */
    public void setDateFormat(DateFormat dateFormat)
    {
        this.dateFormat = dateFormat;
    }

    @Override
    public String toString()
    {
        return reportContent;
    }

}
