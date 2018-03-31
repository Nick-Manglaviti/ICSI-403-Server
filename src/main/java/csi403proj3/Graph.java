package csi403proj3;
import java.util.HashMap;


public class Graph {
	// Data Fields
	private HashMap<String, Vertex> vertices;
	
	// Constructors for Vertices
	public Graph() { 
		HashMap<String, Vertex> map = new HashMap<String, Vertex>();
		this.vertices = map;
	}
	public Graph(HashMap<String, Vertex> vertices) {
		this.vertices = vertices;
	}
	
	//Add Edges between Vertices
	public void addEdge(Vertex from, Vertex to) {
		if (vertices.containsKey(from.getName())) {
			from = vertices.get(from.getName());
		} else {
			vertices.put(from.getName(), from);
		}
		if (vertices.containsKey(to.getName())) {
			to = vertices.get(to.getName());
		} else {
			vertices.put(to.getName(), to);
		}
		// Add to the Adjacency Lists
		this.vertices.get(from.getName()).getChildren().add(to);
		this.vertices.get(to.getName()).getParents().add(from);
	}	
		
	// Getters and Setters
	public HashMap<String, Vertex> getVertices() {
		return vertices;
	}
	public void setVertices(HashMap<String, Vertex> vertices) {
		this.vertices = vertices;
	}	
}
