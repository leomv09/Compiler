package compiler.parser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author jose
 */
public class FileWriter
{
    private final File file;
    private final PrintWriter writer;
    
    public FileWriter(String path) throws IOException
    {
        this.file = new File(path);
        if (file.exists())
        {
            file.delete();
        }
        this.writer =  new PrintWriter(new BufferedWriter(new java.io.FileWriter(file, true)));
    }
    
    public void append(String content)
    {
        this.writer.println(content);
        this.writer.flush();
    }
}
