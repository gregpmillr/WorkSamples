import java.util.*;

public class Graph {
	
	private HashMap<String, Vertex> vertices;
	private HashMap<Integer, Edge> edges;
	private LinkedList<Integer> adj[];
	private int V;
	
	public Graph() {
		this.vertices = new HashMap<String, Vertex>();
		this.edges = new HashMap<Integer, Edge>();
	}
	
	public Graph(ArrayList<Vertex> vertices) {
		this.vertices = new HashMap<String, Vertex>();
		this.edges = new HashMap<Integer, Edge>();
		
		for(Vertex v : vertices) {
			this.vertices.put(v.getLabel(), v);
		}
	}
	
	public void topologicalSortUtil(int v, boolean visited[], Stack stack) {
		
		// Mark current node as visited
		visited[v] = true;
		
		Integer i;
		
		// Recurse for all vertices adjacent to this vertex
		Iterator<Integer> it = adj[v].iterator();
		while(it.hasNext()) {
			i = it.next();
			if(!visited[i])
				topologicalSortUtil(i, visited, stack);
		}
		
		// Push current vertex to stack 
		stack.push(new Integer(v));		
	}
	
	public void topologicalSort() {
		
		V = vertices.size();
		Stack stack = new Stack();
	
		adj = new LinkedList[vertices.size()];
		for(int i=0; i < V; i++)
			adj[i] = new LinkedList();

		// Mark all vertices as not visited
		boolean visited[] = new boolean[V];
		for (int i = 0; i < V; i++) 
			visited[i] = false;
		
		// Call recursive helper function to store Topological Sort
		// starting from all vertices one by one
		for (int i = 0; i < V; i++)
			if(visited[i] == false)
				topologicalSortUtil(i,visited,stack);
		
		// Print contents of stack
		while(stack.empty() == false)
			System.out.print(stack.pop() + " ");
	}
	
	public boolean addEdge(Vertex one, Vertex two) {
		return addEdge(one, two, 1);
	}
	
	public boolean addEdge(Vertex one, Vertex two, int weight) {
		// Keep duplicated Vertices out!
		if(one.equals(two)) {
			return false;
		}
		
		// Ensure Edge is not in the Graph
		Edge e = new Edge(one,two,weight);
		if(edges.containsKey(e.hashCode())) {
			return false;
		}
		
		// Ensure edge isn't already incident to one of the vertices
//		else if(one.containsNeighbor(e) || two.containsNeighbor(e)) {
//			return false;
//		}
		
		edges.put(e.hashCode(), e);
		one.addNeighbor(e);
		two.addNeighbor(e);
		return true;
	}
	
	public boolean containsEdge(Edge e) {
		if(e.getOne() == null || e.getTwo() == null) {
			return false;
		}
		
		return this.edges.containsKey(e.hashCode());
	}
	
	/**
	 * Removes the specified Edge from the Graph,
	 * this includes deleting each Vertex's incidence neighborhood
	 * @param e The Edge to remove from the Graph
	 * @return Edge the Edge removed from the Graph
	 */
	public Edge removeEdge(Edge e) {
		e.getOne().removeNeighbor(e);
		e.getTwo().removeNeighbor(e);
		return this.edges.remove(e.hashCode());
	}
	
	/**
	 * 
	 * @param vertex The Vertex to loop up
	 * @return true if this graph contains Vertex
	 */
	public boolean containsVertex(Vertex vertex) {
		return this.vertices.get(vertex.getLabel()) != null;
	}
	
	public Vertex getVertex(String label) {
		return this.vertices.get(label);
	}
	
	public boolean addVertex(Vertex vertex, boolean overwriteExisting) {
		Vertex current = this.vertices.get(vertex.getLabel());
		
		// Check if Vertex already exists
		if(current != null) {
			// Check if we want to override the existing Vertex
			if(overwriteExisting) {
				while(current.getNeighborCount() > 0) {
					this.removeEdge(current.getNeighbor(0));
				}
			} else {
				return false;
			}
		}
		
		vertices.put(vertex.getLabel(), vertex);
		return true;
	}
	
	public Vertex removeVertex(String label) {
		Vertex v = vertices.remove(label);
		
		// Remove the Edges from this vertices of the 
		// removed vertex's neighbors
		while(v.getNeighborCount() > 0) {
			this.removeEdge(v.getNeighbor(0));
		}
		
		return v;
	}
	
	public Set<String> vertexKeys() {
		return this.vertices.keySet();
	}
	
	public Set<Edge> getEdges() {
		return new HashSet<Edge>(this.edges.values());
	}
}
