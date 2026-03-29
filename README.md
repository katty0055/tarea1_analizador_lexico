# Analizador Léxico - Tarea 1

Analizador léxico para archivos de formato JSON-like, implementado en Java.
Lee un archivo fuente, identifica los tokens de cada línea y genera un archivo de salida con los tokens encontrados.

## Requisitos

- Java JDK instalado (versión 8 o superior)
- Verificar instalación: `java -version`

## Estructura del proyecto

```
src/
    Token.java      # Enum con los tipos de tokens
    Lexer.java      # Lógica del analizador léxico
    Main.java       # Punto de entrada del programa
fuente.txt          # Archivo de entrada de ejemplo
```

## Compilación

Desde la raíz del proyecto, ejecutar:

```bash
javac src/Token.java src/Lexer.java src/Main.java
```

## Ejecución

```bash
java -cp src Main <archivo_entrada> <archivo_salida>
```

**Ejemplo:**

```bash
java -cp src Main fuente.txt output_generado.txt
```

## Tokens reconocidos

| Token | Descripción |
|---|---|
| `L_LLAVE` | `{` |
| `R_LLAVE` | `}` |
| `L_CORCHETE` | `[` |
| `R_CORCHETE` | `]` |
| `DOS_PUNTOS` | `:` |
| `COMA` | `,` |
| `LITERAL_CADENA` | Cadena de texto entre comillas: `"texto"` |
| `LITERAL_NUM` | Número entero o decimal: `42`, `3.14`, `1e10` |
| `PR_TRUE` | Palabra reservada `true` o `TRUE` |
| `PR_FALSE` | Palabra reservada `false` o `FALSE` |
| `PR_NULL` | Palabra reservada `null` o `NULL` |

## Manejo de errores

Si se encuentra un carácter no reconocido, el programa imprime un mensaje de error indicando la línea y columna, y continúa con la siguiente línea del archivo.
