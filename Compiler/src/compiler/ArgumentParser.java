package compiler;

import java.io.File;
import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.impl.Arguments;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import net.sourceforge.argparse4j.inf.MutuallyExclusiveGroup;
import net.sourceforge.argparse4j.inf.Namespace;

/**
 *
 * @author jose
 */
public class ArgumentParser
{
    private final net.sourceforge.argparse4j.inf.ArgumentParser parser;
    
    public ArgumentParser()
    {
        this.parser = ArgumentParsers.newArgumentParser("Compiler")
            .description("compiler for a pascal like language.");
        
        this.parser.addArgument("files")
            .metavar("F")
            .type(File.class)
            .nargs("+")
            .help("file to process");
        
        this.parser.addArgument("--no-verbose")
            .dest("verbose")
            .action(Arguments.storeFalse())
            .help("don't print analysis information");
        
        MutuallyExclusiveGroup group = this.parser.addMutuallyExclusiveGroup();
        
        group.addArgument("-s", "--scan")
            .dest("action")
            .action(Arguments.storeConst())
            .setConst("scan")
            .help("perform a lexical analysis of the file(s)");
        
        group.addArgument("-p", "--parse")
            .dest("action")
            .action(Arguments.storeConst())
            .setConst("parse")
            .help("perform a syntactic analysis of the file(s)");
        
        group.addArgument("-c", "--compile")
            .dest("action")
            .action(Arguments.storeConst())
            .setConst("compile")
            .setDefault("compile")
            .help("compile the file(s) to assembly language [default]");
    }
    
    public ArgumentParserResult parse(String[] args)
    {
        ArgumentParserResult result = null;
        
        try
        {
            Namespace ns = this.parser.parseArgs(args);
            result = new ArgumentParserResult(ns);
        }
        catch (ArgumentParserException e)
        {
            this.parser.handleError(e);
            System.exit(1);
        }
        
        return result;
    }
    
}
