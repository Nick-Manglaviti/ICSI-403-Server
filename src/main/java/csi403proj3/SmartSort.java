package csi403proj3;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class SmartSort {
	
	public static void dfsVisit(Vertex selected, ArrayList<Vertex> seen) {
		/* Visit all children of selected
		 * Create Iterator for children of selected
		 * add them to seen if not already in, do again*/
		Iterator<Vertex> it2 = selected.getChildren().iterator();
		while (it2.hasNext()) {
			Vertex child = it2.next();
			Iterator<Vertex> it3 = child.getParents().iterator();
			while (it3.hasNext()) {
				Vertex parent = it3.next();
				if((!seen.contains(parent)) && parent.getParents().isEmpty()) {
					seen.add(parent);
					dfsVisit(parent, seen);
				} else if (!seen.contains(parent)) {
					dfsVisit(parent, seen);
				}
			}
			if(!seen.contains(child)) {
				seen.add(child);
				dfsVisit(child, seen);
			}  
		}
	}
	
	public static String dfs(HashMap<String, Vertex> vertices) {
		/* Create a Seen Set, Iterator for Graph
		 * If this Vertex is an absolute parent and 
		 * has not been seen, visit it. */
		ArrayList<Vertex> seen = new ArrayList<Vertex>();	
		Iterator<Entry<String, Vertex>> it = vertices.entrySet().iterator();
		while(it.hasNext()) {
			Vertex current = it.next().getValue();
			if((!seen.contains(current)) && current.getParents().isEmpty()) {
				seen.add(current);
				dfsVisit(current, seen); // Visit it
			}
		}
		ArrayList<String> names = new ArrayList<String>();
		for(Vertex temp : seen) {
			names.add(temp.getName());
		}
		return names.toString();
	}
	/*
	public static void main(String args[]) {
		Graph geniuses = new Graph();
		Vertex v1 = new Vertex("Einstein");
		Vertex v10 = new Vertex("Einstein");
		Vertex v2 = new Vertex("Feymann");
		Vertex v3 = new Vertex("Gell-Man");
		Vertex v4 = new Vertex("Thorne");
		Vertex v5 = new Vertex("Lorentz");
		Vertex v6 = new Vertex("Planck");
		Vertex v7 = new Vertex("Hilbert");
		Vertex v8 = new Vertex("Noether");
		Vertex v9 = new Vertex("Poincare");
		geniuses.addEdge(v1, v2);
		geniuses.addEdge(v2, v3);
		geniuses.addEdge(v3, v4);
		geniuses.addEdge(v1, v2);
		geniuses.addEdge(v10, v5);
		geniuses.addEdge(v5, v6);
		geniuses.addEdge(v7, v8);
		geniuses.addEdge(v9, v8);
		
		System.out.println(dfs(geniuses.getVertices()));
	}
	*/
}
