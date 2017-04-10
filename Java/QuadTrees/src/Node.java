/**
 * Node class for the QuadTree which has functions which can be used to gather
 * information of other Nodes
 * 
 * @author greg
 *
 */
public class Node {
	
	/**
	 * Declare instance variables
	 */
	private Point point;
	private int w;
	private int h;

	/**
	 * enum to set which quadrant a Node is in
	 *
	 */
	public static enum QuadrantPosition {
		
		NW(0), NE(1), SW(2), SE(3), EMPTY(-1);

		private final int val;

		QuadrantPosition(int val) {
			this.val = val;
		}

		public int getVal() {return val;}
		
	} //end enum

	/**
	 * Constructor to create a new Node which has the bounds specified in the parameters
	 * as top, left, right, bottom
	 * 
	 * @param top Top bounds
	 * @param left Left bounds
	 * @param right Right bounds
	 * @param bottom Bottom bounds
	 */
	public Node(int top, int left, int right, int bottom) {
		
		point = new Point(left, top);
		this.w = right - left;
		this.h = bottom - top;
		
	} //end constructor
	
	/**
	 * @param point Check the quadrant position of this point
	 * @return QuadrantPosition representing which quadrant this point is within
	 */
	public QuadrantPosition quadrant(Point point) {

		//first check the north 
		if (point.getY() >= top() && point.getY() < (top() + getHeight() / 2)) {
			if (point.getX() >= left() && point.getX() < (left() + getWidth() / 2)) {
				return QuadrantPosition.NW;
			}
			else if (point.getX() >= (left() + getWidth() / 2) && point.getX() < right()) {
				return QuadrantPosition.NE;
			}
			else {return QuadrantPosition.EMPTY;}
		}
		
		//check the south 
		else if (point.getY() >= (top() + getWidth() / 2) && point.getY() < bottom()) {
			if (point.getX() >= left() && point.getX() < (left() + getWidth() / 2)) {
				return QuadrantPosition.SW;
			}
			else if (point.getX() >= (left() + getWidth() / 2) && point.getX() < right()) {
				return QuadrantPosition.SE;
			}
			else {
				return QuadrantPosition.EMPTY;
			}
		}//end if
		//empty
		else {return QuadrantPosition.EMPTY;}
		
	} //end method
	
	/**
	 * @param point Point to look for in this Node
	 * @return boolean if this Node contains the point.
	 */
	public boolean contains(Point point) {
		
		if(point.getX() < right() && left() <= point.getX() && bottom() > point.getY() && top() <= point.getY()){
			return true;
		}else{return false;}
		
	} //end method

	/**
	 * @param other Node which is being compared to
	 * @return boolean if this Node is intersecting other
	 */
	public boolean intersects(Node other) {
		
		// no bound on this is beyond the opposite bound of other
		if (!(this.left() > other.right() || this.left() > other.right()
				|| this.top() > other.bottom())){
			return true;
		}else{return false;}
		
	} //end method

	/**
	 * @param other Node to check if we're inside of
	 * @return boolean if this Node is covered by or contained in other Node
	 */
	public boolean coveredBy(Node other) {
		
		//all bounds of this within bounds of other
		if (this.top() >= other.top() && this.left() >= other.left() 
				&& this.right() <= other.right()
				&& this.bottom() <= other.bottom()){
			return true;
		}else{return false;}
		
	} //end method

	/**
	 * @return this left bound
	 */
	public int left() {return point.getX();}

	/**
	 * @return this right bound
	 */
	public int right() {return point.getX() + getWidth();}

	/**
	 * @return this top bound
	 */
	public int top() {return point.getY();}

	/**
	 * @return this bottom bound
	 */
	public int bottom() {return point.getY() + getWidth();}

	/**
	 * @return NW quadrant of this Node
	 */
	public Node quadrantNW() {return new Node(top(), left(), left() + getWidth() / 2, top() + getWidth() / 2);}

	/**
	 * @return NE quadrant of this Node
	 */
	public Node quadrantNE() {return new Node(top(), left() + getWidth() / 2, right(), top() + getWidth() / 2);}

	/**
	 * @return SW quadrant of this Node
	 */
	public Node quadrantSW() {return new Node(top() + getWidth() / 2, left(), left() + getWidth() / 2, bottom());}

	/**
	 * @return SE quadrant of this Node
	 */
	public Node quadrantSE() {return new Node(top() + getWidth() / 2, left() + getWidth() / 2, right(), bottom());}
	
	public int getWidth(){return this.w;}
	
	public int getHeight(){return this.h;}
} //end class
