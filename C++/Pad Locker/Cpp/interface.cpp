//Source for interface.h
#include "interface.h"

ui::ui(){};

ui::~ui(){};

LinkedList LL;//New instance of LinkedList

void ui::begin()
{
	generateSequence();
	commandRoutine();
}//End begin

void ui::generateSequence()
{
	char direction;//Temp instance of Node->direction
	int number = minValue;//Default value of Node->number

	//Loop to the maximum index value
	for (int x = minValue; x < maxIndex; x++)
	{
		//int aRandom = rand() % maxValue; //For generating random seq on execution*
		//If the square root of x has a remainder of 0
		if (x % 2 == 0)
		{
			direction = 'R';
			LL.Add(direction, number);//Instantiate a Node object (N(next*,R,0))
		}
		else
		{
			direction = 'L';//Store the inverse direction
			LL.Add(direction, number);
		}//End if

		direction = nullChar;//Default temp instance of Node->direction
	}//End for loop
}//End generateSequence

void ui::commandRoutine()
{
	int indexSelected = minValue;//Current working value
	int indexLength = minValue;//Current length of index
	int newNumber = minValue;//Temp instance of supplemental data
	int digitOne, digitTwo;//Contain command[1][2] after being cast to int
	bool nodeSelected = false;//True after an input of Gx
	bool nullFirst = false;//True if all nodes are deleted
	HANDLE hConsole = GetStdHandle(STD_OUTPUT_HANDLE);

	LL.updateSeqeunce();//<-Update characters for output

	cout << "Welcome to padLock. Press any key for a list of commands.";
	_getch();
	clearScreen();//<-Clear terminal

	while (1)
	{
		//Check to see if node objects exist
		if (LL.checkFirstNode() == false)
		{
			cout << "Current sequence: ";
		}//End if

		//Loop for the length of the character sequence and output the c string
		for (int x = minValue; x < sequenceLength; x++)
		{
			SetConsoleTextAttribute(hConsole, FOREGROUND_RED | FOREGROUND_GREEN | FOREGROUND_INTENSITY);
			cout << LL.sequence[x];
		}//End for

		cout << endl;
		SetConsoleTextAttribute(hConsole, FOREGROUND_RED | FOREGROUND_GREEN | FOREGROUND_BLUE);
		
		//If nodeSelected was set to true on this loop, the user has entered a valid index number
		if (nodeSelected == true)
		{
			cout << "Node selected: ";
			SetConsoleTextAttribute(hConsole, FOREGROUND_BLUE | FOREGROUND_GREEN | FOREGROUND_INTENSITY);
			cout << indexSelected << endl << endl;
		}
		else if (LL.checkFirstNode() == true)
		{
			SetConsoleTextAttribute(hConsole, FOREGROUND_RED | FOREGROUND_INTENSITY);
			cout << "All nodes have been deleted, please restart to try again." << endl << endl;
			nullFirst = true;
		}
		else
		{
			cout << "A node has not been selected." << endl << endl;
		}//End if

		SetConsoleTextAttribute(hConsole, FOREGROUND_RED | FOREGROUND_GREEN | FOREGROUND_BLUE);
		cout << "Enter a command by entering its corresponding character: " << endl << endl << "Q: Quit without saving."
			<< endl << "E: Quit and save the current sequence." << endl << "G: Go to a position in the sequence. Ex: (G,0-6)"
			<< endl << "S: Replace a sequence value. Ex: (S,0-49)" << endl << "D: Delete a sequence value." << endl <<
			"R: Reset the sequence values to zero." << endl << endl;
		cout << "Enter a command: ";

		//Get an instance of command* Ex: command[x][y][z]['\n']
		cin.get(command, commandLength);
		cin.clear();//Clear the failbit
		cin.ignore(numeric_limits<streamsize>::max(), '\n');//Flush the buffer after input has been extracted with \n as the delimiter
		trimArray();//<-Sets unwanted input to null

		if (command[initCommand] == qKey)//On input ['Q']
		{
			break;//Return to main, exit program
		}
		else if (command[initCommand] == eKey)//On input ['E'];
		{
			ofstream sequenceHistory;//Create ofstream object
			sequenceHistory.open("sequenceHistory.txt", fstream::app);//Open file and append if it exists

			//If the file successfully opens
			if (sequenceHistory.is_open())
			{
				//Loop through the sequence c string and read each character to file
				for (int x = minValue; x < sequenceLength; x++)
				{
					sequenceHistory << LL.sequence[x];
				}
				sequenceHistory << '\n';//Breakline
				sequenceHistory.close();//Close ofstream
				cout << "The sequence has been saved. Press any key to quit." << endl;
				_getch();
				break;//Return to main, exit program
			}
			else
			{
				cout << "The file could not be opened. Press any key to try again or quit without saving." << endl;
			}
		}
		else if (command[initCommand] == gKey && isdigit(command[selectionDigit]))//On input of ['G']['x'], only if [1] is a digit
		{
			indexSelected = command[selectionDigit] - '0';//Cast command[1] to int 
			indexLength = LL.checkLastNode();//Get current length of list index

			while (1)
			{
				//If the input is within a range of zero to seven
				if (indexSelected < indexLength)
				{
					//Check if all nodes were deleted
					if (nullFirst != true)
					{
						nodeSelected = true;//Set true
					}//End if
					
					cout << "Node number: " << indexSelected << " selected." << " Press any key to continue." << endl;
					break;//Break while loop, continue routine
				}
				else
				{
					indexLength--;//Subtract one for output
					cout << "Please enter a valid node number between 0-" << indexLength << "." << endl;
					nodeSelected = false;//Set false
					indexSelected = minValue;//Default the index number on bad input
					break;
				}//End nested if
			}//End while
		}
		else if (command[initCommand] == sKey && isdigit(command[selectionDigit]))//On an input of ['S']['x'], only if [1] is a digit
		{
			//if nodeSelected is set to false, continue routine
			if (nodeSelected == false)
			{
				cout << "This command can only be performed after a node has been selected.\nPress any key to continue." << endl;
			}
			else
			{
				//Loop until a valid command is input
				while (1)
				{
					digitOne = command[selectionDigit] - '0';//Cast command[1] to an int
					newNumber = digitOne;//If command[2] is null, this is the value to be set in Node->number

					//However, if command[2] is not null and contains a digit
					if (isdigit(command[extraDigit]))
					{
						digitTwo = command[extraDigit] - '0';//Cast command[1] to an int
						newNumber = concatenateInt(digitOne, digitTwo);//<-Concatenates the int values cast from command[1][2]
					}

					//If the retrieved value is within the valid range
					if (newNumber >= minValue && newNumber <= maxValue)
					{
						LL.replaceData(indexSelected, newNumber);//<-Pass the index and element values to be set in the LinkedList class
						nodeSelected = false;//Set false
						cout << "Node at positon: " << indexSelected << " was changed to " << newNumber << 
							"." << " Press any key to continue." << endl;
						indexSelected = minValue;//Set default value
						break;//Break and continue routine
					}
					else
					{
						cout << "Invalid input. Please enter a number between 0 and 49." << endl;
						break;//Break and continue routine
					}//End if
				}//End while
			}//End if
		}
		else if (command[initCommand] == dKey)//On input of ['D']
		{
			//If nodeSelected is set to false, continue the routine
			if (nodeSelected == false)
			{
				cout << "This command can only be performed after a node has been selected.\nPress any key to continue." 
					<< endl;
			}
			else
			{
				LL.DeleteNode(indexSelected);//<-Pass the index value input to the LinkedList class to perform a delete routine
				nodeSelected = false;//Set false
				cout << "Node at positon: " << indexSelected << " was deleted." << " Press any key to continue." << endl;
				indexSelected = minValue;//Set default value
			}//End if
		}
		else if (command[initCommand] == rKey)//On input of ['R']
		{
			LL.resetSeqeuence();//<-Call to reset all elements to 0
			cout << "The sequence is about to be reset. Press any key to continue.";
		}
		else
		{
			cout << "Invalid command. Press any key to continue." << endl;
		}//End if -- End input

		//If not the input of ['Q']
		if (command[initCommand] != qKey)
		{
			_getch();//Pause after output
		}

		memset(command, minValue, commandLength);//Set all values in command to 0
		LL.updateSeqeunce();//<-Updates the sequences after any possible change might have occured
		clearScreen();//<-Clears the terminal
	}//End while loop
}//End commandRoutine

void ui::trimArray()
{
	//If any of the ASCII values are matched by the value of command[0]
	if (command[initCommand] == qKey || command[initCommand] == eKey || command[initCommand] == dKey ||
		command[initCommand] == rKey)
	{
		command[selectionDigit] = nullChar;//Set [1][2] to null
		command[extraDigit] = nullChar;
	}
	else if (command[initCommand] == gKey && command[selectionDigit] != nullChar)//if input matches ['G']['x']
	{
		command[extraDigit] = nullChar;//Set [2] to null
	}
}//End trimArray

void ui::clearScreen()
{
	HANDLE                     hStdOut;
	CONSOLE_SCREEN_BUFFER_INFO csbi;
	DWORD                      count;
	DWORD                      cellCount;
	COORD                      homeCoords = { 0, 0 };

	hStdOut = GetStdHandle(STD_OUTPUT_HANDLE);
	if (hStdOut == INVALID_HANDLE_VALUE) return;

	//Get the cell count of the current buffer
	if (!GetConsoleScreenBufferInfo(hStdOut, &csbi)) return;
	cellCount = csbi.dwSize.X *csbi.dwSize.Y;

	//Populate the buffer with spaces
	if (!FillConsoleOutputCharacter(hStdOut, (TCHAR) ' ', cellCount, homeCoords, &count)) return;

	//Fill buffer with attributes
	if (!FillConsoleOutputAttribute(hStdOut, csbi.wAttributes, cellCount, homeCoords, &count)) return;

	//Move cursor to home
	SetConsoleCursorPosition(hStdOut, homeCoords);
}//End clearScreen

int ui::concatenateInt(int x, int y)
{
	int concatenation = minValue;
	int power = 1;

	while (power <= y) power *= 10;//Raise y to a power by getting the product of its factors
	concatenation = (x * power) + y;//The concatenated int value is x * the power of y plus the original value of y
	
	//In the instance that y only has one factor, the previous logic will perform (int) x + 0, and only the first digit will be copied
	if (y == minValue)
	{
		//Multiply the first digit by 10
		concatenation = concatenation * 10;
	}

	return concatenation;
}//End concatenateInt