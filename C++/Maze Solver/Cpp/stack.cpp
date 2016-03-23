#include <iostream>
#include "stacknode.h"
#include "stack.h"

using namespace std;


Stack::Stack() : _top(NULL)
{
}

Stack::~Stack()
{
	while (_top != NULL)
	{
		Pop();
	}
}

myerror_code Stack::Push(int row, int col)
{

	_top = new StackNode(row, col,_top);
	return(success);
}

int* Stack::Peek()
{
	return _top->GetCoordinates();
}

myerror_code Stack::Pop()
{
	if (_top != NULL) {
		StackNode* node = _top;//Get old the top node 
		_top = _top->GetPrev();//set new top node to old _top's next node
		delete node;//delete the old top node
		return(success);
	}
	else {
		return(underflow);
	}
}

ostream& operator<<(ostream& output, Stack& stack)
{
	StackNode *node = stack._top;

	while (node != NULL)
	{
		cout << node->GetData() << endl;
		node = node->GetPrev();
	}

	return output;
}

