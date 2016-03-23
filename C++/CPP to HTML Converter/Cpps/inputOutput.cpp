#include <iostream>
#include <fstream>
#include <cstdlib>
#include <conio.h>
#include "inputOutput.h"
#include <string>
#include <vector>
#include <regex>

using namespace std;

//constructor
inputOutput::inputOutput(){};

//prototypes
void addPlusPlus();
void setFileInputName(string fileInputName);
void setFileOutputName(string fileOutputName);
string getFileInputName();
string getFileOutputName();
void validate();

//structure for opening file failure
struct openFailure : public exception
{
	//override what() of base exception class
	const char * what() const throw ()
	{
		return "GOTCHA! Failure to open file\n";
	}
};



void inputOutput::setFileInputName(string fileInputName){
	inputFileName = fileInputName;
}

void inputOutput::setFileOutputName(string fileOutputName){
	outputFileName = fileOutputName;
}

string inputOutput::getFileInputName(){
	return inputFileName;
}

string inputOutput::getFileOutputName(){
	return outputFileName;
}

bool inputOutput::openFiles(){
	try{
		cout << "Attempting to open files...\n";
		fin.open(inputFileName);
		//open inputStream file, if fails then throw open file failure exception
		if (fin.fail())
		{
			throw openFailure();
		}

		//open outputStream file, if fails then throw open file failure exception
		fout.open(outputFileName);
		if (fout.fail())
		{
			throw openFailure();
		}
		cout << "Files opened successfully!\n";
		return true;
	}
	catch (openFailure &e){
		//display the error caught
		cout << "dang we got a problem...AND HERE IT IS: " << e.what() << endl;
		cout << "Restarting program..." << endl;
		return false;
	}
	catch (ifstream::failure e){//if file IO fails, then throw the fail exception
		cout << "IO failure: " << e.what() << endl;
		return false;
	}
	catch (...){//throw any generic error
		cout << "generic error " << endl; 
		return false;
	}
}


void inputOutput::addPlusPlus()
{	
	string line;
	string finishedLine;
	cout << "Copying characters from input file to output file...\n";
	finishedLine += "<PRE> \n"; //<PRE> is the tag for HTML file that will convert all the spacing according to the input file

	//this function will do a while getline on fin.
	//it will break the line into a char array 
	//it will go through the char array, and check for the thing I want to replace.
	//as it goes through, it adds each char to a new string.
	try{
		while (getline(fin, line)){
			vector<char> vectorLine(line.begin(), line.end());
			for (int i = 0; i < vectorLine.size(); i++){
				if (vectorLine[i] == '<'){//replace < with &lt;
					finishedLine += "&lt;";
				}
				else if (vectorLine[i] == '>'){	//replace > with &gt;
					finishedLine += "&gt;";
				}
				else{
					finishedLine += vectorLine[i];
				}
			}
			finishedLine += "\n";
		}
		finishedLine += "</PRE>"; //<PRE> is the tag for HTML file that will convert all the spacing according to the input file

		fout << finishedLine;
		cout << "Characters copied successfully!\n";
		cout << "Closing streams...\n";
		fin.close();
		fout.close();
		cout << "Finished!\n";
	}
	catch (exception &e){
		cout << "There's an error" << e.what() << endl;
	}

}


void inputOutput::validate(){
	//validation for the current objects file IO names
	//declare local variables
	regex validateCpp("[0-9a-zA-Z_-]+[.][c][p][p]{1}");
	regex validateHtml("[0-9a-zA-Z_-]+[.][h][t][m][l]{1}");
	bool valid = false;
	bool firstInputValid = false;
	bool secondInputValid = false;
	string tempFileName;

	while (!firstInputValid){
		cout << "Enter a valid input file name, it must be like 'fileToConvert.cpp'" << endl;
		getline(cin, tempFileName);
		//if regex matches with cpp validate, and the string size is < 255 then it's valid
		if (regex_match(tempFileName, validateCpp) && tempFileName.size() < 255){
			//if regex contains any of these reserved words, then reject it
			if (regex_match(tempFileName, std::regex("[?:CON|?:AUX|?:PRN|?:NUL|?:COM1|?:COM2|?:COM3|?:COM4|?:COM5|?:COM6|?:COM7|?:COM8|?:COM9|?:LPT1|?:LPT2|?:LPT3|?:LPT4|?:LPT5|?:LPT6|?:LPT7|?:LPT8|?:LPT9|?:con|?:aux|?:prn|?:nul|?:com1|?:com2|?:com3|?:com4|?:com5|?:com6|?:com7|?:com8|?:com9|?:lpt1|?:lpt2|?:lpt3|?:lpt4|?:lpt5|?:lpt6|?:lpt7|?:lpt8|?:lpt9]+[.][c][p][p]{1}"))) {
				cout << "Your input contains a reserved word" << endl;
				tempFileName = "";
				firstInputValid = false;
			}
			else{ 
				firstInputValid = true; 
				inputFileName = tempFileName;
				}
		}
		else{ cout << "Doesn't match" << endl; }
	}
	valid = false;
	while (!secondInputValid){
		cout << "Enter a valid output file name, it must be like 'fileConverted.html'" << endl;
		getline(cin, tempFileName);
		//if regex matches with cpp validate, and the string size is < 255 then it's valid
		if (regex_match(tempFileName, validateHtml) && tempFileName.size() < 255){
			//if regex contains any of these reserved words, then reject it
			if (regex_match(tempFileName, std::regex("[?:CON|?:AUX|?:PRN|?:NUL|?:COM1|?:COM2|?:COM3|?:COM4|?:COM5|?:COM6|?:COM7|?:COM8|?:COM9|?:LPT1|?:LPT2|?:LPT3|?:LPT4|?:LPT5|?:LPT6|?:LPT7|?:LPT8|?:LPT9|?:con|?:aux|?:prn|?:nul|?:com1|?:com2|?:com3|?:com4|?:com5|?:com6|?:com7|?:com8|?:com9|?:lpt1|?:lpt2|?:lpt3|?:lpt4|?:lpt5|?:lpt6|?:lpt7|?:lpt8|?:lpt9]+[.][h][t][m][l]{1}"))) {
				cout << "Your input contains a reserved word" << endl;
				tempFileName = "";
				secondInputValid = false;
			}
			else{
				secondInputValid = true;
				outputFileName = tempFileName;
			}
		}
		else{ cout << "Doesn't match" << endl; }
	}
}



