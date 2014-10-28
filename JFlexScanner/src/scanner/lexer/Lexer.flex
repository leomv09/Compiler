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

    private Token newToken(int type)
    {
        Token token = new Token(type, yytext(), yyline, yycolumn);
        tokenList.addToken(token);
        return token;
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
  return new Token(ParserSym.EOF, yytext(), yyline, yycolumn);
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

{STRING} { return newToken(ParserSym.STRING, String(yytext())); }

{IDENTIFICADORES} { return newToken(ParserSym.IDENTIFIER); }
 
"ARRAY" { return newToken(ParserSym.RESERVED_WORDS, String(yytext() ); }

"BEGIN" { return newToken(ParserSym.RESERVED_WORDS, String(yytext()); }

"BOOLEAN" { return newToken(ParserSym.RESERVED_WORDS, String(yytext()); }

"BYTE" { return newToken(ParserSym.RESERVED_WORDS, String(yytext()); }

"CASE" { return newToken(ParserSym.RESERVED_WORDS, String(yytext()); }

"CHAR" { return newToken(ParserSym.RESERVED_WORDS, String(yytext()); }

"CONST" { return newToken(ParserSym.RESERVED_WORDS, String(yytext()); }

"DO" { return newToken(ParserSym.RESERVED_WORDS, String(yytext()); }

"DOWNTO" { return newToken(ParserSym.RESERVED_WORDS, String(yytext()); }

"ELSE"  { return newToken(ParserSym.RESERVED_WORDS, String(yytext()); }

"END" { return newToken(ParserSym.RESERVED_WORDS, String(yytext()); }

"FALSE" { return newToken(ParserSym.RESERVED_WORDS, String(yytext()); }

"FILE" { return newToken(ParserSym.RESERVED_WORDS, String(yytext()); }

"FOR"  { return newToken(ParserSym.RESERVED_WORDS, String(yytext()); }

"FORWARD" { return newToken(ParserSym.RESERVED_WORDS, String(yytext()); }

"FUNCTION" { return newToken(ParserSym.RESERVED_WORDS, String(yytext()); }

"GOTO" { return newToken(ParserSym.RESERVED_WORDS, String(yytext()); }

"IF" { return newToken(ParserSym.RESERVED_WORDS, String(yytext()); }

"IN" { return newToken(ParserSym.RESERVED_WORDS, String(yytext()); }

"INLINE" { return newToken(ParserSym.RESERVED_WORDS, String(yytext()); }

"INT" { return newToken(ParserSym.RESERVED_WORDS, String(yytext()); }

"LABEL" { return newToken(ParserSym.RESERVED_WORDS, String(yytext()); }

"LONGINT" { return newToken(ParserSym.RESERVED_WORDS, String(yytext()); }

"NIL" { return newToken(ParserSym.RESERVED_WORDS, String(yytext()); }

"OF" { return newToken(ParserSym.RESERVED_WORDS, String(yytext()); }

"PACKED" { return newToken(ParserSym.RESERVED_WORDS, String(yytext()); }

"PROCEDURE" { return newToken(ParserSym.RESERVED_WORDS, String(yytext()); }

"PROGRAM" { return newToken(ParserSym.RESERVED_WORDS, String(yytext()); }

"READ" { return newToken(ParserSym.RESERVED_WORDS, String(yytext()); }

"REAL" { return newToken(ParserSym.RESERVED_WORDS, String(yytext()); }

"RECORD" { return newToken(ParserSym.RESERVED_WORDS, String(yytext()); }

"REPEAT" { return newToken(ParserSym.RESERVED_WORDS, String(yytext()); }

"SET" { return newToken(ParserSym.RESERVED_WORDS, String(yytext()); }

"SHORTINT" { return newToken(ParserSym.RESERVED_WORDS, String(yytext()); }

"STRING" { return newToken(ParserSym.RESERVED_WORDS, String(yytext()); }

"THEN" { return newToken(ParserSym.RESERVED_WORDS, String(yytext()); }

"TO" { return newToken(ParserSym.RESERVED_WORDS, String(yytext()); }

"TRUE" { return newToken(ParserSym.RESERVED_WORDS, String(yytext()); }

"TYPE" { return newToken(ParserSym.RESERVED_WORDS, String(yytext()); }

"UNTIL" { return newToken(ParserSym.RESERVED_WORDS, String(yytext()); }

"VAR" { return newToken(ParserSym.RESERVED_WORDS, String(yytext()); }

"WHILE" { return newToken(ParserSym.RESERVED_WORDS, String(yytext()); }

"WITH" { return newToken(ParserSym.RESERVED_WORDS, String(yytext()); }

"WRITE" { return newToken(ParserSym.RESERVED_WORDS, String(yytext()); }







"+" { return newToken(ParserSym.PLUS); }

"+=" { return newToken(ParserSym.PLUS_EQUALS); }

"-" { return newToken(ParserSym.MINUS); }

"-=" { return newToken(ParserSym.MINUS_EQUALS); }

"*" { return newToken(ParserSym.TIMES); }

"/" { return newToken(ParserSym.DIV); }

"/=" { return newToken(ParserSym.DIV_EQUALS); }

"*=" { return newToken(ParserSym.TIMES_EQUALS); }

"=" { return newToken(ParserSym.EQUALS); }

"<>" { return newToken(ParserSym.NOT_EQUALS); }

">" { return newToken(ParserSym.GREATER_THAN); }

"<" { return newToken(ParserSym.LESS_THAN); }

">=" { return newToken(ParserSym.GREATER_EQUALS_THAN); }

"<=" { return newToken(ParserSym.GREATER_LESS_THAN); }

"MOD" { return newToken(ParserSym.MOD); }

"NOT" { return newToken(ParserSym.NOT); }

"AND" { return newToken(ParserSym.AND); }

"OR" { return newToken(ParserSym.OR); }

"XOR" { return newToken(ParserSym.XOR); }

"<<" { return newToken(ParserSym.SHIFT_LEFT); }

">>" { return newToken(ParserSym.SHIFT_RIGHT); }

"<<=" { return newToken(ParserSym.SHIFT_LEFT_EQUALS); }

">>=" { return newToken(ParserSym.SHIFT_RIGHT_EQUALS); }

":=" { return newToken(ParserSym.ASSIGNMENT); }

"(" { return newToken(ParserSym.LPAREN); }

")" { return newToken(ParserSym.RPAREN); }

"[" { return newToken(ParserSym.LBRACKET); }

"]" { return newToken(ParserSym.RBRACKET); }

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