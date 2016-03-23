#include <iostream>
#include "stacknode.h"

using namespace std;

StackNode::StackNode() : _data('#'), _prev(NULL)
{
}
StackNode::StackNode(int row, int column, StackNode* prev) : _row(row),_column(column), _data('#'), _prev(prev)
{
}

StackNode::~StackNode()
{
}

char StackNode::GetData()
{
	return _data;
}

void StackNode::SetData(char data)
{
	_data = data;
}

StackNode* StackNode::GetPrev()
{
	return _prev;
}

void StackNode::SetPrev(StackNode *prev)
{
	_prev = prev;
}

int* StackNode::GetCoordinates()
{
	int *coords = new int[2];
	coords[0] = _row;
	coords[1] = _column;

	return coords;
}
