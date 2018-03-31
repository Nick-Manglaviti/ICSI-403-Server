package csi403proj4;

import java.util.ArrayList;

public class GiftWrap {
	
	// To find orientation of ordered triplet (p, q, r).
    // The function returns following values
    // 0 --> p, q and r are colinear
    // 1 --> Clockwise
    // 2 --> Counterclockwise
	public static int orientation(Point p, Point q, Point r) {
        int val = (q.getY() - p.getY()) * (r.getX() - q.getX()) -
                  (q.getX() - p.getX()) * (r.getY() - q.getY());
      
        if (val == 0) return 0; 
        return (val > 0)? 1: 2; 
    }

	// Prints convex hull of a set of n points.
    public static ArrayList<Point> convexHull(ArrayList<Point> points) {
      
        // Initialize Result
        ArrayList<Point> hull = new ArrayList<Point>();
      
        // Find the leftmost point
        int l = 0;
        for (int i = 1; i < points.size(); i++)
            if (points.get(i).getX() < points.get(l).getX())
                l = i;
        // Start from leftmost point, keep moving 
        // counterclockwise until reach the start point
        // again. This loop runs O(h) times where h is
        // number of points in result or output.
        int p = l, q;
        do 	{
            // Add current point to result
            hull.add(points.get(p));
            q = (p + 1) % points.size();
             
            for (int i = 0; i < points.size(); i++) {
               if (orientation(points.get(p), points.get(i), points.get(q)) == 2)
                   q = i;
            }
            p = q;
        } while (p != l);  // While we don't come to first 
                           // point
      
        // Return Ordered Hull
        return hull;
    }
}
	
	
	
	
	
	

