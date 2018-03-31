package csi403proj5;

public class Edge {
	// Data Fields
	Vertex v1, v2;
	boolean traveled;

	// Constructors
	public Edge() {}
	public Edge(Vertex v1, Vertex v2) {
		this.v1 = v1;
		this.v2 = v2;
		this.traveled = false;
	}

	// Getters and Setters
	public Vertex getV1() {
		return v1;
	}
	public void setV1(Vertex v1) {
		this.v1 = v1;
	}
	public Vertex getV2() {
		return v2;
	}
	public void setV2(Vertex v2) {
		this.v2 = v2;
	}
	public boolean isTraveled() {
		return traveled;
	}
	public void setTraveled(boolean traveled) {
		this.traveled = traveled;
	}
}
