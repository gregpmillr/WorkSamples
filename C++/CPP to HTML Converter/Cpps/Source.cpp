//This program will convert the selected file to another file for example .cpp to .html file.
#include <iostream>
#include <fstream>
#include <cstdlib>
#include <conio.h>
#include "inputOutput.h"
#include <regex>
using namespace std;

bool worked = false;
string choice = "";
int main()
{
	//read from fileToConvert.cpp, write to fileConverted.htm
	while (!worked){
		try{
			inputOutput io1;
			io1.validate();
			//open files for read/write
			if (io1.openFiles()){
				//conversion function on file IO object
				io1.addPlusPlus();
				cout << "Success! Completed file IO.\n";
				cout << "Would you like to go again? Y/N" << endl;
				getline(cin,choice);
				if (choice == "Y"){
					worked = false;
				}
				else{ worked = true; }
			}
			//read from fin, write to fout

		}
		catch (exception &e){
			//generic exception catch
			cout << "error:" << e.what() << endl;
			_getch();
		}
	}
	return 0;
}