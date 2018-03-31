package csi403proj5;

import java.util.ArrayList;

public class Vertex {
	// Data Fields
	String name; 
	ArrayList<Integer> edgeID;
	boolean visited;
	
	// Constructors
	public Vertex() {} // No Arguments
	public Vertex(String name) {
		this.name = name;
		this.edgeID = new ArrayList<Integer>();
		this.visited = false;
	}
	
	// Getters and Setters
	public String getName() {
		return name;
	}
	public boolean isVisited() {
		return visited;
	}
	public void setVisited(boolean visited) {
		this.visited = visited;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Integer> getEdgeID() {
		return edgeID;
	}
	public void setEdgeID(ArrayList<Integer> edgeID) {
		this.edgeID = edgeID;
	}
}
