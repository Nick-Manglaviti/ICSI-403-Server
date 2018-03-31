package csi403proj4;

import java.awt.geom.Line2D;
import java.util.ArrayList;

public class Polygon {
	
	// Data Fields
	ArrayList<Point> vertices;
	ArrayList<Side> segments;
	ArrayList<Point> perimeterPoints;
	ArrayList<Point> encompassedPoints;
	
	// Domain
	int xMin, xMax;
	// Range
	int yMin, yMax;
	
	// Constructors
	public Polygon() {}
	
	public Polygon(ArrayList<Point> vertices) {
		this.vertices = vertices;
		
		findDomainAndRange();
		this.segments = findSegments();
		this.perimeterPoints = findPerimeterPoints();
		findencompassedPoints();
	}
	
	private void findDomainAndRange() {
		
		for(int i = 0; i < vertices.size(); i++) {
			// Set Values on First Pass
			if(i == 0) {
				xMin = vertices.get(i).getX();
				xMax = vertices.get(i).getX();
				yMin = vertices.get(i).getY();
				yMax = vertices.get(i).getY();
				continue;
			}
			// X Minimum
			if(vertices.get(i).getX() < xMin) {
				xMin = vertices.get(i).getX();
			}
			// X Maximum
			if(vertices.get(i).getX() > xMax) {
				xMax = vertices.get(i).getX();
			}
			// Y Minimum
			if(vertices.get(i).getY() < yMin) {
				yMin = vertices.get(i).getY();
			}
			// Y Maximum
			if(vertices.get(i).getY() > yMax) {
				yMax = vertices.get(i).getY();
			}
		}
	}
	
	private ArrayList<Side> findSegments() {
		segments = new ArrayList<Side>();
		if(vertices.size() < 3) {
			return null;
		}
		for(int i = 0; i < vertices.size(); i++) {
			if(i == vertices.size() - 1) {
				Side temp = new Side(vertices.get(i), vertices.get(0));
				segments.add(temp);
				return segments;
			} else {
				Side temp = new Side(vertices.get(i), vertices.get(i+1));
				segments.add(temp);
			}
			
		} return null;
	}
	
	private ArrayList<Point> findPerimeterPoints() {
		perimeterPoints = new ArrayList<Point>();
		for(int i = 0; i < segments.size(); i++) {
			// Share The X Axis
			if(segments.get(i).sharedAxis() == 'x') {
				if(segments.get(i).getFrom().getY() > segments.get(i).getTo().getY()) {
					for(int j = segments.get(i).getTo().getY() + 1; 
							j < segments.get(i).getFrom().getY(); j++) {
						Point temp = new Point();
						temp.setX(segments.get(i).getTo().getX());
						temp.setY(j);
						perimeterPoints.add(temp);
					}
				} else {
					for(int j = segments.get(i).getFrom().getY() + 1; 
							j < segments.get(i).getTo().getY(); j++) {
						Point temp = new Point();
						temp.setX(segments.get(i).getFrom().getX());
						temp.setY(j);
						perimeterPoints.add(temp);
					}
				}
				continue;
			}
			// Share the Y axis
			if(segments.get(i).sharedAxis() == 'y') {
				if(segments.get(i).getFrom().getX() > segments.get(i).getTo().getX()) {
					for(int j = segments.get(i).getTo().getX() + 1; 
							j < segments.get(i).getFrom().getX(); j++) {
						Point temp = new Point();
						temp.setY(segments.get(i).getTo().getY());
						temp.setX(j);
						perimeterPoints.add(temp);
					}
				} else {
					for(int j = segments.get(i).getFrom().getX() + 1; 
							j < segments.get(i).getTo().getX(); j++) {
						Point temp = new Point();
						temp.setY(segments.get(i).getFrom().getY());
						temp.setX(j);
						perimeterPoints.add(temp);
					}
				}
				continue;
			}
			
			// Line is Diagonal, Determine who is higher and the sign of the slope
			double slope;
			// If (Slope is Positive) AND (To.Y > From.Y)
			if(segments.get(i).getSlope() > 0 
					&& segments.get(i).getTo().getY() > segments.get(i).getFrom().getY()) {
				
				slope = segments.get(i).getSlope();
				int tempX = segments.get(i).getFrom().getX() + 1;
				double tempY = segments.get(i).getFrom().getY() + slope;
				
				while(tempY != segments.get(i).getTo().getY()) {
					if( (tempY) % 1 == 0) {
						Point temp = new Point();
						temp.setX(tempX);
						temp.setY((int) tempY);
						perimeterPoints.add(temp);
					}
					tempX++;
					tempY += slope;
				}
				continue;
			}
			// If (Slope is Negative) AND (From.Y > To.Y)
			if(segments.get(i).getSlope() < 0 
					&& segments.get(i).getFrom().getY() > segments.get(i).getTo().getY()) {
				
				slope = segments.get(i).getSlope();
				int tempX = segments.get(i).getFrom().getX() + 1;
				double tempY = segments.get(i).getFrom().getY() + slope;
				
				while(tempY != segments.get(i).getTo().getY()) {
					if( (tempY) % 1 == 0) {
						Point temp = new Point();
						temp.setX(tempX);
						temp.setY((int) tempY);
						perimeterPoints.add(temp);
					}
					tempX++;
					tempY += slope;
				}
				continue;
			}
			// If (Slope is Negative) AND (To.Y > From.Y)
			if(segments.get(i).getSlope() < 0 
					&& segments.get(i).getTo().getY() > segments.get(i).getFrom().getY()) {
				
				slope = segments.get(i).getSlope();
				int tempX = segments.get(i).getFrom().getX() - 1;
				double tempY = segments.get(i).getFrom().getY() - slope;
				
				while(tempY != segments.get(i).getTo().getY()) {
					if( (tempY) % 1 == 0) {
						Point temp = new Point();
						temp.setX(tempX);
						temp.setY((int) tempY);
						perimeterPoints.add(temp);
					}
					tempX--;
					tempY -= slope;
				}
				continue;
			}
			// If (Slope is Positive) AND (From.Y > To.Y)
			if(segments.get(i).getSlope() > 0 
					&& segments.get(i).getFrom().getY() > segments.get(i).getTo().getY()) {
				
				slope = segments.get(i).getSlope();
				int tempX = segments.get(i).getFrom().getX() - 1;
				double tempY = segments.get(i).getFrom().getY() - slope;
				
				while(tempY != segments.get(i).getTo().getY()) {
					if( (tempY) % 1 == 0) {
						Point temp = new Point();
						temp.setX(tempX);
						temp.setY((int) tempY);
						perimeterPoints.add(temp);
					}
					tempX--;
					tempY -= slope;
				}
				continue;
			}
		}
		System.out.println("The Perimeter Points are...");
		for(Point tempString : perimeterPoints) {
			System.out.println(tempString);
		}
		return perimeterPoints;
	}
	
	private void findencompassedPoints() {
		encompassedPoints = new ArrayList<Point>();
		
		for(int x = xMin; x <= xMax; x++) {
			for(int y = yMin; y <= yMax; y++) {
				// The Point we are checking
				ArrayList<Point> topintersects = new ArrayList<Point>();
				ArrayList<Point> botintersects = new ArrayList<Point>();
				int counterT = 0;
				int counterB = 0;
				int xtemp = x;
				int ytemp = y;
				Point test = new Point(xtemp, ytemp);
				
				// Easy Checks
				for(int i = 0; i < vertices.size(); i++) {
					if(vertices.get(i).getX() == test.getX() &&
							vertices.get(i).getY() == test.getY()) {
						test.setX(-1);
						test.setY(-1);
					}
				}
				for(int i = 0; i < perimeterPoints.size(); i++) {
					if(perimeterPoints.get(i).getX() == test.getX() &&
							perimeterPoints.get(i).getY() == test.getY()) {
						test.setX(-1);
						test.setY(-1);
					}
				}
				if(test.getX() == -1) {
					continue;
				}
				
				// Get Intersections
				for(int z = 0; z < segments.size(); z++) {
					// Check Top Intersections
					if(Line2D.linesIntersect(x, y, x, yMax, 
							segments.get(z).getFrom().getX(), segments.get(z).getFrom().getY(),
							segments.get(z).getTo().getX(), segments.get(z).getTo().getY())) {
						int startX = segments.get(z).getFrom().getX();
						int startY = segments.get(z).getFrom().getY();
						int endX = segments.get(z).getTo().getX();
						int endY = segments.get(z).getTo().getY();
						Point start = new Point(startX, startY);
						Point end = new Point(endX, endY);
						
						// Empty, Add Point Plus to Counter
						if(topintersects.isEmpty()) {
							counterT++;
							if(start.getX() == x) {
								topintersects.add(start);
							}
							if(end.getX() == x) {
								topintersects.add(end);
							}
							continue;
						}
						// If Points are not in array, Add to counter
						int inArray = 0;
						for(int k = 0; k < topintersects.size(); k++) {
							if(topintersects.get(k).getX() == start.getX() && topintersects.get(k).getY() == start.getY() || 
									topintersects.get(k).getX() == end.getX() && topintersects.get(k).getY() == end.getY()) {
								inArray = 1;
							}
						}
						if(inArray == 0) {
							counterT++;
						}
					}
					// Check Bottom Intersections
					if(Line2D.linesIntersect(x, y, x, yMin, 
							segments.get(z).getFrom().getX(), segments.get(z).getFrom().getY(),
							segments.get(z).getTo().getX(), segments.get(z).getTo().getY())) {
						int startX = segments.get(z).getFrom().getX();
						int startY = segments.get(z).getFrom().getY();
						int endX = segments.get(z).getTo().getX();
						int endY = segments.get(z).getTo().getY();
						Point start = new Point(startX, startY);
						Point end = new Point(endX, endY);
						
						// Empty, Add Point Plus to Counter
						if(botintersects.isEmpty()) {
							counterB++;
							if(start.getX() == x) {
								botintersects.add(start);
							}
							if(end.getX() == x) {
								botintersects.add(end);
							}
							continue;
						}
						// If Points are not in array, Add to counter
						int inArray = 0;
						for(int k = 0; k < botintersects.size(); k++) {
							if(botintersects.get(k).getX() == start.getX() && botintersects.get(k).getY() == start.getY() || 
									botintersects.get(k).getX() == end.getX() && botintersects.get(k).getY() == end.getY()) {
								inArray = 1;
							}
						}
						if(inArray == 0) {
							counterB++;
						}
					}
					if(counterT != 0 && counterT % 2 != 0 && 
							counterB != 0 && counterB % 2 != 0) {
						boolean flag = false;
						for(int k = 0; k < encompassedPoints.size(); k++) {
							if(encompassedPoints.get(k).getX() == test.getX() &&
									encompassedPoints.get(k).getY() == test.getY())
								flag = true;
						}
						if (!flag) {
							encompassedPoints.add(test);
						}
					}
					
					
				}
			}
		}
		System.out.println("Points Inside...");
		int counter = 0;
		for(Point temp: encompassedPoints) {
			System.out.println(temp);
			counter++;
		}
		System.out.println("Total of " + counter + " points");
	}
	
	@Override
	public String toString() {
		return "Polygon has " + vertices.size() + " vertices and " + segments.size() + 
				" sides. The Domain is from " + xMin + " to " + xMax + ". " +
				"The Range is from " + yMin + " to " + yMax + ".";
	}
	
	// Getters and Setters
	public ArrayList<Point> getVertices() {
		return vertices;
	}

	public ArrayList<Side> getSegments() {
		return segments;
	}

	public ArrayList<Point> getAllPoints() {
		return perimeterPoints;
	}
	

	public ArrayList<Point> getEncompassedPoints() {
		return encompassedPoints;
	}
	
	public int getxMin() {
		return xMin;
	}

	public int getxMax() {
		return xMax;
	}
	
	public int getyMin() {
		return yMin;
	}
	
	public int getyMax() {
		return yMax;
	}
}
