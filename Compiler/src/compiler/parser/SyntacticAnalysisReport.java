package compiler.parser;

import compiler.Report;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author jose
 */
public class SyntacticAnalysisReport implements Report
{
    private final SyntacticAnalysisResult result;
    
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
    
    public SyntacticAnalysisReport(SyntacticAnalysisResult result)
    {
        this.date = new Date();
        this.result = result;
        this.reportDateFormat = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.MEDIUM);
        this.generateReport();
    }

    private void generateReportHeaders()
    {
        StringBuilder sb = new StringBuilder();
        int errorCount = this.result.getErrors().size();
        
        sb.append("┌ DATE: ").append(this.reportDateFormat.format(this.date)).append(System.lineSeparator());
        sb.append("└ ERRORS: ").append(errorCount).append(System.lineSeparator());
        
        this.reportHeaders = sb.toString();
    }
    
    private void generateReportErrors()
    {
        List<SyntaxError> errors = this.result.getErrors();
        
        if (!errors.isEmpty())
        {
            StringBuilder sb = new StringBuilder();
            SyntaxError error;
            
            for (int i=0; i<errors.size(); i++)
            {
                error = errors.get(i);
                sb.append(i+1).append(". ").append(error).append(System.lineSeparator());
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
        
        sb.append("# PARSER REPORT #").append(System.lineSeparator());
        sb.append("=================").append(System.lineSeparator()).append(System.lineSeparator());
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
    
    /**
     * Gets the report headers.
     * 
     * @return The report headers.
     */
    @Override
    public String getReportHeaders()
    {
        return reportHeaders;
    }
    
    /**
     * Gets the report errors.
     * 
     * @return The report errors.
     */
    @Override
    public String getReportErrors()
    {
        return reportErrors;
    }

    @Override
    public String getReportResults()
    {
        return "";
    }
    
    /**
     * Gets the full report content.
     * 
     * @return The report content.
     */
    @Override
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
