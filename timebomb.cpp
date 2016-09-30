/**
https://open.kattis.com/problems/timebomb
*/
#include <iostream>
#include <stdlib.h>     /* atoi */
#include <bitset>

#define SUCCESS "BEER!!"
#define FAILED  "BOMM!!"


//###  ###  ###  ###  ###  ###  ###  ###  ###  ###
//***    *  ***  ***  * *  ***  ***  ***  ***  ***
//* *    *    *    *  * *  *    *      *  * *  * *
//* *    *  ***  ***  ***  ***  ***    *  ***  ***
//* *    *  *      *    *    *  * *    *  * *    *
//***    *  ***  ***    *  ***  ***    *  ***  ***


int main()
{
    char columns[100] = {0};
    // Read
    char c;
    int index = 0;
    for (int i=0; i<5; ++i)
    {
        index = 0;
        c = ' ';
        while (c != '\n')
        {
            for(int k=0; k<3; ++k)
            {
                std::cin.get(c);
                columns[index] <<= 1;
                if (c == '*')
                    columns[index] |= 1;
                ++index;
            }
            std::cin.get(c); // read space or newline
        }
    }
    int length = index/3;
    /*std::cout << "length: " << length << std::endl;
    for (int i=0; i<length*3; ++i)
    {
        std::cout << std::bitset<8>(columns[i]) << std::endl;
    }*/

    // Parse
    char numbers[length];
    index = 0;
    for(int i=0; i<length*3; i+=3){
        switch (columns[i])
        {
        case 0b00000: // 1
            numbers[index] = '1';
            break;
        case 0b10111: // 2
            numbers[index] = '2';
            break;
        case 0b10101: // 3
            numbers[index] = '3';
            break;
        case 0b10000: // 7
            numbers[index] = '7';
            break;
        case 0b11100: // 4
            numbers[index] = '4';
            break;
        case 0b11101: // 5, 9
            switch (columns[i+2])
            {
            case 0b10111: // 5
                numbers[index] = '5';
                break;
            case 0b11111: // 9
                numbers[index] = '9';
                break;
            }
            break;
        case 0b11111: // 0, 6, 8
            switch (columns[i+1])
            {
            case 0b10001: // 0
                numbers[index] = '0';
                break;
            default:
                switch (columns[i+2])
                {
                case 0b10111: // 6
                    numbers[index] = '6';
                    break;
                case 0b11111: // 8
                    numbers[index] = '8';
                    break;
                }
                break;
            }
            break;
        }
        //std::cout << numbers[index] << std::endl;
        ++index;
    }
    numbers[index] = '\n';

    int number = atoi(numbers);
    //std::cout << "result: " << number << std::endl;
    if (number % 6 == 0)
        std::cout << SUCCESS << std::endl;
    else
        std::cout << FAILED << std::endl;
    return 0;
}