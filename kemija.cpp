#include <iostream>
#include <fstream>
#include <string>

bool isVowel(char c)
{
  switch(c)
  {
    case 'a':
    case 'e':
    case 'i':
    case 'o':
    case 'u':
      return true;
    default:
      return false;
  }
}

int main()
{
  std::string encoded;
  std::string decoded;

  std::getline(std::cin, encoded);
  for(std::string::iterator it=encoded.begin(); it!=encoded.end();++it)
  {
    decoded += *it;
    if(isVowel(*it))
    {
      ++it;
      ++it;
    }
  }

  std::cout << decoded << std::endl;
}
