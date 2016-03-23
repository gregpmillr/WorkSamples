//Header: Organism
#ifndef _Organism_H
#define _Organism_H

#include <iostream>
#include <vector>
#include <time.h>
#include <Windows.h>
#include <stdlib.h>
#include <stdio.h>
#include <time.h>
#include <conio.h>
#include <cstdlib>
#include <ctime>
using namespace std;

enum speciesType {ZOMBIE, HUMAN};//List of species

class World;//World prototype

class Organism//Class world is a prototype of the world where gameplay executes
{
protected:
	//Member properties
	World *world;//An organism exists in this world
	enum moveList { LEFT, UP, RIGHT, DOWN, UPRIGHT, DOWNRIGHT, UPLEFT, DOWNLEFT };//List of adjacent moves
	int x, y, xPos, yPos, stepsInTime;
	bool moved, hasSpawned;

public:
	//Constructor(s), Destructor(s)
	Organism();
	Organism(World *world, int xPos, int yPos);
	virtual ~Organism();
	
	//Absolute virtual methods
	virtual speciesType getSpecies() = 0;
	virtual void move() = 0;
	virtual void spawn() = 0;
	
	//Member methods
	bool getMoved() const;
	void setMoved(bool oneStep);

	bool getHasSpawned();
	void setHasSpawned(bool hasSpawned);

	int getSteps();
	void setSteps(int aStepinTime);

	void setPosition(int x, int y);
};
#endif
//End Header: Organism