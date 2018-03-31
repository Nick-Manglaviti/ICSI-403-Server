package csi403proj5;

import java.util.ArrayList;

public class Graph {
	// Data Fields
	ArrayList<Vertex> vertices;
	ArrayList<Edge> edges;
	int count;
	
	// Constructor
	public Graph() {}
	public Graph(ArrayList<Vertex> vertices, ArrayList<Edge> edges) {
		this.vertices = vertices;
		this.edges = edges;
	}
	
	// Functions: Traverse
	public boolean isConnected() {
		// Reset Visits
		for(int x = 0; x < vertices.size(); x++) {vertices.get(x).setVisited(false);}
		for(int y = 0; y < edges.size(); y++) {edges.get(y).setTraveled(false);}
		
		this.count = 0;
		visit(vertices.get(0));
		if(count == vertices.size()) {
			return true;
		}
		return false;
	} // Recursive Visit to get count
		private void visit(Vertex v) {
			if(v.isVisited() == false) {
				this.count++;
				v.setVisited(true);
				for(int x = 0; x < v.getEdgeID().size(); x++) {
					if(edges.get(v.getEdgeID().get(x)).isTraveled() == false) {
						edges.get(v.getEdgeID().get(x)).setTraveled(true);
						visit(edges.get(v.getEdgeID().get(x)).getV1());
						visit(edges.get(v.getEdgeID().get(x)).getV2());
					}
				}
			}
		}
	
	
	// Functions: Check Type
	public boolean isBus() {
		// Long Vertex
		if(vertices.size() == 1 && edges.size() == 1) {
			return true;
		}
		
		if(vertices.size() != (edges.size() + 1)) {
			return false;
		}
		int parents = 0; // Vertices with one edge
		int children = 0; // Vertices with two edges
		
		// Set Parameters for Counters
		for(int x = 0; x < vertices.size(); x++) {
			if(vertices.get(x).getEdgeID().size() == 1) {
				parents++;
			}
			if(vertices.get(x).getEdgeID().size() == 2) {
				children++;
			}
		}
		// Head and Tail
		if(parents != 2) {
			return false;
		}
		// (V - 2) children
		if(children != (vertices.size() - 2)) {
			return false;
		}
		
		if(!this.isConnected()) {
			return false;
		}
		return true;
	}
	
	public boolean isRing() {
		if(vertices.size() != edges.size()) {
			return false;
		}
		
		for(int x = 0; x < vertices.size(); x++) {
			if(vertices.get(x).getEdgeID().size() != 2) {
				return false;
			}
			for(int y = 0; y < 2; y++) {
				int edge1ID = vertices.get(x).getEdgeID().get(0);
				int edge2ID = vertices.get(x).getEdgeID().get(1);
				
				String from1 = edges.get(edge1ID).getV1().getName();
				String to1 = edges.get(edge1ID).getV2().getName();
				
				String from2 = edges.get(edge2ID).getV1().getName();
				String to2 = edges.get(edge2ID).getV2().getName();
				
				// Regular
				if(from1.equals(from2)) {
					if(to1.equals(to2)) {
						return false;
					}
				}
				// Transpose
				if(to1.equals(from2)) {
					if(from1.equals(to2)) {
						return false;
					}
				}
			}
		}
		// Connected
		if(!this.isConnected()) {
			return false;
		}
		return true;
	}
	
	public boolean isStar() {
		if(vertices.size() != edges.size()+1) {
			return false;
		}
		int tips = 0;
		int center = 0;
		for(int x = 0; x < vertices.size(); x++) {
			if(vertices.get(x).getEdgeID().size() == 1) {
				tips++;
			} else {
				if(vertices.get(x).getEdgeID().size() == (vertices.size()-1)) {
					center++;
				}
			}
		}
		if(tips != (vertices.size()-1) || (center != 1)) {
			return false;
		}
		if(!this.isConnected()) {
			return false;
		}
		
		return true;
	}
	
	// Getters and Setters
	public ArrayList<Vertex> getVertices() {
		return vertices;
	}
	public void setVertices(ArrayList<Vertex> vertices) {
		this.vertices = vertices;
	}
	public ArrayList<Edge> getEdges() {
		return edges;
	}
	public void setEdges(ArrayList<Edge> edges) {
		this.edges = edges;
	}
}
