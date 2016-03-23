#include <iostream>
#include <fstream>
#include <cstdlib>
#include <conio.h>

using namespace std;

class inputOutput
{
private:
	//declare private variables
	string inputFileName;
	string outputFileName;
	ifstream fin;
	ofstream fout;
public:

	//constructor
	inputOutput();

	//local functions
	bool openFiles();
	//conversion for IO files
	void addPlusPlus();
	//set file names
	void setFileInputName(string fileInputName);
	void setFileOutputName(string fileOutputName);
	//get file names
	string getFileInputName();
	string getFileOutputName();
	//validation function
	void validate();

};