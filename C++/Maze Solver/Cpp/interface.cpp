#include "interface.h"

#include <conio.h>
#include <time.h>

Interface::Interface()
{
}


void Interface::Start(char txtFileName[])
{
	//set up grid
	maze = new Maze(txtFileName);
	maze->SetGrid(); //gets rows, columns, creates grid and initializes
	maze->SetUpMaze(); //set grid specific char cells from text file
	maze->GetLast(); //set last move to show when finished
	maze->PlaceFirstBlip(this->stack);

	bool keepGoing = true;

	while (keepGoing)
	{	
		maze->SetMove();//set the next move for the blip, then move.
		keepGoing = maze->MoveBlip(stack);
	}
	DisplayMaze();//display path, clean up
}




void Interface::DisplayMaze()
{
	//assign grid, to a temporary grid, display it.
	char** tempGrid = new char*[maze->GetColumns()];
	tempGrid = maze->GetGrid();
	for (int i = 0; i < maze->GetRows(); i++)
	{
		for (int j = 0; j < maze->GetColumns(); j++)
		{
			//clean up breadcrumbs
			if (tempGrid[i][j] == '*')
			{
				tempGrid[i][j] = ' ';
			}
			cout << tempGrid[i][j];
		}
	}//end outer for
	cout << endl << endl;
}
