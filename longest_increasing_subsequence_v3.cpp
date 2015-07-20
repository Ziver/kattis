#include <iostream>
#include <stdio.h>
#include <limits.h>
#include <cstring>

#define MAX_ARR_SIZE 100000

int arrLength;
// Array containing the input data
int arr[MAX_ARR_SIZE];
// Array containing max
int resultArr[MAX_ARR_SIZE];
int resultLink[MAX_ARR_SIZE];

int findLongestSequence()
{
    int globalMaxIndex = 0;
    for(int i=0; i<arrLength; ++i)
    {
        for(int j=0; j<i; ++j)
        {
            //std::cout << "arr["<<j<<"]="<<arr[j]<<" < arr["<<i<<"]="<<arr[i]<<" && currentMax="<<currentMax<<" < resultArr["<<j<<"]="<<resultArr[j]<<" ";
            if(arr[j] < arr[i] && resultArr[i] <= resultArr[j])
            {
                //std::cout << "best";
                resultArr[i] = resultArr[j] + 1;
                resultLink[i] = j;
            }
            //std::cout << std::endl;
        }
        if(resultArr[globalMaxIndex] < resultArr[i])
            globalMaxIndex = i;
    }

    /*std::cout << "resultArr:  ";
    for(int i=0; i< arrLength; ++i)
        std::cout << resultArr[i] << " ";
    std::cout << std::endl << "resultLink: ";
    for(int i=0; i< arrLength; ++i)
        std::cout << resultLink[i] << " ";
    std::cout << std::endl << "index: " << globalMaxIndex << std::endl;*/
    return globalMaxIndex;
}


int main()
{    
    arr[0] = INT_MIN;
    while( std::cin >> arrLength )
    {
        for(int i=0; i<arrLength; ++i)
        {
            scanf("%d", &arr[i]);
            resultArr[i] = 1;
            resultLink[i] = -1;
        }
        
        
        int linkIndex = findLongestSequence();
        
        std::cout << resultArr[linkIndex] << std::endl;
        while(linkIndex >= 0)
        {
            std::cout << linkIndex << " ";
            linkIndex = resultLink[linkIndex];
        }
        std::cout << std::endl;
    }    
}