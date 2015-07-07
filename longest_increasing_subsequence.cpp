#include <iostream>
#include <stdio.h>

#define MAX_ARR_SIZE 100000

int arrLength;
int arr[MAX_ARR_SIZE];

int resultArrLength;
int resultArr[MAX_ARR_SIZE];

int findLongestSequence(int arrSeq[], int arrSeqLength)
{
    if(arrSeqLength <= 0)
        return 0;
    int longestSeq = 1;
    for(int i=1; i<arrSeqLength; ++i)
    {
        if(arrSeq[0] < arrSeq[i])
        {
            int tmpLen = 1 + findLongestSequence(&arrSeq[i], arrSeqLength-i);
            if(tmpLen > longestSeq)
                longestSeq = tmpLen;
        }
    }
    return longestSeq;
}

int main()
{    
    while( std::cin >> arrLength )
    {        
        for(int i=0; i<arrLength; ++i)
            scanf("%d", &arr[i]);
        
        resultArrLength = findLongestSequence(arr, arrLength);
        
        std::cout << resultArrLength << std::endl;
        for(int i=0; i<resultArrLength; ++i)
            std::cout << resultArr[i] << " ";
            std::cout << std::endl;
    }    
}