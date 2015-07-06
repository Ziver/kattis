#include <iostream>

int main()
{
    unsigned int number = 0;
    unsigned int reverse = 0;
    unsigned int tmp = 0;
    std::cin >> number;
    
    // Calc bit length
    tmp = number;
    int bitLength = 0;
    while(tmp != 0)
    {
        tmp >>= 1;
        ++bitLength;
    }
    
    // Reverse binary order
    tmp = number;
    for(int i=0; i<bitLength-1; ++i)
    {
        reverse |= (tmp & 0x01);
        reverse <<= 1;
        tmp >>= 1;  
    }
    reverse |= (tmp & 0x01);
    
    std::cout << reverse << std::endl;
}