#include <iostream>
#define DEBUG true

uint64_t factorize(uint64_t x);

int main(){
    uint64_t x = 0;
    std::cin >> x;
    int k = factorize(x);
    std::cout << std::endl << k << std::endl;
}

uint64_t factorize(uint64_t x){
    if(x == 1) return 1;
    int primes [4] = {2,3,5,7};
    int prime_factors = 0;
 
    for(int i=0; i<4; ++i){
        if( primes[i]*primes[i] > x)
            break;
        while( x % primes[i] == 0){
            if(DEBUG)std::cout << primes[i] << " ";
            ++prime_factors;
            x /= primes[i];
        }
    }
    
    return prime_factors;
}