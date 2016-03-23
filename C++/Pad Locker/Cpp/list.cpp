//Source for list.h
#include "list.h"
#include "interface.h"

Node::Node() : next(NULL),  direction('R'), number(0) {}

void Node::setDirection(char thisDirection)
{
	this->direction = thisDirection;
}//End setDirection

char Node::getDirection()
{
	return this->direction;
}//End getDirection

void Node::setNumber(int thisNumber)
{
	this->number = thisNumber;
}//End setNumber

int Node::getNumber()
{
	return this->number;
}//End getNumber

LinkedList::LinkedList() : first(NULL) {}

LinkedList::~LinkedList()
{
	Node *node = first;//Create a reference to the initial node object

	//While a Node exists
	while (node != NULL)
	{
		Node *temp = node;//Copy the current node
		node = node->next;//Copy the object at this->next* 

		delete temp;//Delete copy
	}//End while
}//End destructor

char LinkedList::updateSeqeunce()
{
	//Set default values
	int index = minValue;
	int thisNum = minValue;
	char digits[digitLength];

	Node *node = first;//Create a reference to the initial object
	Node *prev = NULL;//Null pointer

	memset(sequence, 0, sequenceLength);//Clear contents of sequence c string

	//While a Node exists
	while (node != NULL)
	{
		thisNum = node->getNumber();//Get Node->number
		_snprintf_s(digits, sizeof(digits), "%d", thisNum);//Cast the element to a char array
		
		//Copy Node data to the sequence c string, increment each time
		sequence[index++] = node->getDirection();
		sequence[index++] = digits[minValue];
		sequence[index++] = digits[selectionDigit];
		sequence[index++] = ' ';

		prev = node;//Prev becomes a copy of node
		node = node->next;//Node becomes a copy for the next node
	}//End while

	return sequence[index];
}//End fetch sequence

bool LinkedList::checkFirstNode()
{
	Node *node = first;//Copy the initial node

	//If the copy points to NULL
	if (node == NULL)
	{
		return true;//Return true
	}

	return false;
}//End checkFirstNode()

int LinkedList::checkLastNode()
{
	int index = minValue;//Set default value

	Node *node = first;//Point to first
	Node *prev = NULL;//Null pointer

	while (node != NULL)
	{
		index++;//Increment while Node objects exist

		prev = node;
		node = node->next;
	}

	return index;
}//End checkLastNode

void LinkedList::Add(char direction, int number)
{
	//Create a new Node object and set the values passed in the parameters
	Node *node = new Node();
	node->setDirection(direction);
	node->setNumber(number);

	//If there are no node objects in memory
	if (first == NULL)
	{
		first = node;//This is the first Node put into memory
	}
	else
	{
		Node *currNode = first;//If there is an initial Node, copy it
		Node *prevNode = NULL;//Null pointer 

		//While a node exists
		while (currNode != NULL)
		{
			prevNode = currNode;//Copy the current node at the location of the null pointer
			currNode = currNode->next;//Point to a new address in memory
		}//End while

		//If this isn't the first Node
		if (prevNode != NULL)
		{
			prevNode->next = node;//Set the copy of this node to the same address in memory
		}//End nested if
	}//End if
}//End Add

void LinkedList::replaceData(int indexSelected, int newNumber)
{
	int index = defaultIndex;//Set the index number to default

	Node *node = first;
	Node *prev = NULL;

	while (node != NULL)
	{
		index++;//Increment while Node objects exist

		if (index == indexSelected)//If index matches the index number input
		{
			break;//Break out of loop, continue subroutine
		}

		prev = node;//Copy node at the location of the null pointer
		node = node->next;//Point to a new address
	}//End while

	//If index is larger than zero
	if (index >= minValue)
	{
		node->setNumber(newNumber);//Set Node->number to input value
	}//End if
}//End replaceData

void LinkedList::DeleteNode(int indexSelected)
{
	int index = defaultIndex;//Set index to default

	Node *node = first;//Point to initial Node object
	Node *prev = NULL;//Null pointer

	while (node != NULL)
	{
		index++;//Increment while Node objects exist

		if (index == indexSelected)
		{
			break;//Break on match
		}

		prev = node;//Copy this node
		node = node->next;//Point to new address
	}//End while

	//If the value of index is in range
	if (index >= minValue && node != NULL)
	{
		//If this is the first node
		if (node == first)
		{
			first = node->next;//Copy the next node at the location of the initial object
		}
		else
		{
			prev->next = node->next;//Otherwise, the copy of this object needs to point to next* encapsulated by node
		}
		
		delete node;//Once the object has been copied, delete it
		resetDirections();//<-Defaults the value of Node->direction
	}//End if
}//End DeleteNode

void LinkedList::resetSeqeuence()
{
	Node *node = first;
	Node *prev = NULL;

	while (node != NULL)
	{
		//While Node objects exist, set Node->number to 0
		node->setNumber(minValue); 

		prev = node;
		node = node->next;
	}//End while
}//End fetchSequence

void LinkedList::resetDirections()
{
	int index = minValue;//Set default value

	Node *node = first;
	Node *prev = NULL;
	
	//While Node objects exist
	while (node != NULL)
	{
		index++;//Increment the value of index by 1

		//If the square root of index has a remainder of 0
		if (index % 2 == 0)
		{
			//Set Node->Direction to left
			node->setDirection('L');
		}
		else
		{
			//Set Node->Direction to right
			node->setDirection('R');
		}//End if

		prev = node;
		node = node->next;
	}//End while
}//End resetDirections

