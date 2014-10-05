/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileManager;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Leo
 */
public class FileWriter {
    
 
    
    /**
     * Method that writes the content given to a file.
     * 
     * @param filePath The path where the file will be saved.
     * @param fileContent The content of the file.
     */
    public void writeToFile(String filePath, String fileContent)
    {
        Writer writer;
        
        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                  new FileOutputStream(filePath), "utf-8"));
            writer.write(fileContent);
            try 
            {
                writer.close();
            } catch (Exception ex2) {
                Logger.getLogger(FileWriter.class.getName()).log(Level.SEVERE, null, ex2);
            }
        } catch (IOException ex) {
          Logger.getLogger(FileWriter.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
}
