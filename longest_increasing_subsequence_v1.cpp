#include <iostream>
#include <stdio.h>
#include <limits.h>
#include <cstring>

#define MAX_ARR_SIZE 100000

int arrLength;
int arr[MAX_ARR_SIZE];


int findLongestSequence(int index, int*& resultArr)
{
    if(index >= arrLength)
        return 0;
    int longestSeq = 1;
    resultArr = new int[arrLength-index];
    resultArr[0] = index;
    for(int i=index+1; i<arrLength; ++i)
    {
        if(arr[index] < arr[i])
        {
            int* tmpArr;
            int tmpLen = findLongestSequence(i, tmpArr);
            if(tmpLen > longestSeq - 1)
            {
                longestSeq = tmpLen + 1;
                for(int i=0; i<tmpLen; ++i)
                    resultArr[i+1] = tmpArr[i];
                //memcpy(&resultArr[1], tmpArr, tmpLen);
                delete[] tmpArr;
            }
        }
    }
    return longestSeq;
}

int main()
{    
    while( std::cin >> arrLength )
    {
        arr[0] = INT_MIN;
        ++arrLength;
        for(int i=1; i<arrLength; ++i)
            scanf("%d", &arr[i]);
        
        int* resultArr;
        int resultArrLength = findLongestSequence(0, resultArr);
        
        std::cout << resultArrLength-1 << std::endl;
        for(int i=1; i<resultArrLength; ++i)
            std::cout << resultArr[i]-1 << " ";
        std::cout << std::endl;
        delete[] resultArr;
    }    
}