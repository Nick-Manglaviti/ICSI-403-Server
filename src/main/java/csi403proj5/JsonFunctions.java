package csi403proj5;

import java.util.ArrayList;
import javax.json.*;

public class JsonFunctions {

	public static String parseJsonArray(JsonArray inArray) {
		
		ArrayList<Vertex> vertices = new ArrayList<Vertex>();
		ArrayList<Edge> edges = new ArrayList<Edge>();
		
		for (int x = 0; x < inArray.size(); x++) {
			// Check Size of object
			if (inArray.getJsonObject(x).size() != 1) {
				return "Malformed JSON: Invalid Number of Arguements in inList index " + x;
			}
			
			// Check for connected array
			JsonArray connected;
			try {
				connected = inArray.getJsonObject(x).getJsonArray("connected");
				if (connected == null) {
					return "Malformed JSON: No connected variable at index "+x+" in inList!";
				}
			} catch (Exception e) {
				return "Malformed JSON: No array at index "+x+" in inList!";
			}
			if(connected.size() != 2) {
				return "Malformed JSON: Array at index "+x+" in inList is not size of 2!";
			}
			
			// Vertices Place Holders
			Vertex v1 = null;
			Vertex v2 = null;
			// Get Vertices Names
			String v1Name;
			try {
			v1Name = connected.getString(0);
			if(v1Name == null) {
				return "Malformed JSON: No name for Vertex 1 at index "+x+" in inList!";
			}
			} catch (Exception e) {
				return "Malformed JSON: No name for Vertex 1 at index "+x+" in inList!";
			}
			String v2Name;
			try {
			v2Name = connected.getString(1);
			if(v2Name == null) {
				return "Malformed JSON: No name for Vertex 2 at index "+x+" in inList!";
			}
			} catch (Exception e) {
				return "Malformed JSON: No name for Vertex 2 at index "+x+" in inList!";
			}
			// Search to Set/Create Vertices
			for(int y = 0; y < vertices.size(); y++) {
				if(vertices.get(y).getName().equals(v1Name)) {
					v1 = vertices.get(y);
				}
				if(vertices.get(y).getName().equals(v2Name)) {
					v2 = vertices.get(y);
				}
			}
			if(v1 == null) {
				v1 = new Vertex(v1Name);
				vertices.add(v1); //MARKED
			}
			if(v2 == null) {
				v2 = new Vertex(v2Name);
				vertices.add(v2);
			}
			
			// Create/Add Edge
			v1.getEdgeID().add(edges.size());
			v2.getEdgeID().add(edges.size());
			Edge e1 = new Edge(v1, v2);
			edges.add(e1);
		}
		if(vertices.size() < 1 && edges.size() < 1) {
			return "Malformed JSON: Structure is invalid, minimal requirements Vertices = 1 and Edges = 1";
		}
		Graph structure = new Graph(vertices, edges);
		
		// Bus
		if (structure.isBus()) {
			return "{ \"type\" : \"bus\" }";
		}
		// Ring
		if (structure.isRing()) {
			return "{ \"type\" : \"ring\" }";
		}
		// Star
		if (structure.isStar()) {
			return "{ \"type\" : \"star\" }";
		}
		
		return "{ \"type\" : \"irregular\" }";
	}
}
