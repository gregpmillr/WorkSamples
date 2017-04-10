/**
 * Represents the ticket for the airline class. Subclass of Flight. This includes an overridden
 * method to return the airline ticket as a String object.
 * <p>
 * Assumptions/Restrictions: None - does only what was asked for in the assignment
 * </p>
 * <p>
 * Noteworthy Features: None - does only what was asked for in the assignment
 * <p>
 * 
 * @author Greg
 */
public class AirlineTicket extends Flight {

	/**
	 * Declare instance variables
	 */
	private Flight flight;
	private Customer passenger;
	private double price;

	/**
	 * Empty constructor for AirlineTicket class
	 */
	public AirlineTicket() {
	}

	/**
	 * Constructor for AirlineTicket class. Sets properties for the passenger,
	 * flight, and price.
	 * 
	 * @param passenger of type Customer, representing the current passenger for the ticket
	 * @param flight associated with this airline ticket
	 * @param price representing the cost of this airline ticket
	 */
	public AirlineTicket(Customer passenger, Flight flight, double price) {
		this.passenger = passenger;
		this.flight = flight;
		this.price = price;
	}

	public Flight getFlight() {
		return flight;
	}
	
	public Customer getPassenger() {
		return passenger;
	}

	public double getPrice() {
		return price;
	}

	protected void setFlight(Flight flight) {
		this.flight = flight;
	}

	protected void setPassenger(Customer passenger) {
		this.passenger = passenger;
	}

	protected void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		String ticketInfo;

		ticketInfo = passenger.getName() + ", Flight " + flight.getFlightNumber() + ", " + flight.getOrigin() + " to "
				+ flight.getDestination() + ", " + flight.getDepartureDateTime();

		return ticketInfo;
	}
}
