#include <iostream>
#include <cstring>

#define MAX_WORD_LENGTH 25+1


unsigned int countKeyPress(char wanted[], char typed[])
{
  unsigned int presses = 0;
  size_t wantedLength = strlen(wanted);
  size_t typedLength = strlen(typed);
  
  // Calculate number of deletions
  for(unsigned int i=0; i<std::min(wantedLength,typedLength); ++i)
  {
    if(wanted[i] != typed[i])
    {    
      presses += typedLength - i;
      break;
    }
  } 
  // Calculate number of new characters
  presses += std::max(wantedLength,typedLength) 
                   - (std::min(wantedLength,typedLength) - presses);

  return presses;
}


int main()
{
  unsigned int testcases;
  std::cin >> testcases;

  for(unsigned int i=0; i<testcases; ++i)
  {
    // Input
    static char wanted[MAX_WORD_LENGTH];
    std::cin >> wanted;
    static char typed[MAX_WORD_LENGTH];
    std::cin >> typed;
    static char suggestions[3][MAX_WORD_LENGTH];
    std::cin >> suggestions[0] >> suggestions[1] >> suggestions[2];
    
    // Calculate
    unsigned int result = countKeyPress(wanted, typed);
    for(unsigned int j=0; j<3; ++j)
    {
      result = std::min(result, 1 + countKeyPress(wanted, suggestions[j]));
    }

    std::cout << result << std::endl;
  }
}
