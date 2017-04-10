/**
 * Represents the testing class for this program. Tests the functionality of all
 * methods within classes to build a Customer object it's related data for a
 * flight.
 * 
 * <p>
 * Assumptions/Restrictions: None - does only what was asked for in the
 * assignment
 * </p>
 * <p>
 * Noteworthy Features: None - does only what was asked for in the assignment
 * </p>
 * 
 * @author Greg
 */
public class TestTicket {
	
	/**
	 * Empty constructor for TestTicket
	 */
	public TestTicket() {
	}

	
	/**
	 * Initiates the testing process. This method tests the constructors of 
	 * each object, as well as toString methods and a data manipulation method
	 */
	public static void start() {
		
		testEmptyConstructors();
		testConstructors();

	} // method start
	
	/**
	 * Tests the empty constructors of AirlineTIcket, Customer and Flight classes.
	 * Also manipulates data using object methods, prints this data using toString.
	 */
	public static void testEmptyConstructors() {
		
		System.out.println("+-----------------------------------------------------------------");
		System.out.println("| Testing empty constructors: ");

		Customer greg;
		AirlineTicket ticketGreg;
		Flight flightBahamas;

		greg = new Customer();
		flightBahamas = new Flight();
		ticketGreg = new AirlineTicket();

		// manipulate data using object methods and print it out
		System.out.println("| Manipulating data...");
		manipulateData(greg, ticketGreg, flightBahamas);
		System.out.println("| After manipulation:");
		printObjects(greg, ticketGreg, flightBahamas);
		
	} // method testEmptyConstructors
	
	/**
	 * Tests the constructors requiring parameters in Customer, Flight and AirlineTicket classes.
	 * Prints out this data to check if toString methods work.
	 */
	public static void testConstructors() {
		
		System.out.println("| Testing constructors: ");
		Customer greg;
		AirlineTicket ticketGreg;
		Flight flightBahamas;

		greg = new Customer("Greg Miller", "127 Leeds St, Halifax", 123456);
		flightBahamas = new Flight(101, "Ottawa", "Calgary", "03/02/99 7:50 pm");
		ticketGreg = new AirlineTicket(greg, flightBahamas, 747.00);

		// apply points from ticket
		System.out.println("| Applying points:");
		greg.applyPoints(ticketGreg);
		
		// uses overridden toString methods
		printObjects(greg, ticketGreg, flightBahamas);
		
	} // method testConstructors

	/**
	 * Use the parameters methods to manipulate data to ensure proper function of getters and setters.
	 * 
	 * @param customer boarding the flight
	 * @param ticket for the customer with a relationship to the flight
	 * @param flight that the customer is boarding and the ticket is for
	 */
	public static void manipulateData(Customer customer, AirlineTicket ticket, Flight flight) {
		
		customer.setName("Geoff Caines");
		customer.setAddress("98 Charles Rd, Dartmouth");
		customer.setMembershipNumber(1293023);
		customer.setMembershipPoints(0);

		flight.setFlightNumber(101);
		flight.setOrigin("Dartmouth");
		flight.setDestination("PEI");
		flight.setDepartureDateTime("03/02/99 7:50 pm");

		ticket.setPassenger(customer);
		ticket.setFlight(flight);
		ticket.setPrice(747.00);
		
	} // method manipulateData

	/**
	 * Tests toString methods of respective objects to ensure proper functionality
	 * 
	 * @param customer boarding the flight
	 * @param ticket for the customer with a relationship to the flight
	 * @param flight that the customer is boarding and the ticket is for
	 */
	public static void printObjects(Customer customer, AirlineTicket ticket, Flight flight) {
		
		System.out.println("| Customer: " + customer);
		System.out.println("| Flight: " + flight);
		System.out.println("| Ticket: " + ticket);
		System.out.println("+-----------------------------------------------------------------");
		
	} // method printVariables
	
} // class TestTicket
