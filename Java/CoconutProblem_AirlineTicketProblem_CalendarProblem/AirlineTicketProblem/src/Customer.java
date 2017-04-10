/**
 * Represents a customer with it's associated data. A customer has membership
 * points, membership number, a name, and address. Membership points may be
 * applied to the customer using the applyPoints method.
 * <p>
 * Assumptions/Restrictions: None - does only what was asked for in the
 * assignment
 * </p>
 * <p>
 * Noteworthy Features: None - does only what was asked for in the assignment
 * <p>
 * 
 * @author Greg
 */
public class Customer {

	/**
	 * Declare instance variables
	 */
	private String address;
	private int membershipNumber;
	private int membershipPoints;
	private String name;
	private static final int INITIAL_MEMBERSHIP_POINTS = 0;


	/**
	 * Empty constructor for Customer
	 */
	public Customer() {
	}

	/**
	 * Constructor setting properties for the name, address, and
	 * membershipNumber of the customer
	 * 
	 * @param name of the customer
	 * @param address of the customer
	 * @param membershipNumber of the customer
	 */
	public Customer(String name, String address, int membershipNumber) {
		this.name = name;
		this.address = address;
		this.membershipNumber = membershipNumber;
		this.membershipPoints = INITIAL_MEMBERSHIP_POINTS;
	}

	/**
	 * Apply points to a customer's total membershipPoints field. Points are
	 * calculated from the price of the ticket that is used.
	 * 
	 * @param ticket representing the ticket for application of points
	 * @return double representing new amount of membership points
	 */
	public int applyPoints(AirlineTicket ticket) {
		this.membershipPoints = (int) (membershipPoints + Math.round(ticket.getPrice()/100));

		return this.membershipPoints;
	}

	public String getAddress() {
		return address;
	}

	public int getMembershipNumber() {
		return membershipNumber;
	}

	public int getMembershipPoints() {
		return membershipPoints;
	}

	public String getName() {
		return name;
	}

	protected void setAddress(String address) {
		this.address = address;
	}

	protected void setMembershipNumber(int membershipNumber) {
		this.membershipNumber = membershipNumber;
	}

	protected void setMembershipPoints(int membershipNumber) {
		this.membershipPoints = membershipNumber;
	}

	protected void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		String customerInfo;

		customerInfo = this.getName() + ", " + this.getAddress() + ", #" + this.getMembershipNumber() + ", "
				+ this.getMembershipPoints() + " points";

		return customerInfo;
	}

}
