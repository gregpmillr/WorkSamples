//Class: Zombie
#include "Zombie.h"
#include "Human.h"

Zombie::Zombie() : Organism(){}//Default constructor

Zombie::Zombie(World* thisWorld, int xPos, int yPos) : Organism(thisWorld, xPos, yPos){}//Custom constructor

Zombie::~Zombie(){}//Default destructor

speciesType Zombie::getSpecies()//Accessor for the objects species type
{
	return ZOMBIE;//Return enum->ZOMBIE
}//End accessor

int Zombie::getStarvation()
{
	return this->starvation;
}

void Zombie::setStarvation(int starveSteps)
{
	this->starvation = starveSteps;
}

void Zombie::move()//Move method for zombie objects
{
	vector<int> adjacentHumans;//Vector stores sequence of possible human targets

	//If a target is in bounds, not a null cell and contains an instance of human:
	if ((y - 1 >= 0) && (world->getOrganism(x, y - 1) != NULL) && (world->getOrganism(x, y - 1)->getSpecies() == HUMAN))
	{
		adjacentHumans.push_back(UP);//This move is pushed into the sequences of targets
	}
	if ((y + 1 < ARRAYSIZE) && (world->getOrganism(x, y + 1) != NULL) && (world->getOrganism(x, y + 1)->getSpecies() == HUMAN))
	{
		adjacentHumans.push_back(DOWN);
	}
	if ((x + 1 < ARRAYSIZE) && (world->getOrganism(x + 1, y) != NULL) && (world->getOrganism(x + 1, y)->getSpecies() == HUMAN))
	{
		adjacentHumans.push_back(RIGHT);
	}
	if ((x - 1 >= 0) && (world->getOrganism(x - 1, y) != NULL) && (world->getOrganism(x - 1, y)->getSpecies() == HUMAN))
	{
		adjacentHumans.push_back(LEFT);
	}
	if ((y - 1 >= 0) && (x + 1 < ARRAYSIZE) && (world->getOrganism(x + 1, y - 1) != NULL) && (world->getOrganism(x + 1, y - 1)->getSpecies() == HUMAN))
	{
		adjacentHumans.push_back(UPRIGHT);
	}
	if ((y + 1 < ARRAYSIZE) && (x + 1 < ARRAYSIZE) && (world->getOrganism(x + 1, y + 1) != NULL) && (world->getOrganism(x + 1, y + 1)->getSpecies() == HUMAN))
	{
		adjacentHumans.push_back(DOWNRIGHT);
	}
	if ((y - 1 >= 0) && (x - 1 >= 0) && (world->getOrganism(x - 1, y - 1) != NULL) && (world->getOrganism(x - 1, y - 1)->getSpecies() == HUMAN))
	{
		adjacentHumans.push_back(UPLEFT);
	}
	if ((y + 1 < ARRAYSIZE) && (x - 1 >= 0) && (world->getOrganism(x - 1, y + 1) != NULL) && (world->getOrganism(x - 1, y + 1)->getSpecies() == HUMAN))
	{
		adjacentHumans.push_back(DOWNLEFT);
	}//End if

	if (!adjacentHumans.empty())//If the vector contains a sequence
	{
		this->setStarvation(0);//Zombie eats and starve defaultPopulation is set to default
	}
	else//Otherwise, find a valid cell set to null
	{
		int starveSteps = this->getStarvation();//Zombie doesn't eat, increment, increment starvation for this step
		this->setStarvation(starveSteps + 1);

		//Since the zombie did not eat, check for a null cell that is adjacent to x, y
		if ((y - 1 >= 0) && (world->getOrganism(x, y - 1) == NULL))
		{
			adjacentHumans.push_back(UP);//If this adjacent sell is null, store its direction in the sequence
		}
		if ((y + 1 < ARRAYSIZE) && (world->getOrganism(x, y + 1) == NULL))
		{
			adjacentHumans.push_back(DOWN);
		}
		if ((x + 1 < ARRAYSIZE) && (world->getOrganism(x + 1, y) == NULL))
		{
			adjacentHumans.push_back(RIGHT);
		}
		if ((x - 1 >= 0) && (world->getOrganism(x - 1, y) == NULL))
		{
			adjacentHumans.push_back(LEFT);
		}//End nested if
	}//End if

	//One a zombie's gameplay logic has executed, updated x, y coordinates are mutated
	if (!adjacentHumans.empty())//If a sequence exists
	{
		int ranNum = rand() % adjacentHumans.size();//Get a random number between 0 and the index of the vector
		int move = adjacentHumans.at(ranNum);//The move play is a random number somewhere on the vectors index

		world->setOrganism(x, y, NULL);//Set this cell to null at this zombie's coordinates

		//If the random number correlates to an adjacent and possible move
		if (move == UP)
		{
			this->setPosition(x, y - 1);//Set objects x, y properties and mutate by the indicated direction
		}
		else if (move == DOWN)
		{
			this->setPosition(x, y + 1);
		}
		else if (move == RIGHT)
		{
			this->setPosition(x + 1, y);
		}
		else if (move == LEFT)
		{
			this->setPosition(x - 1, y);
		}
		else if (move == UPRIGHT)
		{
			this->setPosition(x + 1, y - 1);
		}
		else if (move == DOWNRIGHT)
		{
			this->setPosition(x + 1, y + 1);
		}
		else if (move == UPLEFT)
		{
			this->setPosition(x - 1, y - 1);
		}
		else if (move == DOWNLEFT)
		{
			this->setPosition(x - 1, y + 1);
		}//End nested if

		world->setOrganism(x, y, this);//Set organism in the world at the mutated coordinates
	}//End if

	this->setMoved(true);

	starveThisZombie();
}//End move

void Zombie::spawn()
{
	if (this->getSteps() >= SPAWNZOMBIES)//If this zombie has survived for eight steps in time
	{
		vector<int> adjacentHumans;//Vector holds a sequence of humans in adjacent cells

		//Check adjacent cells for human objects that are in bounds and not null
		if ((y - 1 >= 0) && (world->getOrganism(x, y - 1) != NULL) && (world->getOrganism(x, y - 1)->getSpecies() == HUMAN))
		{
			adjacentHumans.push_back(UP);//This move is pushed into the sequences of targets
		}
		if ((y + 1 < ARRAYSIZE) && (world->getOrganism(x, y + 1) != NULL) && (world->getOrganism(x, y + 1)->getSpecies() == HUMAN))
		{
			adjacentHumans.push_back(DOWN);
		}
		if ((x + 1 < ARRAYSIZE) && (world->getOrganism(x + 1, y) != NULL) && (world->getOrganism(x + 1, y)->getSpecies() == HUMAN))
		{
			adjacentHumans.push_back(RIGHT);
		}
		if ((x - 1 >= 0) && (world->getOrganism(x - 1, y) != NULL) && (world->getOrganism(x - 1, y)->getSpecies() == HUMAN))
		{
			adjacentHumans.push_back(LEFT);
		}
		if ((y - 1 >= 0) && (x + 1 < ARRAYSIZE) && (world->getOrganism(x + 1, y - 1) != NULL) && (world->getOrganism(x + 1, y - 1)->getSpecies() == HUMAN))
		{
			adjacentHumans.push_back(UPRIGHT);
		}
		if ((y + 1 < ARRAYSIZE) && (x + 1 < ARRAYSIZE) && (world->getOrganism(x + 1, y + 1) != NULL) && (world->getOrganism(x + 1, y + 1)->getSpecies() == HUMAN))
		{
			adjacentHumans.push_back(DOWNRIGHT);
		}
		if ((y - 1 >= 0) && (x - 1 >= 0) && (world->getOrganism(x - 1, y - 1) != NULL) && (world->getOrganism(x - 1, y - 1)->getSpecies() == HUMAN))
		{
			adjacentHumans.push_back(UPLEFT);
		}
		if ((y + 1 < ARRAYSIZE) && (x - 1 >= 0) && (world->getOrganism(x - 1, y + 1) != NULL) && (world->getOrganism(x - 1, y + 1)->getSpecies() == HUMAN))
		{
			adjacentHumans.push_back(DOWNLEFT);
		}//End if

		if (!adjacentHumans.empty())//If the list contains a sequence
		{
			int thisRandom = rand() % adjacentHumans.size();//Get a random number between 0 and the index of the sequence
			int convertLocation = adjacentHumans.at(thisRandom);//Convert a human held in the direction stored

			Zombie *convertedZombie = new Zombie(world, ARRAYSIZE, ARRAYSIZE);//Create a new instance of zombie

			//If the direction matches a legal move, mutate the new zombies coordinates
			if (convertLocation == UP)
			{
				convertedZombie->setPosition(x, y - 1);//mutate x, y properties
			}
			else if (convertLocation == DOWN)
			{
				convertedZombie->setPosition(x, y + 1);
			}
			else if (convertLocation == RIGHT)
			{
				convertedZombie->setPosition(x + 1, y);
			}
			else if (convertLocation == LEFT)
			{
				convertedZombie->setPosition(x - 1, y);
			}
			else if (convertLocation == UPRIGHT)
			{
				convertedZombie->setPosition(x + 1, y - 1);
			}
			else if (convertLocation == DOWNRIGHT)
			{
				convertedZombie->setPosition(x + 1, y + 1);
			}
			else if (convertLocation == UPLEFT)
			{
				convertedZombie->setPosition(x - 1, y - 1);
			}
			else if (convertLocation == DOWNLEFT)
			{
				convertedZombie->setPosition(x - 1, y + 1);
			}//End if

			world->setOrganism(convertedZombie->x, convertedZombie->y, NULL);
			world->setOrganism(convertedZombie->x, convertedZombie->y, convertedZombie);//Set this object in the world
			convertedZombie->setMoved(true);
			convertedZombie->setHasSpawned(true);

			this->setSteps(0);//Set steps to default
		}
	}
	else//If zombie hasn't lived over eight steps yet
	{
		int currentSteps = this->getSteps();//increment stepsInTime
		this->setSteps(currentSteps + 1);
	}
}//End spawn

void Zombie::starveThisZombie()//starveThisZombie sets this objects cell to null if the zombie has not eaten over three steps
{
	if (starvation >= STARVEZOMBIE)
	{
		Human* dispelledHuman = new Human(world, x, y);
		dispelledHuman->setPosition(x, y);
		world->setOrganism(x, y, NULL);
		world->setOrganism(x, y, dispelledHuman);
	}//End if
}
//End Class: Zombie