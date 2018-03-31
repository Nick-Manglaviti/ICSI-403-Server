package csi403proj5;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		
		ArrayList<Vertex> vertices = new ArrayList<Vertex>();
		ArrayList<Edge> edges = new ArrayList<Edge>();
		
		// Square
		
		Vertex v1 = new Vertex("A");
		vertices.add(v1);
		Vertex v2 = new Vertex("B");
		vertices.add(v2);
		Vertex v3 = new Vertex("C");
		vertices.add(v3);
		Vertex v4 = new Vertex("D");
		vertices.add(v4);
		Vertex v5 = new Vertex("E");
		vertices.add(v5);
		v1.getEdgeID().add(edges.size());
		v3.getEdgeID().add(edges.size());
		Edge e1 = new Edge(v1, v3);
		edges.add(e1);
		v2.getEdgeID().add(edges.size());
		v3.getEdgeID().add(edges.size());
		Edge e2 = new Edge(v2, v3);
		edges.add(e2);
		v4.getEdgeID().add(edges.size());
		v3.getEdgeID().add(edges.size());
		Edge e3 = new Edge(v4, v3);
		edges.add(e3);
		v5.getEdgeID().add(edges.size());
		v3.getEdgeID().add(edges.size());
		Edge e4 = new Edge(v5, v3);
		edges.add(e4);
		
		
		// Triangle
		/*
		Vertex v5 = new Vertex("1");
		vertices.add(v5);
		Vertex v6 = new Vertex("2");
		vertices.add(v6);
		Vertex v7 = new Vertex("3");
		vertices.add(v7);
		v5.getEdgeID().add(edges.size());
		v6.getEdgeID().add(edges.size());
		Edge e5 = new Edge(v5, v6);
		edges.add(e5);
		v6.getEdgeID().add(edges.size());
		v7.getEdgeID().add(edges.size());
		Edge e6 = new Edge(v6, v7);
		edges.add(e6);
		v7.getEdgeID().add(edges.size());
		v5.getEdgeID().add(edges.size());
		Edge e7 = new Edge(v7, v5);
		edges.add(e7);
		*/
		
		Vertex v8 = new Vertex("?");
		vertices.add(v8);
		Vertex v9 = new Vertex("!");
		vertices.add(v9);
		v8.getEdgeID().add(edges.size());
		v9.getEdgeID().add(edges.size());
		Edge e8 = new Edge(v8, v9);
		edges.add(e8);
		
		
		Graph structure = new Graph(vertices, edges);
		System.out.println("Vertices = " + vertices.size() + "\nEdges = " + edges.size());
		
		// Bus
		if (structure.isBus()) {
		System.out.println("Bus");
		} else {
			System.out.println("NOPE HAHAhahah!");
		}
		// Ring
		if (structure.isRing()) {
		System.out.println("Ring");
		} else {
			System.out.println("NOT HAHAhahah!");
		}
		// Star
		if (structure.isStar()) {
		System.out.println("Stars");
		} else {
			System.out.println("Negative.");
		}
		
		
	}

}
