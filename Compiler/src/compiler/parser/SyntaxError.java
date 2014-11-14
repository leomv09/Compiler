package compiler.parser;

import java_cup.runtime.Symbol;

/**
 *
 * @author jose
 */
public class SyntaxError
{
    
    private final String message;
    private final Symbol symbol;
    
    public SyntaxError(String message, Symbol symbol)
    {
        this.message = message;
        this.symbol = symbol;
    }

    public String getMessage()
    {
        return this.message;
    }

    public Object getSymbol()
    {
        return this.symbol;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        
        sb.append(message);
        sb.append(" [LAST TOKEN = ");
        sb.append(ParserSym.terminalNames[symbol.sym]);
        sb.append(" (");
        sb.append(symbol.left);
        sb.append(", ");
        sb.append(symbol.right);
        sb.append(")].");
        
        return sb.toString();
    }
    
}
