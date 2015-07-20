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
    for(int i=arrLength-1; i>=0; --i)
    {
        for(int j=i+1; j<arrLength; ++j)
        {
            if(arr[j] > arr[i] && resultArr[i] <= resultArr[j])
            {
                resultArr[i] = resultArr[j] + 1;
                resultLink[i] = j;
            }
        }
        if(resultArr[globalMaxIndex] < resultArr[i])
            globalMaxIndex = i;
    }

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