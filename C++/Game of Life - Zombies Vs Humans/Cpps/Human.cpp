//Class: Human
#include "Human.h"

Human::Human() : Organism(){}//Default constructor

Human::Human(World* thisWorld, int xPos, int yPos) : Organism(thisWorld, xPos, yPos){}//Custom Constructor

Human::~Human(){}//Default constructor

speciesType Human::getSpecies()//Accessor for the objects species type
{
	return HUMAN;//Return enum->HUMAN
}//End accessor

void Human::move()//Move method for human objects
{
	vector<int> recruitPoints;//Vector holds a sequence of movement by direction
	srand(time(NULL));

	//If an adjacent cell is null
	if ((y - 1 >= 0) && (world->getOrganism(x, y - 1) == NULL))
	{
		recruitPoints.push_back(UP);//Write the corresponding direction in the sequence
	}
	if ((y + 1 < ARRAYSIZE) && (world->getOrganism(x, y + 1) == NULL))
	{
		recruitPoints.push_back(DOWN);
	}
	if ((x + 1 < ARRAYSIZE) && (world->getOrganism(x + 1, y) == NULL))
	{
		recruitPoints.push_back(RIGHT);
	}
	if ((x - 1 >= 0) && (world->getOrganism(x - 1, y) == NULL))
	{
		recruitPoints.push_back(LEFT);
	}

	if (!recruitPoints.empty())	//If a sequence is written
	{
		int ranNum = rand() % recruitPoints.size();//Get a random number between 0 and the index of the vector
		int move = recruitPoints.at(ranNum);//This number at the index of the vector is the move to be made

		world->setOrganism(x, y, NULL);//Set the current cell in the world to null

		//Mutate x, y properties of object where the index number equates to the direction
		if (move == UP){
			this->setPosition(x, y - 1);
		}
		else if (move == DOWN){
			this->setPosition(x, y + 1);
		}
		else if (move == RIGHT){
			this->setPosition(x + 1, y);
		}
		else if (move == LEFT){
			this->setPosition(x - 1, y);
		}
		world->setOrganism(x, y, this);//Set the mutuated object inside the world
	}
	
	this->setMoved(true);
}//End move
void Human::spawn()//Spawn method for human
{
	//If a human survives for three steps
	if (this->getSteps() > 2)
	{
		vector<int> recruitPoints;//Vector holds a sequence of possible spawning points
		

		if ((y - 1 >= 0) && (world->getOrganism(x, y - 1) == NULL))
		{
			recruitPoints.push_back(UP);//Write to sequence
		}
		if ((y + 1 < ARRAYSIZE) && (world->getOrganism(x, y + 1) == NULL))
		{
			recruitPoints.push_back(DOWN);
		}
		if ((x + 1 < ARRAYSIZE) && (world->getOrganism(x + 1, y) == NULL))
		{
			recruitPoints.push_back(RIGHT);
		}
		if ((x - 1 >= 0) && (world->getOrganism(x - 1, y) == NULL))
		{
			recruitPoints.push_back(LEFT);
		}

		if (!recruitPoints.empty())//If a sequence was written
		{
			int ranNum = rand() % recruitPoints.size();//Get a random number between zero and the index of the vector
			int breedLocation = recruitPoints.at(ranNum);//Store an index number at random

			Human *newHuman = new Human(world, ARRAYSIZE, ARRAYSIZE);//Create a new instance of human

			//Mutate the objects x, y properties where the direction equates to the random index number
			if (breedLocation == UP)
			{
				this->setPosition(x, y - 1);//Mutate x, y properties
			}
			else if (breedLocation == DOWN)
			{
				this->setPosition(x, y + 1);
			}
			else if (breedLocation == RIGHT)
			{
				this->setPosition(x + 1, y);
			}
			else if (breedLocation == LEFT)
			{
				this->setPosition(x - 1, y);
			}//End nested if

			world->setOrganism(newHuman->x, newHuman->y, newHuman);//Set the mutuated object inside the world
			newHuman->setMoved(true);
			newHuman->setHasSpawned(true);
		}//End nested if

		this->setSteps(0);//Set stepsInTime to default, as this object successfully recruited
	}
	else//Otherwise they have not survived for three steps yet
	{
		int currentSteps = this->getSteps();//increment stepsInTime
		this->setSteps(currentSteps + 1);
	}//End if
}//End spawn
//End Class: Human