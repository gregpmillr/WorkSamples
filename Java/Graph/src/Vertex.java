import java.util.ArrayList;

public class Vertex {
	
	private ArrayList<Edge> neighborhood;
	private String label;
	
	public Vertex(String label) {
		this.label = label;
		this.neighborhood = new ArrayList<Edge>();
	}
	
	/**
	 * Adds an edge to the incidence neighborhood of this graph IF 
	 * the edge isn't already present
	 * @param edge The edge to add
	 */
	public void addNeighbor(Edge edge) {
		if(this.neighborhood.contains(edge)) {
			return;
		}
		
		this.neighborhood.add(edge);
	}
	
	/**
	 * 
	 * @param edge Edge for which to search
	 * @return true if edge is contained in this neighborhood
	 */
	public boolean containsNeighbor(Edge edge) {
		return this.neighborhood.contains(edge);
	}
	
	/**
	 * 
	 * @param index The index of the Edge to retrieve
	 * @return Edge the Edge at the specified index in this.neighborhood
	 */
	public Edge getNeighbor(int index) {
		return this.neighborhood.get(index);
	}
	
	/**
	 * 
	 * @param index The index of the edge to remove from this.neighborhood
	 * @return Edge The removed Edge
	 */
	public Edge removeNeighbor(int index) {
		return this.neighborhood.remove(index);
	}
	
	/**
	 * 
	 * @param index The index of the edge to remove from this.neighborhood
	 * @return Edge The removed Edge
	 */
	public void removeNeighbor(Edge e) {
		this.neighborhood.remove(e);
	}
	
	public int getNeighborCount() {
		return this.neighborhood.size();
	}
	
	public String getLabel() {
		return this.label;
	}
	
	public String toString() {
		return "Vertex " + label;
	}
	
	public int hashCode() {
		return this.label.hashCode();
	}
	
	/**
	 * @param other The object to compare
	 * @return true if other is instanceof Vertex and two Vertex objects have
	 * 			the same label
	 */
	public boolean equals(Object other) {
		if( !(other instanceof Vertex) ) {
			return false;
		}
		
		Vertex v = (Vertex) other;
		return this.label.equals(v.label);
	}
	
	/**
	 * 
	 * @return ArrayList<Edge> A copy of this.neighborhood. 
	 */
	public ArrayList<Edge> getNeighbors() {
		return new ArrayList<Edge>(this.neighborhood);
	}
	
	
	
}
