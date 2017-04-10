/**
 * Represents the island of sailors, coconuts, and a monkey
 * and provides a minimum coconuts method to count the amount of coconuts
 * needed to satisfy the amount of sailors given, and a recursive
 * method to solve the amount of coconuts needed to satisfy the amount of sailors 
 * given.
 * <p>
 * Assumptions/Restrictions: None - meets all specifications of the assignment.
 * </p>
 * <p>
 * Noteworthy Features: None - does only what was asked for in the assignment
 * <p>
 * 
 * @author Greg
 */

public class Island {
	
	/**
	 * Initialize constants
	 */
	private static final int MIN_SAILORS_REMAINING = 0;
	private static final int REMAINDER_SAILORS_REMAINING = 1;
	private static final int MIN_COCONUTS_REMAINING = 0;


	/**
	 * Empty constructor for Island class
	 */
	public Island(){
		
	}
	
	/**
	 * Attempts to count the minimum number of coconuts required to satisfy
	 * the requirements. 
	 * <p>
	 * While the testCoconuts method is false, it is continually called while
	 * increasing the number of coconuts until testCoconuts returns true, which
	 * indicates the minimum number of coconuts required.
	 * </p>
	 * 
	 * @param sailors number to base groups of coconuts from
	 */
	public static void determineMinimumCoconuts(int sailors){
		long coconuts = 0;
		while(!testCoconuts(sailors,coconuts,sailors)){
			coconuts++;
		}
		System.out.println("Sailors: " + sailors);
	    System.out.println("Coconuts: " + coconuts + "\n");

	} //method determineMinimumCoconuts

	
	/**
	 * Attempts to recursively solve the coconuts problem. It reduces the coconuts remaining
	 * and sailors at an attempt to recurse and solve the solution.
	 * <p>
	 * Expected to be initially be called with testCoconuts(x,y,z) and will
	 * terminate if there are any sailors remaining, indicating the base case answer.
	 * </p>
	 * 
	 * @param sailors total number of sailors to calculate minimum coconuts
	 * @param coconutsRemaining remaining coconuts after each recurse
	 * @param sailorsRemaining remaining amount of sailors after each recurse
	 * @return true if coconuts are solved for amount of sailors, false otherwise
	 */
	public static boolean testCoconuts(int sailors, long coconutsRemaining, int sailorsRemaining){
		
		// check if morning has come
     	if(sailorsRemaining == MIN_SAILORS_REMAINING){
     		
     		// coconut available for monkey
     		if(coconutsRemaining % sailors == REMAINDER_SAILORS_REMAINING){ 
     			return true; // problem solved
     		}
     		else if( (coconutsRemaining % sailors) != REMAINDER_SAILORS_REMAINING ){ 
     			return false; // problem not satisfied by number of coconuts given
     		}
     	}
     	// more sailors left, recurse 
     	else{	        
	        // check if enough coconutsRemaining
	        if((coconutsRemaining - ((coconutsRemaining/sailors) * sailors) - 1) == MIN_COCONUTS_REMAINING){
	        	return testCoconuts(sailors,coconutsRemaining - (coconutsRemaining/sailors) - 1, sailorsRemaining- 1);
	        }
	        else 
	        	return false; // problem not satisfied by number of coconuts given
     	}
     	
		return false;
		
	} //method testCoconuts
	
} //class Island
