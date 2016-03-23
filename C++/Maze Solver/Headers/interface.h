#ifndef _INTERFACE_H
#define _INTERFACE_H

#include "stack.h"
#include "maze.h"

using namespace std;


class Interface
{

private:
	//properties
	Stack stack;
	Maze *maze;

public:
	Interface();
	void Start(char txtFileName[]);//set up grid, place first blip
	void DisplayMaze(); //displays and cleans up maze

};
#endif

