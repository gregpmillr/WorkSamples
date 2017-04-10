Assignment: 1
Course: CSCI 2110 - Data Structures and Algorithms
Student: Greg Miller (B00627340)
Date: Jan. 24, 2017
Pledge: I pledge that all of the methods in this assignment were
written by me for this assignment with the exception of the following:
	Q1
		Island.testCoconuts copied and modified from Andrew Chaplin
	Q3
		CalPrinter.printCalendar copied and modified from Andrew Chaplin


Q1:
	Description: Coconut recursive problem solver
	Files:
		main.java
		Island.java

	Run instructions:
		Run main.java

	Answers:
		c)5 Sailors = 15621 Coconuts ,6 Sailors = 279931 Coconuts,
		  7 Sailors = 5764795 Coconuts

		d)Largest Sailors: 15 with long data type, 8 with int data type.
			Took roughly 20 minutes to find the solution for 15 Sailors, 6 seconds
			for 8 Sailors. Cannot find answer for large values of Sailors because
			of the limitations of data type sizes which the coconut count exceeds.



Q2:
	Description: Airline ticket calculator and printer
	Files:
		AirlineTicket.java
		Flight.java
		Customer.java
		main.java
		TestTicket.java

	Run instructions:
		Run main.java

	Output:
	+-----------------------------------------------------------------
	| Testing empty constructors:
	| Manipulating data...
	| After manipulation:
	| Customer: Geoff Caines, 98 Charles Rd, Dartmouth, #1293023, 0 points
	| Flight: Flight 101, Dartmouth, PEI, 03/02/99 7:50 pm
	| Ticket: Geoff Caines, Flight 101, Dartmouth to PEI, 03/02/99 7:50 pm
	+-----------------------------------------------------------------
	| Testing constructors:
	| Applying points:
	| Customer: Greg Miller, 127 Leeds St, Halifax, #123456, 7 points
	| Flight: Flight 101, Ottawa, Calgary, 03/02/99 7:50 pm
	| Ticket: Greg Miller, Flight 101, Ottawa to Calgary, 03/02/99 7:50 pm
	+-----------------------------------------------------------------



Q3:
	Description: Calendar printer
	Files:
		CalPrinter.java
		main.java
		Month.java

	Run instructions:
		Run main.java

	Extra functionality:
		Continuous loop for input with validations.

	Output:
																							2000
	+------------ Jan -------------+------------ Feb -------------+------------ Mar -------------+
	|  Su  Mo  Tu  We  Th  Fr  Sa  |  Su  Mo  Tu  We  Th  Fr  Sa  |  Su  Mo  Tu  We  Th  Fr  Sa  |
	|                           1  |           1   2   3   4   5  |               1   2   3   4  |
	|   2   3   4   5   6   7   8  |   6   7   8   9  10  11  12  |   5   6   7   8   9  10  11  |
	|   9  10  11  12  13  14  15  |  13  14  15  16  17  18  19  |  12  13  14  15  16  17  18  |
	|  16  17  18  19  20  21  22  |  20  21  22  23  24  25  26  |  19  20  21  22  23  24  25  |
	|  23  24  25  26  27  28  29  |  27  28  29                  |  26  27  28  29  30  31      |
	|  30  31                      |                              |                              |
	+------------ Apr -------------+------------ May -------------+------------ Jun -------------+
	|  Su  Mo  Tu  We  Th  Fr  Sa  |  Su  Mo  Tu  We  Th  Fr  Sa  |  Su  Mo  Tu  We  Th  Fr  Sa  |
	|                           1  |       1   2   3   4   5   6  |                   1   2   3  |
	|   2   3   4   5   6   7   8  |   7   8   9  10  11  12  13  |   4   5   6   7   8   9  10  |
	|   9  10  11  12  13  14  15  |  14  15  16  17  18  19  20  |  11  12  13  14  15  16  17  |
	|  16  17  18  19  20  21  22  |  21  22  23  24  25  26  27  |  18  19  20  21  22  23  24  |
	|  23  24  25  26  27  28  29  |  28  29  30  31              |  25  26  27  28  29  30      |
	|  30                          |                              |                              |
	+------------ Jul -------------+------------ Aug -------------+------------ Sep -------------+
	|  Su  Mo  Tu  We  Th  Fr  Sa  |  Su  Mo  Tu  We  Th  Fr  Sa  |  Su  Mo  Tu  We  Th  Fr  Sa  |
	|                           1  |           1   2   3   4   5  |                       1   2  |
	|   2   3   4   5   6   7   8  |   6   7   8   9  10  11  12  |   3   4   5   6   7   8   9  |
	|   9  10  11  12  13  14  15  |  13  14  15  16  17  18  19  |  10  11  12  13  14  15  16  |
	|  16  17  18  19  20  21  22  |  20  21  22  23  24  25  26  |  17  18  19  20  21  22  23  |
	|  23  24  25  26  27  28  29  |  27  28  29  30  31          |  24  25  26  27  28  29  30  |
	|  30  31                      |                              |                              |
	+------------ Oct -------------+------------ Nov -------------+------------ Dec -------------+
	|  Su  Mo  Tu  We  Th  Fr  Sa  |  Su  Mo  Tu  We  Th  Fr  Sa  |  Su  Mo  Tu  We  Th  Fr  Sa  |
	|   1   2   3   4   5   6   7  |               1   2   3   4  |                       1   2  |
	|   8   9  10  11  12  13  14  |   5   6   7   8   9  10  11  |   3   4   5   6   7   8   9  |
	|  15  16  17  18  19  20  21  |  12  13  14  15  16  17  18  |  10  11  12  13  14  15  16  |
	|  22  23  24  25  26  27  28  |  19  20  21  22  23  24  25  |  17  18  19  20  21  22  23  |
	|  29  30  31                  |  26  27  28  29  30          |  24  25  26  27  28  29  30  |
	|                              |                              |  31                          |
	+--------------------------------------------------------------------------------------------+
