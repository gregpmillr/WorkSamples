//Header: Zombie
#ifndef _World_H
#define _World_H

#include "World.h"
#include "Organism.h"

class Zombie : public Organism//Prototype for zombie object : extends organism
{
protected:
	int starvation = 0;//Increments each time a zombie fails to eat
	
public:
	//Constructor(s), Destructor(s)
	Zombie();
	Zombie(World *world, int width, int height);
	~Zombie();

	//Member methods
	virtual speciesType getSpecies();
	virtual void move();
	virtual void spawn();
	void starveThisZombie();
	int getStarvation();
	void setStarvation(int starveSteps);
};
#endif
//End Header: Zombie
