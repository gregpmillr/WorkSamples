import java.util.*;

/**
 * Represents a Month of the given year. Displays and stores
 * Month information such as month names, lengths of months, and a printer
 * for each week of the month.
 * <p>
 * Assumptions/Restrictions: Cannot accept a year before we switched to the Gregorian calendar.
 * </p>
 * <p>
 * Noteworthy Features: None - does only what was asked for in the assignment
 * <p>
 * 
 * @author Greg
 */
public class Month{
	
	/**
	 * Declare and initialize variables
	 */
	private String name;
	private int[][] days;
	private int daysInMonth;
	private int firstDay;
	private final String[] MONTH_NAMES ={"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
	private int[] monthLengths = {31,28,31,30,31,30,31,31,30,31,30,31};
	private final int WEEKS_IN_MONTH = 6;
	private final int DAYS_IN_WEEK = 7;
	private final int RESET_FEB_DAY = 29;
	private final int FEBRUARY = 1;

	/**
	 * Empty constructor for Month class
	 */
	public Month(){}
	
	/**
	 * Constructor containing and setting information of a month.
	 * Determines:
	 * <ul>
	 * <li>Name of month
	 * <li>If is a leap year
	 * <li>Days in the month
	 * <li>First day of the month
	 * <li>Days in the month
	 * </ul>
	 * 
	 * @param year of the calendar to print out
	 * @param monthNum month of year to be created
	 */
	public Month(int year, int monthNum){
		//GregorianCalendar object used to find what day the month starts on
		GregorianCalendar c = new GregorianCalendar(year,monthNum,1); 

		int currentDay = 1;

		//Set the name of the month
		name = MONTH_NAMES[monthNum];

		// reset the length of February for leap years
		if (c.isLeapYear(year))
			monthLengths[FEBRUARY] = RESET_FEB_DAY;  
		daysInMonth = monthLengths[monthNum];

		//What day of the week does the month start on?
		firstDay = c.get(Calendar.DAY_OF_WEEK)-1; 

		//Create an 2d array representing 6 weeks each of 7 days each
		days = new int[WEEKS_IN_MONTH][DAYS_IN_WEEK];  

		//Initialize the days array for this month
		for (int week = 0;week < WEEKS_IN_MONTH;week++){ 
			for (int day = 0; day<DAYS_IN_WEEK;day++){
				if (week == 0 && day < firstDay){
					days[week][day] = 0;
				} else if (currentDay > daysInMonth){
					days[week][day] = 0;
				} else
					days[week][day] = currentDay++;
			} // inner for loop
			
		} // outer for loop
		
	} // constructor Month
	
	/**
	 * Prints a week out of the Month that this object is instantiated as.
	 * 
	 * @param week of the Month to be printed
	 * @return week selected of type String
	 */
	public String weekString(int week){
		
		//initialize String to be printed
		String selectedWeek = "|  ";
		
		// add days and spacing to the String to be printed
		for(int day = 0; day<DAYS_IN_WEEK; day++){
			if(days[week][day] == 0){ // larger spacing
				selectedWeek = selectedWeek + "    ";
			}else{
				if(days[week][day] < 10){ // smaller spacing
					selectedWeek = selectedWeek + " ";
				}
				selectedWeek = selectedWeek + days[week][day] + "  "; // day and spacing
			}	
		} // for loop
		
		return selectedWeek;
		
	} // method weekString
	
	/**
	 * Get the name of the Month
	 * 
	 * @return name of the month of type String
	 */
	public String getName(){
		return name;
	} // method getName
	
	/**
	 * Print out the Month object as a String
	 * 
	 * @return current month
	 */
	public String toString(){
		
		String month = "";
		
		//Initialize the days array for this month
		for (int week = 0;week < WEEKS_IN_MONTH;week++){ 
			month = month + "  " + weekString(week);
		}
		
		return month;
	} // method toString

} // class Month