//Header file class ui
#ifndef _INTERFACE_H

#define _INTERFACE_H
//Includes for program
#define NOMINMAX//Ignores max() in <windows.h>
#include <windows.h>
#include <time.h> 
#include <iostream>
#include <conio.h>
#include <stdio.h>
#include <stdlib.h> 
#include <fstream>
#include "list.h"
using namespace std;

//Key values
const int qKey = 81;//'Q'
const int eKey = 69;//'E'
const int gKey = 71;//'G'
const int sKey = 83;//'S'
const int dKey = 68;//'D'
const int rKey = 82;//'R'

//Numerical constants
const int maxIndex = 7;//Max length of the list index
const int minValue = 0;//Minimum int value used throughout program
const int maxValue = 49;//Maximum int value to be stored in elements
const int commandLength = 4;//Length of desired command input
const int initCommand = 0;//Cell numbers excluding the null terminator
const int selectionDigit = 1;
const int extraDigit = 2;

const char nullChar = '\0';//Null character value

class ui//ui class
{
private:
	//Private properties
	char *command = new char[commandLength];//Contains input for commands
public:
	ui();//Default constructor
	virtual ~ui();//Destructor

	void begin();//Initializes the entire routine
	void generateSequence();//Subroutine initializes a sequences of seven elements
	void commandRoutine();//Subroutine handles input after an entry of '\n'
	void trimArray();//Defaults command[1] or [1][2] to default on certain character matches
	void clearScreen();//Clears the terminal 
	int concatenateInt(int x, int y);//Retreive int value from command[1][2]
};//End class ui
#endif