package csi403proj4;

public class Side {

	// Points
	Point from;
	Point to;
	int length;
	double slope;

	// Constructors
	public Side() {}
	public Side(Point from, Point to) {
		this.from = from;
		this.to = to;
		this.length = getDistance(from,to);
		this.slope = findSlope();
	}
	
	// Functions
	public int getDistance(Point from, Point to) {
		int xdif = from.getX() - to.getX();
		int ydif = from.getY() - to.getY();
		int distance = (int) Math.sqrt( Math.pow(xdif, 2) + Math.pow(ydif, 2));
		return distance;
	}
	public char sharedAxis() {
		if ((from.getX() == to.getX())) {
			return 'x';
		}
		if ((from.getY() == to.getY())) {
			return 'y';
		}
		return '0';
	}
	
	public double findSlope() {
		slope = to.getY() - from.getY();
		slope = (slope)/(to.getX() - from.getX());
		System.out.println("Line Segment : "+from+"->"+to+" slope is: "+slope);
		return slope;
	}
	
	
	// Getters and Setters
	
	public Point getFrom() {
		return from;
	}
	public void setFrom(Point from) {
		this.from = from;
	}
	public Point getTo() {
		return to;
	}
	public void setTo(Point to) {
		this.to = to;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public double getSlope() {
		return slope;
	}
	public void setSlope(double slope) {
		this.slope = slope;
	}
}
