
public class Edge {

	private Vertex one, two;
	private int weight;
	
	public Edge(Vertex one, Vertex two) {
		this(one, two, 1);
	}
	
	public Edge(Vertex one, Vertex two, int weight) {
		//have some way to differentiate the two using this:
		this.one = ( one.getLabel().compareTo(two.getLabel()) <= 0) ? 
					one : two;
		this.two = (this.one == one) ? two : one;
		this.weight = weight;
	}

	/**
	 * Check if either incident Vertices 
	 * @param current
	 * @return
	 */
	public Vertex getNeighbor(Vertex current) {
		// Ensure the current Vertex is one of the Vertices of this Edge.
		if( !( current.equals(one) || current.equals(two) ) ) {
			return null;
		}
		
		// Return the opposite Vertex
		return (current.equals(one)) ? two : one;
	}
	
	public Vertex getOne() {
		return this.one;
	}
	
	public Vertex getTwo() {
		return this.two;
	}
	
	public int getWeight() {
		return this.weight;
	}
	
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	/**
	 * Get the difference in weight
	 * @param other Comparator to this Object's Edge
	 * @return difference in weight
	 */
	public int compareTo(Edge other) {
		return this.weight - other.weight;
	}
	
    public String toString(){
        return "({" + one + ", " + two + "}, " + weight + ")";
    }
    
    public int hashCode(){
        return (one.getLabel() + two.getLabel()).hashCode();
    }
    
    /**
     * @param Object to compare current Edge to
     * @return true if other is an Edge with same Vertices as this Edge
     */
    public boolean equals(Object other) {
    	if(!(other instanceof Edge)) {
    		return false;
    	}
    	
    	Edge e = (Edge) other;
    	
    	return e.one.equals(this.one) && e.two.equals(this.two);
    }

}
