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

{CARACTER} { 
    String text = yytext();
    Character c = null;

    // El caracter es de tipo #65, #97, etc..
    if (text.startsWith("#"))
    {
        int code = Integer.valueOf(text.substring(1));
        c = Character.toChars(code)[0];
    }
    // El caracter es de tipo 'A', 'b', etc...
    else
    {
        c = new Character(text.charAt(1));
    }

    return newToken(ParserSym.CHARACTER, c);
}

{STRING} { return newToken(ParserSym.STRING); }

{IDENTIFICADORES} { return newToken(ParserSym.IDENTIFIER); }
 
"ARRAY" { return newToken(ParserSym.RESERVED_WORDS); }

"BEGIN" { return newToken(ParserSym.RESERVED_WORDS); }

"BOOLEAN" { return newToken(ParserSym.RESERVED_WORDS); }

"BYTE" { return newToken(ParserSym.RESERVED_WORDS); }

"CASE" { return newToken(ParserSym.RESERVED_WORDS); }

"CHAR" { return newToken(ParserSym.RESERVED_WORDS); }

"CONST" { return newToken(ParserSym.RESERVED_WORDS); }

"DO" { return newToken(ParserSym.RESERVED_WORDS); }

"DOWNTO" { return newToken(ParserSym.RESERVED_WORDS); }

"ELSE"  { return newToken(ParserSym.RESERVED_WORDS); }

"END" { return newToken(ParserSym.RESERVED_WORDS); }

"FALSE" { return newToken(ParserSym.RESERVED_WORDS); }

"FILE" { return newToken(ParserSym.RESERVED_WORDS); }

"FOR"  { return newToken(ParserSym.RESERVED_WORDS); }

"FORWARD" { return newToken(ParserSym.RESERVED_WORDS); }

"FUNCTION" { return newToken(ParserSym.RESERVED_WORDS); }

"GOTO" { return newToken(ParserSym.RESERVED_WORDS); }

"IF" { return newToken(ParserSym.RESERVED_WORDS); }

"IN" { return newToken(ParserSym.RESERVED_WORDS); }

"INLINE" { return newToken(ParserSym.RESERVED_WORDS); }

"INT" { return newToken(ParserSym.RESERVED_WORDS); }

"LABEL" { return newToken(ParserSym.RESERVED_WORDS); }

"LONGINT" { return newToken(ParserSym.RESERVED_WORDS); }

"NIL" { return newToken(ParserSym.RESERVED_WORDS); }

"OF" { return newToken(ParserSym.RESERVED_WORDS); }

"PACKED" { return newToken(ParserSym.RESERVED_WORDS); }

"PROCEDURE" { return newToken(ParserSym.RESERVED_WORDS); }

"PROGRAM" { return newToken(ParserSym.RESERVED_WORDS); }

"READ" { return newToken(ParserSym.RESERVED_WORDS); }

"REAL" { return newToken(ParserSym.RESERVED_WORDS); }

"RECORD" { return newToken(ParserSym.RESERVED_WORDS); }

"REPEAT" { return newToken(ParserSym.RESERVED_WORDS); }

"SET" { return newToken(ParserSym.RESERVED_WORDS); }

"SHORTINT" { return newToken(ParserSym.RESERVED_WORDS); }

"STRING" { return newToken(ParserSym.RESERVED_WORDS); }

"THEN" { return newToken(ParserSym.RESERVED_WORDS); }

"TO" { return newToken(ParserSym.RESERVED_WORDS); }

"TRUE" { return newToken(ParserSym.RESERVED_WORDS); }

"TYPE" { return newToken(ParserSym.RESERVED_WORDS); }

"UNTIL" { return newToken(ParserSym.RESERVED_WORDS); }

"VAR" { return newToken(ParserSym.RESERVED_WORDS); }

"WHILE" { return newToken(ParserSym.RESERVED_WORDS); }

"WITH" { return newToken(ParserSym.RESERVED_WORDS); }

"WRITE" { return newToken(ParserSym.RESERVED_WORDS); }

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

. {
    Token token = new Token(0, yytext(), yyline, yycolumn);
    tokenList.addError(token);
    return token;
}