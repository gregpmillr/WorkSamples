/**
 * Represents the testing cases for the coconut sailor problem.
 * <p>
 * This class tests the following scenarios:
 * <ul>
 * <li>Determine the minimum number of coconuts for a given amount of sailors
 * <li>Determine the maximum amount of sailors that can satisfy the problem
 * <li>Tests that a given number of coconuts is resolvable to sailors
 * </ul>
 * <p>
 * Assumptions/Restrictions: None - meets all specifications of the assignment.
 * <p>
 * Noteworthy Features: None - does only what was asked for in the assignment
 * 
 * @author Greg
 */

public class TestIsland {
		
	/**
	 * Initialize constants
	 */
	private static final int DETERMINER_MIN_SAILORS = 3;
	private static final int COMPARE_SAILORS = 5;

	/**
	 * Empty constructor for Tester
	 */
	public TestIsland(){
		
	}
	
	/**
	 * Method to test the functionality of the Island class. This test will
	 * uses methods to determine minimum sailors for given coconuts,
	 * tests if coconuts are resolvable to given sailors,
	 * determines largest amount of sailors which can satisfy the problem
	 */
	public static void start(){
		
		/**
		 * Determine the minimum number of coconuts for a given amount of sailors
		 */
		final int minimumDeterminerSailors = DETERMINER_MIN_SAILORS;
		System.out.println("Minimum number of coconuts: ");
		Island.determineMinimumCoconuts(minimumDeterminerSailors);
		System.out.println("-----------------------------------------------------\n");

		
		/**
		 * Tests that a given number of coconuts is resolvable to sailors
		 */
		System.out.println("Test to check sailors against coconuts, true if resolvable, "
				+ "false otherwise ");
		System.out.println("5 sailors against 15621 coconuts: ");
		System.out.println(Island.testCoconuts(COMPARE_SAILORS, 15621, 5));
		System.out.println("\n5 sailors against 1000 coconuts: ");
		System.out.println(Island.testCoconuts(COMPARE_SAILORS, 1000, 5) + "\n");
		System.out.println("-----------------------------------------------------\n");
		
		/**
		 * Determine largest amount of sailors which satisfies the problem
		 */
		System.out.println("Largest amount of sailors which satisfies the problem: ");
		int sailors = 2;
		while(true){
			Island.determineMinimumCoconuts(sailors);
			sailors++;
		} // end while
		
	} // method start
	
} // class Tester
