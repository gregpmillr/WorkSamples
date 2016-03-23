#include "World.h"
using namespace std;

int main()
{
	World thisWorld(time(0));
	thisWorld.display();

	while (1) { 
		thisWorld.step();
		thisWorld.display();
	}

	return 0;
}