package scanner;

%%

%{
    private TokenList tokenList;

    public TokenList getTokenList()
    {
        return this.tokenList;
    }
%}

%init{
    tokenList = new TokenList();
%init}

%eof{
    tokenList.generateTokenLines();
%eof}

%class Lexer
%type Token
%function nextToken
%unicode
%line
%column

PALABRASRESERVADAS = "ARRAY" | "BEGIN" | "BOOLEAN" | "BYTE" | "CASE" | "CHAR" | "CONST" | "DO" | "DOWNTO" | "ELSE" | "END" | "FALSE" | "FILE" | "FOR" | "FORWARD" | "FUNCTION" | "GOTO" | "IF" | "IN" | "INLINE" | "INT" | "LABEL" | "LONGINT" | "NIL" | "OF" | "PACKED" | "PROCEDURE" | "PROGRAM" | "READ" | "REAL" | "RECORD" | "REPEAT" | "SET" | "SHORTINT" | "STRING" | "THEN" | "TO" | "TRUE" | "TYPE" | "UNTIL" | "VAR" | "WHILE" | "WITH" | "WRITE"

FIN_DE_LINEA = \r|\n|\r\n
ESPACIOS = {FIN_DE_LINEA} | [ \t\f]
EXP_NUMERICAS = [0-9]
EXP_ALPHA = [a-zA-Z]
EXP_ALPHA_NUMERIC = {EXP_NUMERICAS} | {EXP_ALPHA}

OPERADORES = "," | ";" | "++" | "--" | ">=" | ">" | "<=" | "<" | "<>" | "=" | "+" | "-" | "*" | "/" | "(" | ")" | "[" | "]" | ":=" | "." | ":" | "+=" | "-=" | "*=" | "/=" | ">>" | "<<" | "<<=" | ">>=" | "NOT" | "OR" | "AND" | "XOR" | "DIV" | "MOD"

NUMERO_ENTERO_POSITIVO = ({EXP_NUMERICAS})+
NUMERO_ENTERO_NEGATIVO = "-"{NUMERO_ENTERO_POSITIVO}
NUMERO_ENTERO = {NUMERO_ENTERO_NEGATIVO} | {NUMERO_ENTERO_POSITIVO}
NUMERO_REAL = {NUMERO_ENTERO}"."{NUMERO_ENTERO_POSITIVO} "E"{NUMERO_ENTERO}?

STRING = "'" ({ESPACIOS} | {EXP_ALPHA_NUMERIC})* "'" //Faltan símbolos
CARACTER = "#"{NUMERO_ENTERO_POSITIVO} | "'"{EXP_ALPHA_NUMERIC}"'" 

IDENTIFICADORES = {EXP_ALPHA}({EXP_ALPHA_NUMERIC})*//Identificador que inicie con una letra.

LITERALES = {NUMERO_ENTERO} | {NUMERO_REAL} | {CARACTER} | {STRING}

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

{PALABRASRESERVADAS} {
    Token token = new Token(yytext(), "PALABRARESERVADA", yyline, yycolumn);
    tokenList.addToken(token);
    return token;
}

{OPERADORES} {
    Token token = new Token(yytext(), "OPERADOR", yyline, yycolumn);
    tokenList.addToken(token);
    return token;
}

{LITERALES} {
    Token token = new Token(yytext(), "LITERAL", yyline, yycolumn);
    tokenList.addToken(token);
    return token;
}

{IDENTIFICADORES} {
    Token token = new Token(yytext(), "IDENTIFICADOR", yyline, yycolumn);
    tokenList.addToken(token);
    return token;
}

. {
    Token token = new Token(yytext(), "ERROR", yyline, yycolumn);
    return token;
}