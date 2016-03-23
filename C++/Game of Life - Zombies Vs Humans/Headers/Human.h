//Header: Human
#ifndef _Human_H
#define _Human_H

#include "World.h"
#include "Organism.h"

class Human : public Organism//Prototype for zombie object : extends organism
{
public:
	//Constructor(s), Destructor(s)
	Human(); 
	Human(World *world, int xPos, int yPos);
	virtual ~Human();
	
	//Member methods
	virtual speciesType getSpecies();
	virtual void move();
	virtual void spawn();
};
#endif
//End Header: Human
