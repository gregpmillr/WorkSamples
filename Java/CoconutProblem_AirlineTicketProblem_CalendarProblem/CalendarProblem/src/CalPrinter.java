/**
 * Represents the calendar and as well as prints the calendar using a 
 * multidimensional array of Month objects. Provides a print calendar method
 * which, when given a year, formats a calendar and prints it to the console.
 * <p>
 * Assumptions/Restrictions: Cannot accept a year before we switched to the Gregorian calendar.
 * </p>
 * <p>
 * Noteworthy Features: None - does only what was asked for in the assignment
 * <p>
 * 
 *  @author Greg
 */
public class CalPrinter {
	
	/**
	 * Initialize constants
	 */
	private static final int CALENDAR_ROWS = 4;
	private static final int CALENDAR_COLUMNS = 3;
	private static final int WEEKS_IN_MONTH = 6;


	/**
	 * Empty constructor for CalPrinter class
	 */
	public CalPrinter(){}
	
	/**
	 * Prints a Gregorian calendar when given a year
	 * <p>
	 * Declares a multidimensional array holding month objects
	 * which are then instantiated. Prints out headers for the current 
	 * month and week days, then displays each week line by line.
	 * </p>
	 * 
	 * @param year to be printed, resolves leap years.
	 */
	public static void printCalendar(int year){
		
		// create an array of 12 month objects corresponding to the year
		Month[][] monthList = new Month[CALENDAR_ROWS][CALENDAR_COLUMNS];
		
		int monthCounter = 0;
		
		System.out.println("                                            " + year);
		
		// using the array of months print the calendar one line at a time
		for(int i = 0;i<CALENDAR_ROWS;i++){
			//instantiate each Month object in 2D array
			for(int j = 0;j<CALENDAR_COLUMNS;j++){
				monthList[i][j] = new Month(year,monthCounter);
				System.out.print("+------------ " + monthList[i][j].getName() + " -------------");
				monthCounter++;
			} // inner for loop
			
			System.out.print("+\n");
			
			// print day headers
			for(int j = 0;j<CALENDAR_COLUMNS;j++){
				System.out.print("|  Su  Mo  Tu  We  Th  Fr  Sa  ");
			} // inner for loop
			
			System.out.print("|\n");
			
			// print week for a given Month object
			for(int week = 0;week<WEEKS_IN_MONTH;week++){
				for(int j=0;j<CALENDAR_COLUMNS;j++){
					System.out.print(monthList[i][j].weekString(week));
				}
				System.out.print("|\n");
			} // inner for loop
			
		} // outer for loop
		
		System.out.println("+------------------------------------------------"
				+ "--------------------------------------------+");
		
	} // printCalendar method
	
} // CalPrinter class
