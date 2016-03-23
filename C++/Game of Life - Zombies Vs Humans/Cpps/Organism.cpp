//Class: Organism
#include "Organism.h"
#include "World.h"

Organism::Organism()//Default constructor
{
	world = NULL;
	moved = false;
	stepsInTime = 0;
	x = 0; 
	y = 0;
}//End default constructor

Organism::Organism(World* thisWorld, int xPos, int yPos)//Custom constructor
{
	world = thisWorld;
	moved = false;
	stepsInTime = 0;
	x = xPos;
	y = yPos;
}//End custom constructor

Organism::~Organism(){}//Default destructor

bool Organism::getMoved() const//Get the state of organsim->moved
{
	return this->moved;
}//End get method for moved

void Organism::setMoved(bool getMoved)//Set the moved property
{
	this->moved = getMoved;
}//End set method for moved

bool Organism::getHasSpawned()
{
	return this->hasSpawned;
}

void Organism::setHasSpawned(bool getSpawned)
{
	this->hasSpawned = getSpawned;
}

int Organism::getSteps()
{
	return this->stepsInTime;
}

void Organism::setSteps(int setSteps)
{
	this->stepsInTime = setSteps;
}

void Organism::setPosition(int x, int y){//Set method for x, y properties
	this->x = x;
	this->y = y;
}//End set method for x, y
//End Class: Organism