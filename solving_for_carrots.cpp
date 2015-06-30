#include <iostream>

int main()
{
  unsigned int contestants;
  std::cin >> contestants;
  unsigned int problems;
  std::cin >> problems;
  
  for(unsigned int i=0; i<contestants; ++i)
  {
    // Ignore Input
    std::cin.ignore(10000, '\n');
  }
  
  std::cout << problems << std::endl;
}
