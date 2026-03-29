public enum Token {
    L_CORCHETE,      // [
    R_CORCHETE,      // ]
    L_LLAVE,         // {
    R_LLAVE,         // }
    COMA,            // ,
    DOS_PUNTOS,      // :
    LITERAL_CADENA,  // ".*"
    LITERAL_NUM,     // [0-9]+(\.[0-9]+)?((e|E)(+|-)?[0-9]+)?
    PR_TRUE,         // true | TRUE
    PR_FALSE,        // false | FALSE
    PR_NULL,         // null | NULL
    EOF              // fin de archivo
}
