/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package file.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;
import scanner.gui.WindowScanner;

/**
 *
 * @author Leo
 */
public class FileManager {
    
    /**
     * Method that writes the content given to a file.
     * 
     * @param file The file to save.
     * @param fileContent The content of the file.
     */
    public void writeToFile(File file, String fileContent)
    {
        Writer writer;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
            writer.write(fileContent);
            writer.close();
        }
        catch (IOException ex)
        {
          Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    /**
     * Read the contents of a file.
     * 
     * @param file The file to read.
     * @return The contents of the file.
     */
    public String readFile(File file)
    {
        BufferedReader br;
        String result = null;
        try
        {
            br = new BufferedReader(new FileReader(file));
            try
            {
                StringBuilder sb = new StringBuilder();
                String line = br.readLine();
                
                while (line != null)
                {
                    sb.append(line);
                    sb.append(System.lineSeparator());
                    line = br.readLine();
                }
                result = sb.toString();
            }
            finally
            {
                br.close();
            }
        }
        catch (IOException ex)
        {
            Logger.getLogger(WindowScanner.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }
    
}
