#ifndef _MAZE_H
#define _MAZE_H

#include <iostream>
#include <fstream>
#include "stack.h"


using namespace std;

enum choiceMoves { UP, DOWN, LEFT, RIGHT, NONE };
class Maze
{

private:
	//properties
	char* txtFileName;
	char **grid;
	int _numRows;
	int _numColumns;
	char temp;
	int x;
	int y;
	int lastCoordinates[2];

	ifstream file;
	myerror_code err;//for simple error handling
	choiceMoves move; //next available move for blip

public:
	//constructor
	Maze(char txtInputFileName[]);

	void SetGrid();
	void SetUpMaze();
	void PlaceFirstBlip(Stack &stack);
	int GetRows();
	int GetColumns();
	char** GetGrid();
	bool MoveBlip(Stack &stack);
	void SetMove();
	void GetLast();
	bool CheckStop(Stack &stack);
};

#endif
