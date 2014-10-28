package scanner.lexer;

import scanner.Token;
import scanner.TokenList;
import java_cup.runtime.*;

%%

%{
    private final TokenList tokenList;

    public TokenList getTokenList()
    {
        return this.tokenList;
    }

    private Token newToken(int type, Object value)
    {
        Token token = new Token(type, value, yyline, yycolumn);
        tokenList.addToken(token);
        return token;
    }
%}

%init{
    tokenList = new TokenList();
%init}

%public
%class Lexer
%unicode
%line
%column

// Enable CUP Compatibility Mode.
%implements java_cup.runtime.Scanner
%function next_token
%type Token
%eofval{
  return new Token(0, null, yyline, yycolumn);
%eofval}
%eofclose

PALABRASRESERVADAS = "ARRAY" | "BEGIN" | "BOOLEAN" | "BYTE" | "CASE" | "CHAR" | "CONST" | "DO" | "DOWNTO" | "ELSE" | "END" | "FALSE" | "FILE" | "FOR" | "FORWARD" | "FUNCTION" | "GOTO" | "IF" | "IN" | "INLINE" | "INT" | "LABEL" | "LONGINT" | "NIL" | "OF" | "PACKED" | "PROCEDURE" | "PROGRAM" | "READ" | "REAL" | "RECORD" | "REPEAT" | "SET" | "SHORTINT" | "STRING" | "THEN" | "TO" | "TRUE" | "TYPE" | "UNTIL" | "VAR" | "WHILE" | "WITH" | "WRITE"
OPERADORES = "," | ";" | "++" | "--" | ">=" | ">" | "<=" | "<" | "<>" | "=" | "+" | "-" | "*" | "/" | "(" | ")" | "[" | "]" | ":=" | "." | ":" | "+=" | "-=" | "*=" | "/=" | ">>" | "<<" | "<<=" | ">>=" | "NOT" | "OR" | "AND" | "XOR" | "DIV" | "MOD"

FIN_DE_LINEA = \r | \n | \r\n
ESPACIOS = {FIN_DE_LINEA} | [ \t\f]

EXP_ALPHA = [a-zA-Z]
EXP_NUMERICAS = [0-9]
EXP_ALPHA_NUMERICA = {EXP_ALPHA} | {EXP_NUMERICAS}

NUMERO_ENTERO_POSITIVO = ({EXP_NUMERICAS})+
NUMERO_ENTERO_NEGATIVO = "-"{NUMERO_ENTERO_POSITIVO}
NUMERO_ENTERO = {NUMERO_ENTERO_NEGATIVO} | {NUMERO_ENTERO_POSITIVO}
NUMERO_REAL = {NUMERO_ENTERO}"."{NUMERO_ENTERO_POSITIVO} ("E"{NUMERO_ENTERO})?

STRING = "'" ([^'])* "'"
CARACTER = "#"{NUMERO_ENTERO_POSITIVO} | "'" [^'] "'" 

IDENTIFICADORES = {EXP_ALPHA}({EXP_ALPHA_NUMERICA}){0, 126}
LITERALES = {NUMERO_REAL} | {NUMERO_ENTERO} | {CARACTER} | {STRING}

COMENTARIO_LINEA = "//" ([^\r\n])* {FIN_DE_LINEA}?
COMENTARIO_BLOQUE = "(*" [^*] ~"*)" | "(*" "*"+ ")" | "{" [^}] ~"}"
COMENTARIOS = {COMENTARIO_LINEA} | {COMENTARIO_BLOQUE}

%%

{ESPACIOS} {
    /*Ignore*/
}

{COMENTARIOS} {
    /*Ignore*/
}

{NUMERO_ENTERO} { return newToken(ParserSym.NUMBER, new Integer(yytext())); }
{NUMERO_REAL} { return newToken(ParserSym.REAL, new Double(yytext())); }
{CARACTER} { return newToken(ParserSym.CHARACTER, new Character(yytext())); }
{STRING} { return newToken(ParserSym.STRING, new String(yytext())); }
{IDENTIFICADORES} { return newToken(ParserSym.IDENTIFIER, new String(yytext())); }
{PALABRASRESERVADAS} { return newToken(ParserSym.RESERVED_WORDS, new String(yytext())); }

"+" { return newToken(ParserSym.PLUS, yytext()); }

"+=" { return newToken(ParserSym.PLUS_EQUALS, yytext()); }

"-" { return newToken(ParserSym.MINUS, yytext()); }

"-=" { return newToken(ParserSym.MINUS_EQUALS, yytext()); }

"*" { return newToken(ParserSym.TIMES, yytext()); }

"/" { return newToken(ParserSym.DIV, yytext()); }

"/=" { return newToken(ParserSym.DIV_EQUALS, yytext()); }

"*=" { return newToken(ParserSym.TIMES_EQUALS, yytext()); }

"=" { return newToken(ParserSym.EQUALS, yytext()); }

"MOD" { return newToken(ParserSym.MOD, yytext()); }

"<>" { return newToken(ParserSym.NOT_EQUALS, yytext()); }

">" { return newToken(ParserSym.GREATER_THAN, yytext()); }

"<" { return newToken(ParserSym.LESS_THAN, yytext()); }

">=" { return newToken(ParserSym.GREATER_EQUALS_THAN, yytext()); }

"<=" { return newToken(ParserSym.GREATER_LESS_THAN, yytext()); }

"NOT" { return newToken(ParserSym.NOT, yytext()); }

"AND" { return newToken(ParserSym.AND, yytext()); }

"OR" { return newToken(ParserSym.OR, yytext()); }

"XOR" { return newToken(ParserSym.XOR, yytext()); }

"<<" { return newToken(ParserSym.SHIFT_LEFT, yytext()); }

">>" { return newToken(ParserSym.SHIFT_RIGHT, yytext()); }

"<<=" { return newToken(ParserSym.SHIFT_LEFT_EQUALS, yytext()); }

">>=" { return newToken(ParserSym.SHIFT_RIGHT_EQUALS, yytext()); }

":=" { return newToken(ParserSym.ASSIGNMENT, yytext()); }

"(" { return newToken(ParserSym.LPAREN, yytext()); }
")" { return newToken(ParserSym.RPAREN, yytext()); }
"[" { return newToken(ParserSym.LBRACKET, yytext()); }
"]" { return newToken(ParserSym.RBRACKET, yytext()); }

/*

{PALABRASRESERVADAS} {
    Token token = new Token(0, yytext(), yyline, yycolumn);
    tokenList.addToken(token);
    return token;
}

{OPERADORES} {
    Token token = new Token(0, yytext(), yyline, yycolumn);
    tokenList.addToken(token);
    return token;
}

{LITERALES} {
    Token token = new Token(0, yytext(), yyline, yycolumn);
    tokenList.addToken(token);
    return token;
}

{IDENTIFICADORES} {
    Token token = new Token(0, yytext(), yyline, yycolumn);
    tokenList.addToken(token);
    return token;
}

*/

. {
    Token token = new Token(0, yytext(), yyline, yycolumn);
    tokenList.addError(token);
    return token;
}