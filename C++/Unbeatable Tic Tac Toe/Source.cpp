#include <iostream>
#include <string>
#include <conio.h>
using namespace std;

//initialize 2d array for board
char boardArray[3][3] = { { '1', '2', '3' }, { '4', '5', '6' }, { '7', '8', '9' } };
bool win = false;
bool playerSelection = false;
char firstPlayer;
char playerMove;



void dispArray() {
	//build the 1st row of the board
	cout << boardArray[0][0] << "|";
	cout << boardArray[0][1] << "|";
	cout << boardArray[0][2] << "\n";

	cout << "------" << "\n";

	cout << boardArray[1][0] << "|";
	cout << boardArray[1][1] << "|";
	cout << boardArray[1][2] << "\n";

	cout << "------" << "\n";
	cout << boardArray[2][0] << "|";
	cout << boardArray[2][1] << "|";
	cout << boardArray[2][2] << "\n";
}

void movePlayer() {
	bool playerMoveValid = false;
	while (!playerMoveValid)
	{
		cout << "Choose a number" << endl;
		cin >> playerMove;
		if (isdigit(playerMove) && playerMove != '0'){
			playerMoveValid = true;
			if (playerMove == '1')
				boardArray[0][0] = 'X';
			else if (playerMove == '2')
				boardArray[0][1] = 'X';
			else if (playerMove == '3')
				boardArray[0][2] = 'X';
			else if (playerMove == '4')
				boardArray[1][0] = 'X';
			else if (playerMove == '5')
				boardArray[1][1] = 'X';
			else if (playerMove == '6')
				boardArray[1][2] = 'X';
			else if (playerMove == '7')
				boardArray[2][0] = 'X';
			else if (playerMove == '8')
				boardArray[2][1] = 'X';
			else if (playerMove == '9')
				boardArray[2][2] = 'X';
		}//end if
		else{
			cout << "That's not a valid input." << endl;
		}
	}//end while
}//end move

void aiWins(){
	cout << endl << "You Lost!" << endl;
	_getch();
}

void aiMove() {

	//check if AI can win
	//sides
	if (boardArray[0][0] == 'O' && boardArray[0][1] == 'O' && boardArray[0][2] == '3')
	{
		boardArray[0][2] = 'O';
		aiWins();
		win = true;
	}
	else if (boardArray[1][0] == 'O' && boardArray[1][1] == 'O' && boardArray[1][2] == '6')
	{
		boardArray[1][2] = 'O';
		aiWins();
		win = true;
	}
	else if (boardArray[2][0] == 'O' && boardArray[2][1] == 'O' && boardArray[2][2] == '9')
	{
		boardArray[2][2] = 'O';
		aiWins();
		win = true;
	}
	else if (boardArray[0][2] == 'O' && boardArray[0][1] == 'O' && boardArray[0][0] == '1')
	{
		boardArray[0][0] = 'O';
		aiWins();
		win = true;
	}
	else if (boardArray[1][2] == 'O' && boardArray[1][1] == 'O' && boardArray[1][0] == '4')
	{
		boardArray[1][0] = 'O';
		aiWins();
		win = true;
	}
	else if (boardArray[2][2] == 'O' && boardArray[2][1] == 'O' && boardArray[2][0] == '7')
	{
		boardArray[2][0] = 'O';
		aiWins();
		win = true;
	}

	//up and down
	else if (boardArray[0][0] == 'O' && boardArray[1][0] == 'O' && boardArray[2][0] == '7')
	{
		boardArray[2][0] = 'O';
		aiWins();
		win = true;
	}
	else if (boardArray[0][1] == 'O' && boardArray[1][1] == 'O' && boardArray[2][1] == '8')
	{
		boardArray[2][1] = 'O';
		aiWins();
		win = true;
	}
	else if (boardArray[0][2] == 'O' && boardArray[1][2] == 'O' && boardArray[2][2] == '9')
	{
		boardArray[2][2] = 'O';
		aiWins();
		win = true;
	}
	else if (boardArray[2][0] == 'O' && boardArray[1][0] == 'O' && boardArray[0][0] == '1')
	{
		boardArray[0][0] = 'O';
		aiWins();
		win = true;
	}
	else if (boardArray[2][1] == 'O' && boardArray[1][1] == 'O' && boardArray[0][1] == '2')
	{
		boardArray[0][1] = 'O';
		aiWins();
		win = true;
	}
	else if (boardArray[2][2] == 'O' && boardArray[1][2] == 'O' && boardArray[0][2] == '3')
	{
		boardArray[0][2] = 'O';
		aiWins();
		win = true;
	}

	//diagonal
	//top left
	else if (boardArray[0][0] == 'O' && boardArray[1][1] == 'O' && boardArray[2][2] == '9')
	{
		boardArray[2][2] = 'O';
		aiWins();
		win = true;
	}
	else if (boardArray[2][2] == 'O' && boardArray[1][1] == 'O' && boardArray[0][0] == '1')
	{
		boardArray[0][0] = 'O';
		aiWins();
		win = true;
	}
	//top right
	else if (boardArray[0][2] == 'O' && boardArray[1][1] == 'O' && boardArray[2][0] == '7')
	{
		boardArray[2][0] = 'O';
		aiWins();
		win = true;
	}
	else if (boardArray[2][0] == 'O' && boardArray[1][1] == 'O' && boardArray[0][2] == '3')
	{
		boardArray[0][2] = 'O';
		aiWins();
		win = true;
	}
	//bottom left
	else if (boardArray[0][2] == 'O' && boardArray[1][1] == 'O' && boardArray[2][0] == '7')
	{
		boardArray[2][0] = 'O';
		aiWins();
		win = true;
	}


	//middles
	//top
	else if (boardArray[0][0] == 'O' && boardArray[0][2] == 'O' && boardArray[0][1] == '2')
	{
		boardArray[0][1] = 'O';
		aiWins();
		win = true;
	}
	//middle
	else if (boardArray[1][0] == 'O' && boardArray[1][2] == 'O' && boardArray[1][1] == '5')
	{
		boardArray[1][1] = 'O';
		aiWins();
		win = true;
	}
	//bottom
	else if (boardArray[2][0] == 'O' && boardArray[2][2] == 'O' && boardArray[2][1] == '8')
	{
		boardArray[2][1] = 'O';
		aiWins();
		win = true;
	}

	//up and down
	else if (boardArray[0][0] == 'O' && boardArray[2][0] == 'O' && boardArray[1][0] == '4')
	{
		boardArray[1][0] = 'O';
		aiWins();
		win = true;
	}
	else if (boardArray[0][1] == 'O' && boardArray[2][1] == 'O' && boardArray[1][1] == '5')
	{
		boardArray[1][1] = 'O';
		aiWins();
		win = true;
	}
	else if (boardArray[0][2] == 'O' && boardArray[2][2] == 'O' && boardArray[1][2] == '6')
	{
		boardArray[1][2] = 'O';
		aiWins();
		win = true;
	}

	//diagonal
	else if (boardArray[0][0] == 'O' && boardArray[2][2] == 'O' && boardArray[1][1] == '5')
	{
		boardArray[1][1] = 'O';
		aiWins();
		win = true;
	}
	else if (boardArray[0][2] == 'O' && boardArray[2][0] == 'O' && boardArray[1][1] == '5')
	{
		boardArray[1][1] = 'O';
		aiWins();
		win = true;
	}





	//check if AI can block
	if (boardArray[0][0] == 'X' && boardArray[0][1] == 'X' && boardArray[0][2] == '3')
	{
		boardArray[0][2] = 'O';
	}
	else if (boardArray[1][0] == 'X' && boardArray[1][1] == 'X' && boardArray[1][2] == '6')
	{
		boardArray[1][2] = 'O';
	}
	else if (boardArray[2][0] == 'X' && boardArray[2][1] == 'X'  && boardArray[2][2] == '9')
	{
		boardArray[2][2] = 'O';
	}
	else if (boardArray[0][2] == 'X' && boardArray[0][1] == 'X'  && boardArray[0][0] == '1')
	{
		boardArray[0][0] = 'O';
	}
	else if (boardArray[1][2] == 'X' && boardArray[1][1] == 'X'  && boardArray[1][0] == '4')
	{
		boardArray[1][0] = 'O';
	}
	else if (boardArray[2][2] == 'X' && boardArray[2][1] == 'X'  && boardArray[2][0] == '7')
	{
		boardArray[2][0] = 'O';
	}

	//up and down
	else if (boardArray[0][0] == 'X' && boardArray[1][0] == 'X'  && boardArray[2][0] == '7')
	{
		boardArray[2][0] = 'O';
	}
	else if (boardArray[0][1] == 'X' && boardArray[1][1] == 'X'  && boardArray[2][1] == '8')
	{
		boardArray[2][1] = 'O';
	}
	else if (boardArray[0][2] == 'X' && boardArray[1][2] == 'X'  && boardArray[2][2] == '9')
	{
		boardArray[2][2] = 'O';
	}
	else if (boardArray[2][0] == 'X' && boardArray[1][0] == 'X'  && boardArray[0][0] == '1')
	{
		boardArray[0][0] = 'O';
	}
	else if (boardArray[2][1] == 'X' && boardArray[1][1] == 'X'  && boardArray[0][1] == '2')
	{
		boardArray[0][1] = 'O';
	}
	else if (boardArray[2][2] == 'X' && boardArray[1][2] == 'X'  && boardArray[0][2] == '3')
	{
		boardArray[0][2] = 'O';
	}

	//diagonal
	//top left
	else if (boardArray[0][0] == 'X' && boardArray[1][1] == 'X'  && boardArray[2][2] == '9')
	{
		boardArray[2][2] = 'O';
	}
	else if (boardArray[2][2] == 'X' && boardArray[1][1] == 'X'  && boardArray[0][0] == '1')
	{
		boardArray[0][0] = 'O';
	}
	//top right
	else if (boardArray[0][2] == 'X' && boardArray[1][1] == 'X'  && boardArray[2][0] == '7')
	{
		boardArray[2][0] = 'O';
	}
	else if (boardArray[2][0] == 'X' && boardArray[1][1] == 'X'  && boardArray[0][2] == '3')
	{
		boardArray[0][2] = 'O';
	}


	//middles
	//top
	else if (boardArray[0][0] == 'X' && boardArray[0][2] == 'X'  && boardArray[0][1] == '2')
	{
		boardArray[0][1] = 'O';
	}
	//middle
	else if (boardArray[1][0] == 'X' && boardArray[1][2] == 'X'  && boardArray[1][1] == '5')
	{
		boardArray[1][1] = 'O';
	}
	//bottom
	else if (boardArray[2][0] == 'X' && boardArray[2][2] == 'X'  && boardArray[2][1] == '8')
	{
		boardArray[2][1] = 'O';
	}

	//up and down
	else if (boardArray[0][0] == 'X' && boardArray[2][0] == 'X'  && boardArray[1][0] == '4')
	{
		boardArray[1][0] = 'O';
	}
	else if (boardArray[0][1] == 'X' && boardArray[2][1] == 'X'  && boardArray[1][1] == '5')
	{
		boardArray[1][1] = 'O';
	}
	else if (boardArray[0][2] == 'X' && boardArray[2][2] == 'X'  && boardArray[1][2] == '6')
	{
		boardArray[1][2] = 'O';
	}

	//diagonal
	else if (boardArray[0][0] == 'X' && boardArray[2][2] == 'X'  && boardArray[1][1] == '5')
	{
		boardArray[1][1] = 'O';
	}
	else if (boardArray[0][2] == 'X' && boardArray[2][0] == 'X'  && boardArray[1][1] == '5')
	{
		boardArray[1][1] = 'O';
	}
	//exception for diagonal with space in middle
	else if (boardArray[0][0] == 'X' && boardArray[2][2] == 'X'  && boardArray[1][1] == 'O')
	{
		boardArray[1][0] = 'O';
	}
	else if (boardArray[2][0] == 'X' && boardArray[0][2] == 'X'  && boardArray[1][1] == 'O')
	{
		boardArray[1][0] = 'O';
	}



	//if cant block or win, then pick middle or corners
	//middle
	else if (boardArray[1][1] == '5')
	{
		boardArray[1][1] = 'O';
	}

	else if (boardArray[0][0] == '1')
	{
		boardArray[0][0] = 'O';
	}

	else if (boardArray[0][2] == '3')
	{
		boardArray[0][2] = 'O';
	}

	else if (boardArray[2][0] == '7')
	{
		boardArray[2][0] = 'O';
	}

	else if (boardArray[2][2] == '9')
	{
		boardArray[2][2] = 'O';
	}
	else if (boardArray[0][1] == '2')
	{
		boardArray[0][1] = 'O';
	}
	else if (boardArray[1][0] == '4')
	{
		boardArray[1][0] = 'O';
	}
	else if (boardArray[1][2] == '6')
	{
		boardArray[1][2] = 'O';
	}
		else if (boardArray[2][1] == '8')
	{
		boardArray[2][1] = 'O';
	}
}//end aimove



void tie(){
	if (boardArray[0][0] != '1' && boardArray[0][1] != '2' && boardArray[0][2] != '3'
		&& boardArray[1][0] != '4' && boardArray[1][1] != '5' && boardArray[1][2] != '6' 
		&& boardArray[2][0] != '7' && boardArray[2][1] != '8' && boardArray[2][2] != '9')
	{
		cout << "It's a tie!" << endl;
		win = true;
		_getch();
	}
}



void main() {
	while (playerSelection == false){
		cout << "Do you want to go first? y/n";
		cin >> firstPlayer;

		if (firstPlayer == 'y')
		{
			playerSelection = true;
			while (!win) {
				dispArray();
				movePlayer();
				aiMove();
				tie();
				
			}//end while
		}//end if
		else if (firstPlayer == 'n')
		{
			playerSelection = true;
			boardArray[0][0] = 'O';
			while (!win) {
				dispArray();
				movePlayer();
				aiMove();
				tie();
			}//end while
		}
		else{
			cout << endl << "Enter 'y' for yes, or 'n' for no." << endl;
			_getch();
		}

	}//end while playerSelection
}