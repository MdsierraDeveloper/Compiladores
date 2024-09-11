#include<stdio.h>
#include<stdlib.h>
#include<stdbool.h>


enum Estado {
    INI,ES1,ES2,ES3,ES4,
    ES5,ES6,ES7,ES8, ERR,
};


const enum Estado transiciones[9][4] = {
    {ES1, ES5, ES7, ES3},
    {ES2, ES4, ES3, ERR},
    {ES3, ERR, ERR, ERR},
    {ERR, ERR, ERR, ERR},
    {ES1, ERR, ERR, ERR},
    {ES6, ERR, ERR, ERR},
    {ES4, ERR, ERR, ERR},
    {ES1, ERR, ES8, ES3},
    {ES1, ERR, ES4, ERR}
};

enum Estado estadoSiguiente(enum Estado actual, char ltr){
    if(ltr == 'I')
        return transiciones[actual][0];
    if(ltr == 'V')
        return transiciones[actual][1];
    if(ltr == 'X')
        return transiciones[actual][2];
    if(ltr == 'L')
        return transiciones[actual][3];

    return ERR;
}

bool es_aceptado(enum Estado actual){
    return actual == ES1 || actual == ES2 || actual == ES3 || actual == ES4 || actual == ES5 || actual == ES6 || actual == ES7 || actual == ES8;
}

bool esValido(const char* letra){
    enum Estado actual = INI;
    while(*letra != '\0'){
        actual = estadoSiguiente(actual, *letra);
        
        if(actual == ERR){
            return false;
        }
            

        letra++;
    }

    return es_aceptado(actual);
}

int main(){

    if(esValido("XXXIIII"))
        printf("Es romano");
    else
        printf("No valido");

    return 0;
}