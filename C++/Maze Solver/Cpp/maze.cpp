#include <iostream>
#include "Maze.h"
#include <conio.h>

using namespace std;

bool done = false;

void pr_error(myerror_code err)
// Generic error print routine
{
	switch (err) {
	case overflow: {
		cout << "Error encountered: stack overflow" << endl;
	}
	case underflow: {
		cout << "Error encountered: stack underflow" << endl;
	}
	}
}

Maze::Maze(char txtInputFileName[]) : txtFileName(txtInputFileName){}


void Maze::SetGrid()
{
	file.open(txtFileName);

	//get all chars and store into temp, if it's a linebreak that means a new row, so increment if so.
	while (!file.eof())
	{
		temp = file.get();
		if (temp == '\n') { _numRows++; }
	}
	_numRows = _numRows++; //increment for +1 for correct rows

	bool fileIsDone = false;
	file.clear();
	file.seekg(0, ios::beg);

	//custom routine to replicate simple getLine (sorta)
	while (!file.eof() && !fileIsDone)
	{
		temp = file.get();
		_numColumns++;
		if (temp == '\n') { fileIsDone = true; }
	}

	//allocate the array memory for a maze grid object
	grid = new char*[_numColumns];
	for (int i = 0; i < _numRows; ++i)
	{
		grid[i] = new char[_numColumns];
	}
}

void Maze::SetUpMaze()
{
	int counter = 0;
	int counter2 = 0;
	file.clear();
	file.seekg(0, ios::beg);

	//assign array file to array
	while (!file.eof())
	{
		temp = file.get();
		grid[counter][counter2] = temp;
		counter2++;
		if (temp == '\n') { counter++; counter2 = 0; }
	}
}

void Maze::PlaceFirstBlip(Stack &stack)
{
	//print Maze
	for (int i = 0; i < _numRows; i++)
	{
		for (int j = 0; j < _numColumns; j++)
		{
			if (grid[i][j] == ' ')
			{
				myerror_code err;

				//add node to stack with current position
				err = stack.Push(i, j);
				if (err != success) pr_error(err); // just a sample of catching a returning error code

				int* newCoords[2];

				int* coords = stack.Peek();
				for (int i = 0; i < 2; i++)
				{
					newCoords[i] = &coords[i];
				}
				x = *newCoords[0];
				y = *newCoords[1];
				grid[x][y] = '#';
				break;
			}//end if
			break;
		}
	}//end outer for loop
}

void Maze::SetMove()
{
	//set UP,DOWN,LEFT,RIGHT so that the Blip can move in that direction
	if (grid[x][y-1] == ' ' && y-1 >= 0)
	{
		move = LEFT;
	}
	else if (grid[x][y + 1] == ' ' && y + 1 >= 0)
	{
		move = RIGHT;
	}
	else if (grid[x + 1][y] == ' ' && x + 1 >= 0)
	{
		move = UP;
	}
	else if (x - 1 >= 0  && grid[x - 1][y] == ' ')
	{
		move = DOWN;
	}
	else
	{
		move = NONE;
	}
}


bool Maze::MoveBlip(Stack &stack)
{

	myerror_code err;

	//push stacknode to stack, set coords.
	switch(move){
		case UP: {
			err = stack.Push(x + 1, y);
			int* newCoords[2];

			int* coords = stack.Peek();

			for (int i = 0; i < 2; i++)
			{
				newCoords[i] = &coords[i];
			}
			x = *newCoords[0];
			y = *newCoords[1];
			grid[x][y] = '#';
			if (!CheckStop(stack)){ return true; }

			break;
		}
		case DOWN: {
			err = stack.Push(x - 1, y);
			int* newCoords[2];

			int* coords = stack.Peek();
			for (int i = 0; i < 2; i++)
			{
				newCoords[i] = &coords[i];
			}
			x = *newCoords[0];
			y = *newCoords[1];
			grid[x][y] = '#';
			if (!CheckStop(stack)){ return true; }
			break;
		}
		case RIGHT: {
			err = stack.Push(x, y + 1);
			int* newCoords[2];

			int* coords = stack.Peek();
			for (int i = 0; i < 2; i++)
			{
				newCoords[i] = &coords[i];
			}
			x = *newCoords[0];
			y = *newCoords[1];
			grid[x][y] = '#';
			if (!CheckStop(stack)){ return true; }
			break;
		}
		case LEFT: {
			err = stack.Push(x, y - 1);
			int* newCoords[2];

			int* coords = stack.Peek();
			for (int i = 0; i < 2; i++)
			{
				newCoords[i] = &coords[i];
			}
			x = *newCoords[0];
			y = *newCoords[1];
			grid[x][y] = '#';
			if (!CheckStop(stack)){ return true; }
			break;
		}
		case NONE: {
			grid[x][y] = '*';//place breadcrumb

			err = stack.Pop();//backtrack to the next move with a space character

					int* newCoords[2];

					int* coords = stack.Peek();
					for (int i = 0; i < 2; i++)
					{
						newCoords[i] = &coords[i];
					}
					x = *newCoords[0];
					y = *newCoords[1];
					if (!CheckStop(stack)){ return true; }
			break;
		}
	}//end switch
	return false;
}

//gets coordinates for last move of blip
void Maze::GetLast()
{
	bool broken = false;
	//loop from the end
	for (int row = _numRows; row > 1; row--)
	{
		for (int col = _numColumns; col > 1; col--)
		{
			if (grid[row - 1][col - 1] == ' ')
			{
				lastCoordinates[0] = row-1;
				lastCoordinates[1] = col-1;

				broken = true;
				break;
			}if (broken){ break; }
		}if (broken){ break; }
	}//end outer for
}

//check to see if blip has finished looking for math
bool Maze::CheckStop(Stack &stack)
{
	int* coords = stack.Peek();
	int currentNodeCoords[2];
	int lastCoords[2];

	for (int i = 0; i < 2; i++)
	{
		currentNodeCoords[i] = coords[i];

	}

	for (int i = 0; i < 2; i++)
	{
		lastCoords[i] = lastCoordinates[i];
	}

	//compare current coordinates with the correct finishing cell's coordinates
	if (currentNodeCoords[0] == lastCoords[0] && currentNodeCoords[1] == lastCoords[1])
	{
		return true;
	}

	return false;

}


char** Maze::GetGrid()
{
	return this->grid;
}

int Maze::GetRows(){return _numRows;}
int Maze::GetColumns(){return _numColumns;}
