package compiler;

/**
 *
 * @author jose
 */
public interface Report
{
    /**
     * Gets the report headers.
     * 
     * @return The report headers.
     */
    public String getReportHeaders();
    
    /**
     * Gets the report errors.
     * 
     * @return The report errors.
     */
    public String getReportErrors();
    
    /**
     * Gets the report results.
     * 
     * @return The report content.
     */
    public String getReportResults();

    /**
     * Gets the full report content.
     * 
     * @return The report content.
     */
    public String getReportContent();
}
