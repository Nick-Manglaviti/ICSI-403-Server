package csi403proj4;


import java.util.ArrayList;


import javax.json.*;

public class JsonFunctions {

	public static String parseJsonArray(JsonArray inArray) {
		
		ArrayList<Point> points = new ArrayList<Point>();
		
		for (int x = 0; x < inArray.size(); x++) {
			// Check Size of object
			if (inArray.getJsonObject(x).size() != 2) {
				return "Malformed JSON: Invalid Number of Arguements in inList index " + x;
			}
			// Placeholder
			double xcord;
			double ycord;
			Point temp = new Point();
			
			// Try to get X and Y values
			try {
				xcord = inArray.getJsonObject(x).getInt("x");
			} catch (Exception e) {
				return "Malformed JSON: No x value found at index "+x+" in inList!";
			}
			try {
				ycord = inArray.getJsonObject(x).getInt("y");
			} catch (Exception e) {
				return "Malformed JSON: No y value found at index "+x+" in inList!";
			}
			
			// Check Value of number's range
			if(0 <= xcord && xcord <= 18) {
				if(xcord % 1 == 0) {
					temp.setX((int) xcord);
				} else {
					return "Malformed JSON: Must integers at index "+x+" in inList!";
				}
			} else {
				return "Malformed JSON: Must have (0 < x => 18) at index "+x+" in inList!";
			}
			if(0 <= ycord && ycord <= 18) {
				if(ycord % 1 == 0) {
					temp.setY((int) ycord);
				} else {
					return "Malformed JSON: Must integers at index "+x+" in inList!";
				}
			} else {
				return "Malformed JSON: Must have (0 < y => 18) at index "+x+" in inList!";
			}
			
			// Make sure points are unique
			if(points.isEmpty()) {
				points.add(temp);
				continue;
			}
			for(int y = 0; y < points.size(); y++) {
				if(points.get(y).getX() == temp.getX() && points.get(y).getY() == temp.getY()) {
					return "Malformed JSON: Point {"+temp.getX()+", "+temp.getY()+"} is a duplicate!";	
				}
			}
			
			points.add(temp);
		}
		if(points.size() > 3) {
			points = GiftWrap.convexHull(points);
			Polygon shape = new Polygon(points);
			return "{ \"count\" : " + shape.getEncompassedPoints().size() + " }";
		} else {
			return "Malformed JSON: Not enough points to make a polygon!";
		}
	}
}
