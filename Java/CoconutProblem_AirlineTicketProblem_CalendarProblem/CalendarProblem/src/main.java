import java.util.Scanner;

/**
 * main contains the driver of a program that prints a calendar
 * given a year in a 4x3 multidimensional array.
 * <p>
 * When run this program will:
 * <ul>
 * <li>Test the creation of a month
 * <li>Test the output of a week given a month and week number
 * <li>Print out a calendar as a 4x3 multidimensional array
 * </ul>
 * <p>
 * Assumptions/Restrictions: This only works for the Gregorian calendar.
 * <p>
 * Noteworthy Features: None - does only what was asked for in the assignment
 * 
 * @author Greg
 *
 */
public class main {

	/**
	 * Initialize constant
	 */
	private static final String QUIT_PROGRAM = "Q";

	
	/**
	 * A driver for the CalPrinter class that when given a year,
	 * prints out the monthly calendar in a multidimensional array.
	 * 
	 * @param args passed in parameters of type String
	 */
	public static void main(String[] args){

		start();

	} // method main
	
	/**
	 * Represents the method getting input from the user
	 * which determines if the program should end, or keep going.
	 * Also includes some validation.
	 */
	public static void start(){
		Scanner scan = new Scanner(System.in);
		
		//continuous loop until Q is typed
		while(true){
			System.out.println("Enter Q to quit this program.");
			System.out.println("Enter a year to print: ");
			String input = scan.nextLine();
			
			if(input.equals(QUIT_PROGRAM)){
				System.out.println("Quitting...");
				break;
			}else{
				try{
					int year = Integer.parseInt(input);
					CalPrinter.printCalendar(year);

				} catch (Exception e)
				{
					System.out.println("Invalid input.");
				}
			} // else

		} // while
	}

} // class main
