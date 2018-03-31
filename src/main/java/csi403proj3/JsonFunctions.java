package csi403proj3;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.json.*;

public class JsonFunctions {

	public static String parseJsonArray(JsonArray inArray) {
		Graph geniusGraph = new Graph();
		
		for (int x = 0; x < inArray.size(); x++) {
			// Check Size of object
			if (inArray.getJsonObject(x).size() != 1) {
				return "Error: Invalid Number of Arguements in inArray index " + x;
			}
			// Try to Get the Array
			JsonArray geniuses;
			try {
				geniuses = inArray.getJsonObject(x).getJsonArray("smarter");
			} catch (Exception e) {
				return "Error: No smarter object found at index "+x+" in in Array";
			}
			// If Array is size 2
			if(geniuses.size() != 2) {
				return "Error: Array at index " + x + " in inArray does not have 2 elements!";
			}
			// Get the Names
			String first = geniuses.get(0).toString();
			String second = geniuses.get(1).toString();
			// Make sure they are not numbers
			if (isNumeric(first) || isNumeric(second)) {
				return "Error: Array at index " + x + " in inArray has a number!";
			}
			if (first.equals(second)) {
				return "Error: Array at index " + x + " in inArray cannot have the same person twice!";
			}
			Vertex v1 = new Vertex(first);
			Vertex v2 = new Vertex(second);
			geniusGraph.addEdge(v1, v2);
		}
		return "{ \"outList\" : " + SmartSort.dfs(geniusGraph.getVertices()) + "}";
	}
	
	public static boolean isNumeric(String str) {  
	  try {  
	    double d = Double.parseDouble(str);
	  }  
	  catch(NumberFormatException nfe) {  
	    return false;  
	  }  
	  return true;  
	}
}
