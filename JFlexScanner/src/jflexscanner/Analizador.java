/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jflexscanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Leo
 */
public class Analizador {
    
    private File openFile(String nombreArchivo)
    {
        File archivo = new File(nombreArchivo);
        return archivo;
    }
    
    private FileReader createFileReader(File archivo)
    {
        FileReader fr = null;
        try
        {
            fr = new FileReader(archivo);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Analizador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fr;
    }
    
    public void analizar(String rutaArchivo)
    {
        File archivo = openFile(rutaArchivo);
        FileReader fr = createFileReader(archivo);
        
        Yylex sampleLex = new Yylex(fr);
        Yytoken token = null;
        
        do
        {
            try
            {
                token = sampleLex.nextToken();
            } catch (IOException ex) {
                Logger.getLogger(Analizador.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if (token != null)
            {
                // Escribir token en algun lado.
            }
        } while (token != null);
    }

}
