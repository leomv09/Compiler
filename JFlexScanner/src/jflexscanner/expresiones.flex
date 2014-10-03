import java_cup.runtime.*;

class Token
{
    //Atributo
    private String token;
    private String tipoToken;
    private int line;
    private int column;
    private static Map<String, List<int>> mapTokens = new HashMap<>(); 

    //Constructor
    public Token (String token, String tipoToken, int line, int column)
    {
        this.token = token;
        this.tipoToken = tipoToken;
        this.line = line;
        this.column = column;
    }    

    public toString ()
    {
        this.token + " " + this.tipoToken + " [" + Token.getTokenLines(this.tipoToken) + "]"
    }

    public static String getTokenLines(String tipo)
    {
        return "";
    }
} 

%%
%unicode
%cup
%line
%column

PALABRASRESERVADAS = "ARRAY" | "BEGIN" | "BOOLEAN" | "BYTE" | "CASE" | "CHAR" | "CONST" | "DO" | "DOWNTO" | "ELSE" | "END" | "FALSE" | "FILE" | "FOR" | "FORWARD" | "FUNCTION" | "GOTO" | "IF" | "IN" | "INLINE" | "INT" | "LABEL" | "LONGINT" | "NIL" | "OF" | "PACKED" | "PROCEDURE" | "PROGRAM" | "READ" | "REAL" | "RECORD" | "REPEAT" | "SET" | "SHORTINT" | "STRING" | "THEN" | "TO" | "TRUE" | "TYPE" | "UNTIL" | "VAR" | "WHILE" | "WITH" | "WRITE" | 

FIN_DE_LINEA = \r | \n | \r\n
ESPACIOS = {FIN_DE_LINEA} | [ \t\f]
EXP_NUMERICAS = [0-9] //Expresiones númericas permitidas
EXP_ALPHA = [a-zA-Z] //Expresiones 
EXP_ALPHA_NUMERIC = {EXP_NUMERICAS} | {EXP_ALPHA}

OPERADORES = "," | ";" | "++" | "--" | ">=" | ">" | "<=" | "<" | "<>" | "=" | "+" | "-" | "*" | "/" | "(" | ")" | "[" | "]" | ":=" | "." | ":" | "+=" | "-=" | "*=" | "/=" | ">>" | "<<" | "<<=" | ">>=" | "NOT" | "OR" | "AND" | "XOR" | "DIV" | "MOD"

NUMERO_ENTERO_POSITIVO = ({EXP_NUMERICAS})*
NUMERO_ENTERO_NEGATIVO = "-"{NUMERO_ENTERO_POSITIVO}
NUMERO_ENTERO = {NUMERO_ENTERO_NEGATIVO} | {NUMERO_ENTERO_POSITIVO}
NUMERO_REAL = {NUMERO_ENTERO}"."{NUMERO_ENTERO_POSITIVO} "E"{NUMERO_ENTERO}?

STRING = "'" ({ESPACIOS} | {EXP_ALPHA_NUMERIC})* "'" //Faltan símbolos
CARACTER = "#"{NUMERO_ENTERO_POSITIVO} | "'"{EXP_ALPHA_NUMERIC}"'" 

IDENTIFICADORES = {EXP_ALPHA}({EXP_ALPHA_NUMERIC})*//Identificador que inicie con una letra.

LITERALES = {NUMERO_ENTERO} | {NUMERO_REAL} | {CARACTER} | {STRING}

COMENTARIO_LINEA = "//" {[^\r\n]}* {FIN_DE_LINEA}?
COMENTARIO_BLOQUE = "(*" [^*] ~"*)" | "(*" "*"+ ")" | "{" [^}] ~"}"
COMENTARIOS = {COMENTARIO_LINEA} | {COMENTARIO_BLOQUE}


%%



