//Class: World
#include <iostream>
#include <cstdlib>
#include "World.h"
#include "Human.h"
#include "Zombie.h"
using namespace std;

World::World(int rand)//Default constructor 
{
	srand(rand);//Seed object with random number for the timer

	//Loop through the delimited cells
	for (int x = 0; x < ARRAYSIZE; x++)
	{
		for (int y = 0; y < ARRAYSIZE; y++)
		{
			grid[x][y] = NULL;//Set all cells to null to initialize the array
		}//End nested for 
	}//End for

	//Call populateWorldto populate the array at random 
	populateWorld(); 
}//End default constructor

World::~World(){}//Default destructor

Organism* World::getOrganism(int x, int y) const//Accessor for an instance of an organism
{
	if ((x >= 0) && (x < ARRAYSIZE) && (y >= 0) && (y < ARRAYSIZE))//If the coordinates passed are in bounds
	{
		return grid[x][y];//Return the organism at this position
	}
	else
	{
		return NULL;//Otherwise return null
	}//End if
}//End accessor

void World::setOrganism(int x, int y, Organism* thisOrganism)//Mutator for an instance of organism
{
	if ((x >= 0) && (x < ARRAYSIZE) && (y >= 0) && (y < ARRAYSIZE))//If the coordinates passed are in bounds
	{
		grid[x][y] = thisOrganism;//The species of this object is mutated
	}//End if
}//End mutator

void timer(int seconds)
{
	clock_t endwait;
	endwait = clock() + seconds * CLOCKS_PER_SEC / 1000;
	while (clock() < endwait) {}
}

void World::display() const//Display writes the psuedocode to console
{
	HWND console = GetConsoleWindow();
	RECT r;
	GetWindowRect(console, &r);
	MoveWindow(console, r.left, r.top, 540, 330, TRUE);

	HANDLE  hColor;
	hColor = GetStdHandle(STD_OUTPUT_HANDLE);
	int nHumans = 0, nZombies = 0;

	timer(1000);

	//Loop through all the legal cells
	for (int x = 0; x < ARRAYSIZE; x++)
	{
		for (int y = 0; y < ARRAYSIZE; y++)
		{
			//Write a unique ASCII character depending on null or speciesType
			if (grid[x][y] == NULL)
			{
				SetConsoleTextAttribute(hColor, 8);
				cout << " - ";
			}
			else if (grid[x][y]->getSpecies() == HUMAN)
			{
				SetConsoleTextAttribute(hColor, 2);
				cout << " H ";
				nHumans++;//Increment defaultPopulationer
			}
			else if (grid[x][y]->getSpecies() == ZOMBIE)
			{
				SetConsoleTextAttribute(hColor, 4);
				cout << " Z ";
				nZombies++;//Increment defaultPopulationer
			}//End if
		}//End nested for
		cout << endl;
	}//end for 

	SetConsoleTextAttribute(hColor, 8);
	cout << "Humans: " << nHumans << " Zombies: " << nZombies << endl;//Display defaultPopulationers on each step

	if (nZombies == 0){
		cout << "Zombies eliminated.";
		_getch();
	}
	if (nHumans == 0){
		cout << "Humans eliminated.";
		_getch();
	}

	cout << "Day: " << count << endl;
}//End Display

void World::step()//aStepInTime is the default gameplay routine
{
	setMovedToFalse();
	moveOrganisms();
	spawnOrganisms();
	count++;
}//End step

int World::returnRandom(int thisNumber) const//returnRandom takes a max value 
{
	int indexNumber = rand() % thisNumber;//Stores a random value between zero and the max value

	return indexNumber;//This index number gets returned
}//End returnRandom

void World::populateWorld()//populateWorld instantiates species objects in the constructor
{
	int humanCount = 0;
	int zombieCount = 0;

	do 
	{
		int xRand = returnRandom(ARRAYSIZE);
		int yRand = returnRandom(ARRAYSIZE);
		int randomSteps = returnRandom(4);

		if (getOrganism(xRand, yRand) == NULL)
		{
			Human* newHuman = new Human(this, ARRAYSIZE, ARRAYSIZE);
			newHuman->setPosition(xRand, yRand);
			newHuman->setSteps(randomSteps);
			this->setOrganism(yRand, xRand, newHuman);
			humanCount++;
		}
	} while (humanCount < 100);

	do
	{
		int xRand = returnRandom(ARRAYSIZE);
		int yRand = returnRandom(ARRAYSIZE);
		int randomSteps = returnRandom(9);

		if (getOrganism(xRand, yRand) == NULL)
		{
			Zombie* newZombie = new Zombie(this, ARRAYSIZE, ARRAYSIZE);
			newZombie->setPosition(xRand, yRand);
			newZombie->setSteps(randomSteps);
			this->setOrganism(yRand, xRand, newZombie);
			zombieCount++;
		}
	} while (zombieCount < 5);
}//End create organisms

void World::setMovedToFalse()//setMovedToFalse resets all objects moved property to false on each step
{
	//Loop through all legal cells in the array
	for (int x = 0; x < ARRAYSIZE; x++)
	{
		for (int y = 0; y < ARRAYSIZE; y++)
		{
			//If the cell is not null
			if (grid[x][y] != NULL)
			{
				grid[x][y]->setMoved(false);//Call setMoved on this instance of an organism
				grid[x][y]->setHasSpawned(false);
			}//End if
		}//End nested for
	}//End for
}//End setMovedToFalse

void World::moveOrganisms()//moveOrganism executes the gameplay routines for specific objects
{
	//if zombie, move
	for (int x = 0; x < ARRAYSIZE; x++)
	{
		for (int y = 0; y < ARRAYSIZE; y++)
		{
			//If the cell is not null and contains an instance of zombie
			if ((grid[x][y] != NULL) && grid[x][y]->getMoved() == false && grid[x][y]->getSpecies() == ZOMBIE)
			{
				grid[x][y]->move();
			}//End if
		}//End nested for
	}//End for

	//if human, move
	for (int x = 0; x < ARRAYSIZE; x++)
	{
		for (int y = 0; y < ARRAYSIZE; y++)
		{
			//If the cell is not null and contains an instance of zombie
			if ((grid[x][y] != NULL) && grid[x][y]->getMoved() == false && grid[x][y]->getSpecies() == HUMAN)
			{
				grid[x][y]->move();
			}//End if
		}//End nested for
	}//End for
}//End moveOrganism

void World::spawnOrganisms()
{
	//Loop through all legal cells
	for (int x = 0; x < ARRAYSIZE; x++)
	{
		for (int y = 0; y < ARRAYSIZE; y++)
		{
			//If the cell is not null and contains an instance of human
			if ((grid[x][y] != NULL) && (grid[x][y]->getHasSpawned() == false) && grid[x][y]->getSpecies() == ZOMBIE)
			{
				grid[x][y]->spawn();//Call the human's spawn method
			}//End if
		}//End nested for
	}//End for

	//Loop through all legal cells
	for (int x = 0; x < ARRAYSIZE; x++)
	{
		for (int y = 0; y < ARRAYSIZE; y++)
		{
			//If the cell is not null and contains an instance of human
			if ((grid[x][y] != NULL) && (grid[x][y]->getHasSpawned() == false) && grid[x][y]->getSpecies() == HUMAN)
			{
				grid[x][y]->spawn();//Call the human's spawn method
			}//End if
		}//End nested for
	}//End for
}//End spawnOrganisms
//End Class: World