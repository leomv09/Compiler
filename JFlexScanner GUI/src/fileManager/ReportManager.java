/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Leo
 */
public class ReportManager {
    
    /**
     * 
     * @param filePath The path where the file is saved.
     * @return A string with the file information.
     */
    public StringBuilder readReport(String filePath)
    {
        StringBuilder sb = new StringBuilder();
        try
        {
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line = reader.readLine();
                while (line != null)
                {
                    sb.append(line);
                } }
          return sb;
        }
        catch (Exception e)
        {
            Logger.getLogger(ReportManager.class.getName()).log(Level.SEVERE, null, e);
          return null;
        }
          }
    
}
