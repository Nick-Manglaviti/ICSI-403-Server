package csi403proj4;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		ArrayList<Point> points = new ArrayList<Point>();
		/*
		Point point1 = new Point(-2,-4);
		Point point2 = new Point(6,-1);
		Point point3 = new Point(6,5);
		Point point4 = new Point(0,8);
		Point point5 = new Point(-5,3);
		
		points.add(point1);
		points.add(point2);
		points.add(point3);
		points.add(point4);
		points.add(point5);
		*/
		Point point1 = new Point(3,1);
		Point point2 = new Point(2,2);
		Point point3 = new Point(4,2);
		Point point4 = new Point(3,3);
		
		points.add(point1);
		points.add(point2);
		points.add(point3);
		points.add(point4);
		
		points = GiftWrap.convexHull(points);
		
		Polygon raging = new Polygon(points);
		
		System.out.println(raging.toString());
	}

}
