/**
 * Represents a flight which has a flightNumber, departure date, flight origin, 
 * and a flight destination
 * <p>
 * Assumptions/Restrictions: None - does only what was asked for in the assignment
 * </p>
 * <p>
 * Noteworthy Features: None - does only what was asked for in the assignment
 * </p>
 * 
 * @author Greg
 */
public class Flight {

	/**
	 * Declare instance variables
	 */
	private String departureDateTime;
	private String destination;
	private int flightNumber;
	private String origin;

	
	/**
	 * Empty constructor for Flight
	 */
	public Flight() {
	}

	/**
	 * Constructor for Flight class which sets instance variables
	 * 
	 * @param flightNumber for the flight
	 * @param origin destination of starting location
	 * @param destination destination of arrival
	 * @param departureDateTime date and time of departure
	 */
	public Flight(int flightNumber, String origin, String destination, String departureDateTime) {
		this.flightNumber = flightNumber;
		this.origin = origin;
		this.destination = destination;
		this.departureDateTime = departureDateTime;
	}

	public String getDepartureDateTime() {
		return departureDateTime;
	}

	public String getDestination() {
		return destination;
	}

	public int getFlightNumber() {
		return flightNumber;
	}

	public String getOrigin() {
		return origin;
	}

	protected void setDepartureDateTime(String departureDateTime) {
		this.departureDateTime = departureDateTime;
	}

	protected void setDestination(String destination) {
		this.destination = destination;
	}

	protected void setFlightNumber(int flightNumber) {
		this.flightNumber = flightNumber;
	}

	protected void setOrigin(String origin) {
		this.origin = origin;
	}

	@Override
	public String toString() {
		String flightInfo;

		flightInfo = "Flight " + flightNumber + ", " + origin + ", " + destination + ", " + departureDateTime;

		return flightInfo;
	}

}
