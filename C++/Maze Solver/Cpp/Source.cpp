#include <iostream>
#include <windows.h>
#include "interface.h"
#include <regex>
using namespace std;

int main(int argc, char *argv[])
{


	char quit[1];
	quit[0] = 'q';
	regex Tester("((.*)\\.(txt))");
	char testAgainst[255];

	while (1)
	{
	

		cout << "Enter file name";
		cin >> testAgainst;
		if (testAgainst[1] == quit[0])
		{
			break;
		}
		else if (regex_match(testAgainst, Tester))
		{
			Interface *iFace = new Interface();
			iFace->Start(testAgainst);//start with maze text file name
		}
	}

	return 0;
}