#ifndef _STACKNODE_H
#define _STACKNODE_H

class StackNode
{
private:
	int _row;
	int _column;
	char _data;
	StackNode *_prev;

public:
	//constructors & destructor
	StackNode();
	StackNode(int _row, int _column, StackNode* next);
	virtual ~StackNode();

	//set & gets for '*'
	char GetData();
	void SetData(char data);

	//get previous node 
	StackNode* GetPrev();
	//set the next node pointer
	void SetPrev(StackNode *prev);

	int* GetCoordinates();
};

#endif
