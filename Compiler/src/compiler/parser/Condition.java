/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler.parser;

import java.util.List;

/**
 *
 * @author Leo
 */
public class Condition {
    
    private final Object expr1;
    private final Object expr2;
    private final int operator;
    
    public Condition(Object expr1, int operator, Object expr2)
    {
        this.expr1 = expr1;
        this.operator = operator;
        this.expr2 = expr2;
    }
    
    public Condition(Object expr)
    {
        this.expr1 = expr;
        this.expr2 = null;
        this.operator = -1;
    }

    public Object getFirstExpr() {
        return expr1;
    }

    public Object getSecondExpr() {
        return expr2;
    }

    public int getOperator() {
        return operator;
    }
    
    
    /**
     * 
     * @return 
     */
    private String getNotAccomplishedConditionJump()
    {
        String jumpExpr = null;
        switch(this.operator)
        {
            case ParserSym.EQUALS: jumpExpr = "JNE";
                break;
            case ParserSym.NOT_EQUALS: jumpExpr = "JE";
                break;
            case ParserSym.GREATER_THAN: jumpExpr = "JLE";
                break;
            case ParserSym.LESS_THAN: jumpExpr = "JGE";
                break;
            case ParserSym.GREATER_EQUALS_THAN: jumpExpr = "JL";
                break;
            case ParserSym.LESS_EQUALS_THAN: jumpExpr = "JG";
                break;
            default: jumpExpr = "JNE";
        }
        
        return jumpExpr;
    }
    

    /**
     * Transforms the current condition to it's correspondient assembly code.
     * @return Returns the assembly code for the current condition expression.
     */
    public String getAssemblyCode()
    {
        StringBuilder res = new StringBuilder();
        
        if(this.expr1 instanceof List || this.expr2 instanceof List)
        {
            return res.append("Multiple condition list not supported yet!").append("\n").toString();
        }
        else if(this.expr2 != null)
        {
            res.append("MOV AX, ").append(this.expr1).append("\n");
            res.append("CMP AX, ").append(this.expr2).append("\n");
            res.append(this.getNotAccomplishedConditionJump());
            return res.toString();
        }
        else
        {
            res.append("MOV AX, ").append(this.expr1).append("\n");
            res.append("CMP AX, 0").append("\n");
            res.append(this.getNotAccomplishedConditionJump());
            return res.toString();
        }
    }
}
