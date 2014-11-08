package compiler.scanner;

import java_cup.runtime.Symbol;
import compiler.Token;
import compiler.TokenList;
import compiler.parser.ParserSym;

%%

%{
    private final TokenList tokenList;

    public TokenList getTokenList()
    {
        return this.tokenList;
    }

    private Token newToken(int type)
    {
        Token token = new Token(type, yytext().toLowerCase(), yyline, yycolumn);
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
%ignorecase
%caseless

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

"ARRAY" { return newToken(ParserSym.ARRAY); }

"BEGIN" { return newToken(ParserSym.BEGIN); }

"BOOLEAN" { return newToken(ParserSym.BOOLEAN); }

"BYTE" { return newToken(ParserSym.BYTE); }

"CASE" { return newToken(ParserSym.CASE); }

"CHAR" { return newToken(ParserSym.CHAR); }

"CONST" { return newToken(ParserSym.CONST); }

"DIV" { return newToken(ParserSym.DIV); }

"DO" { return newToken(ParserSym.DO); }

"DOWNTO" { return newToken(ParserSym.DOWNTO); }

"ELSE"  { return newToken(ParserSym.ELSE); }

"END" { return newToken(ParserSym.END); }

"FALSE" { return newToken(ParserSym.FALSE, Boolean.FALSE); }

"FILE" { return newToken(ParserSym.FILE); }

"FOR"  { return newToken(ParserSym.FOR); }

"FORWARD" { return newToken(ParserSym.FORWARD); }

"FUNCTION" { return newToken(ParserSym.FUNCTION); }

"GOTO" { return newToken(ParserSym.GOTO); }

"IF" { return newToken(ParserSym.IF); }

"IN" { return newToken(ParserSym.IN); }

"INLINE" { return newToken(ParserSym.INLINE); }

"INT" { return newToken(ParserSym.INT); }

"LABEL" { return newToken(ParserSym.LABEL); }

"LONGINT" { return newToken(ParserSym.LONGINT); }

"NIL" { return newToken(ParserSym.NIL, null); }

"OF" { return newToken(ParserSym.OF); }

"PACKED" { return newToken(ParserSym.PACKED); }

"PROCEDURE" { return newToken(ParserSym.PROCEDURE); }

"PROGRAM" { return newToken(ParserSym.PROGRAM); }

"READ" { return newToken(ParserSym.READ); }

"REAL" { return newToken(ParserSym.REAL); }

"RECORD" { return newToken(ParserSym.RECORD); }

"REPEAT" { return newToken(ParserSym.REPEAT); }

"SET" { return newToken(ParserSym.SET); }

"SHORTINT" { return newToken(ParserSym.SHORTINT); }

"STRING" { return newToken(ParserSym.STRING); }

"THEN" { return newToken(ParserSym.THEN); }

"TO" { return newToken(ParserSym.TO); }

"TRUE" { return newToken(ParserSym.TRUE, Boolean.TRUE); }

"TYPE" { return newToken(ParserSym.TYPE); }

"UNTIL" { return newToken(ParserSym.UNTIL); }

"VAR" { return newToken(ParserSym.VAR); }

"WHILE" { return newToken(ParserSym.WHILE); }

"WITH" { return newToken(ParserSym.WITH); }

"WRITE" { return newToken(ParserSym.WRITE); }

"+" { return newToken(ParserSym.PLUS); }

"++" { return newToken(ParserSym.PLUS_PLUS); }

"+=" { return newToken(ParserSym.PLUS_EQUALS); }

"-" { return newToken(ParserSym.MINUS); }

"--" { return newToken(ParserSym.MINUS_MINUS); }

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

"<=" { return newToken(ParserSym.LESS_EQUALS_THAN); }

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

";" { return newToken(ParserSym.SEMI); }

"," { return newToken(ParserSym.COMMA); }

":" { return newToken(ParserSym.COLON); }

{NUMERO_ENTERO} { return newToken(ParserSym.NUMBER, new Integer(yytext())); }

{NUMERO_REAL} { 
    Double d = Double.valueOf(yytext());
    return newToken(ParserSym.DOUBLE, d);
}

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

{STRING} { 
    String s = yytext().replace("\'", "");
    return newToken(ParserSym.XTRING, s);
}

{IDENTIFICADORES} { return newToken(ParserSym.IDENTIFIER); }
 
. {
    return newToken(ParserSym.error);
}
