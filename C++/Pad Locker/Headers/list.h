//Header file for classes: Node & LinkedList
#ifndef _LIST_H
#define _LIST_H
using namespace std;

//Constants 
const int defaultIndex = -1;
const int sequenceLength = 27;
const int digitLength = 3;

class Node//Node class
{
private:
	//Private properties
	char direction;//Directional value
	int number;//Numerical element
	
public:
	//Public properties
	Node *next;//Points to the next node
	Node();//Default constructor of 'node'

	//Accessors and mutators 
	void setDirection(char thisDirection);
	char getDirection();
	void setNumber(int thisNumber);
	int getNumber();
};//End node class

class LinkedList//LinkedList class
{
private:
	//Private properties
	Node *first;//Pointer to initial node object
public:
	//Public properties
	char *sequence = new char[sequenceLength];//Contains output character sequence

	LinkedList();//Default constructor
	virtual ~LinkedList();//Destructor

	char updateSeqeunce();//Updates character sequence
	bool checkFirstNode();//Check to see if the address of the initial node is null
	int checkLastNode();
	void Add(char direction, int number);//Add another instance of Node
	void replaceData(int listIndex, int newSequence);//Supplant Node->number values;
	void DeleteNode(int listIndex);//Delete an existing istance of Node
	void resetSeqeuence();//Default all elements to 0
	void resetDirections();//Reorder Node->direction on delete
};//End class list
#endif
