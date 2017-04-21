
public class DemoGraph {
	public static void main(String[] args) {
		Graph graph = new Graph();
		
		Vertex[] vertices = new Vertex[5];
		for(int i = 0; i < vertices.length; i++) {
			vertices[i] = new Vertex("" + i);
			graph.addVertex(vertices[i], true);
		}
		
		// Show that duplicate edges aren't added
		for(int i = 0; i < vertices.length - 1; i++) {
			for(int j = i + 1; j < vertices.length; j++) {
				graph.addEdge(vertices[i], vertices[j]);
				graph.addEdge(vertices[i], vertices[j]);
				graph.addEdge(vertices[j], vertices[i]);
			}
		}
		
		// Display initial setup where all vertices are adjacent to each other
		for(int i = 0; i < vertices.length; i++) {
			System.out.println(vertices[i]);
			
			for(int j = 0; j < vertices[i].getNeighborCount(); j++) {
				System.out.println(vertices[i].getNeighbor(j));
			}
			
			System.out.println();
		}
		
		// Overwrite Vertex 3
		graph.addVertex(new Vertex("3"), true);
		for(int i = 0; i < vertices.length; i++) {
			System.out.println(vertices[i]);
			
			for(int j = 0; j < vertices[i].getNeighborCount(); j++) {
				System.out.println(vertices[i].getNeighbor(j));
			}
			
			System.out.println();
		}
		
		System.out.println("Vertex 5: " + graph.getVertex("5"));
		System.out.println("Vertex 3: " + graph.getVertex("3"));
		
		System.out.println("Graph contains {1, 2}: " + 
				graph.containsEdge(new Edge(graph.getVertex("1"),
						graph.getVertex("2"))));
		
		System.out.println(graph.removeVertex("2"));
		System.out.println(graph.vertexKeys()); 
		
		System.out.println("TOPOLOGICAL SORT:");
		graph.topologicalSort();
		
	}
}
