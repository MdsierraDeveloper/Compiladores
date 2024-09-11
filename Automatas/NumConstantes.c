#include <ctype.h>
#include <stdbool.h>
#include <stdio.h>

// Definición de los estados del autómata
typedef enum {
    INI,  // Estado inicial
    SAI,  // Estado de signo al inicio
    DIG,  // Estado de dígito
    PIS,  // Estado de punto al inicio o después de signo
    EXP,  // Estado de símbolo de exponente
    PDD,  // Estado de punto después de dígito
    DDP,  // Estado de dígito después de punto
    SDE,  // Estado de signo después de exponente
    DDE,  // Estado de dígito después de exponente
    ERROR // Estado de error
} Estado;

// Tabla de transiciones (Estado actual, Tipo de carácter)
const Estado Transiciones[10][4] = {
    [INI] =    {SAI, DIG, ERROR, PIS}, // Estado inicial
    [SAI] =    {ERROR, DIG, ERROR, PIS}, // Después de signo
    [DIG] =    {ERROR, DIG, EXP, PDD}, // Después de dígito
    [PIS] =    {ERROR, DDP, ERROR, ERROR}, // Después de punto
    [EXP] =    {SDE, DDE, ERROR, ERROR}, // Después de 'e'
    [PDD] =    {ERROR, DIG, EXP, ERROR}, // Después de punto después de dígito
    [DDP] =    {ERROR, DDP, EXP, ERROR}, // Después de dígito después de punto
    [SDE] =    {ERROR, DDE, ERROR, ERROR}, // Después de signo después de 'e'
    [DDE] =    {ERROR, DDE, ERROR, ERROR}, // Después de dígito después de 'e'
    [ERROR] =  {ERROR, ERROR, ERROR, ERROR} // Estado de error
};

// Determina el tipo de carácter
int tipoCaracter(char c) {
    if (c == '+' || c == '-') return 0; // Signo
    if (isdigit(c)) return 1; // Dígito
    if (tolower(c) == 'e') return 2; // Exponente
    if (c == '.') return 3; // Punto
    return -1; // Carácter no válido
}

// Obtiene el siguiente estado dado el estado actual y un carácter
Estado estadoSiguiente(Estado estadoActual, char letra) {
    int tipo = tipoCaracter(letra);
    if (tipo == -1) return ERROR;
    return Transiciones[estadoActual][tipo];
}

// Verifica si el estado final es aceptable
bool esAceptado(Estado estadoActual) {
    return estadoActual == DIG || estadoActual == DDP || estadoActual == DDE;
}

// Función principal para comprobar si una cadena es una constante numérica válida
bool esConstante(const char *cadena) {
    Estado estadoActual = INI;
    while (*cadena != '\0') {
        estadoActual = estadoSiguiente(estadoActual, *cadena);
        if (estadoActual == ERROR) return false;
        cadena++;
    }
    return esAceptado(estadoActual);
}

int main() {
    // Prueba del autómata con una constante numérica válida
    bool esconstante = esConstante("2.33e2");
    
    if (esconstante) {
        printf("Es una constante válida.\n");
    } else {
        printf("No es una constante válida.\n");
    }
    
    return 0;
}
