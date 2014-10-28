package compiler;

import compiler.parser.Parser;

/**
 *
 * @author jose
 */
public class Compiler
{
    
    /**
     * The main method.
     * 
     * @param args The command line arguments. This is a list of files to analyze, separated by a space.
     */
    public static void main(String[] args)
    {
        String[] parserArgs = {"../samples/code.txt"};
        Parser.main(parserArgs);
        System.out.println("Ejecutado!");
    }
    
}
