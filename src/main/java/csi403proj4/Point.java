package csi403proj4;

public class Point {
	
	// Data Fields
	private int x;
	private int y;
	
	// Constructors
	public Point() {}
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	
	
	@Override
	public String toString() {
		return "[" + x + ", " + y + "]";
	}
	// Getters and Setters
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
}
