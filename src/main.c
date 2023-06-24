/*
 * Julian Personal Projet
*/

#include <stdbool.h>
#include <string.h>
#include <stdio.h>
#include <stdlib.h>

#define s_okay_list "+-*/%"

//Valid symbols : + - * /
bool valid_symbol(char *symbol)
{
    if ( strlen(symbol) > 1 )
        return false;

    for (int i = 0; i < strlen(s_okay_list); i++) {
        if ( symbol[0] == s_okay_list[i] )
            return true;
    }
    return false;
}

bool valid_number(char *number)
{
    for (int i = 0; number[i]; i++) {
        if (48 <= number[i] && number[i] <= 57)
            continue;
        return false;
    }
    return true;
}

int do_calculus(char **av)
{
    int n1 = atoi(av[2]);
    int n2 = atoi(av[3]);
    int result = 0;

    switch ( av[1][0] ) {
        case ('+') :
            result = n1 + n2; break;
        case ('-') :
            result = n1 - n2; break;
        case ('*') :
            result = n1 * n2; break;
        case ('/') :
            result = n1 / n2; break;
        case ('%') :
            result = n1 % n2; break;
        default :
            ;
    }
    printf("Your result is %d.\n", result);
    return result;
}

int main(int ac, char **av)
{
    if (ac != 4)
        return 1 + (printf("Usage:\t ./math <symbol> <number1> <number2>.\n") * 0);

    if (valid_symbol(av[1]) == false)
        return 84 + ( printf("[Error] Invalid symbol.\n") * 0);

    if (valid_number(av[2]) == false)
        return 84 + (printf("[Error] Invalid number 1.\n") * 0);

    if (valid_number(av[3]) == false)
        return 84 + (printf("[Error] Invalid number 2.\n") * 0);

    return do_calculus(av);
}
