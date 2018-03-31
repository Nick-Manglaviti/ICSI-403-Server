package csi403proj3;
import java.util.HashSet;
import java.util.Set;

public class Vertex {
	// Data Fields
		private String name;
		private Set<Vertex> parents;
		private Set<Vertex> children;
		
		// Constructors for Vertices
		public Vertex() { }
		public Vertex(String name) {
			this.name = name;
			this.parents = new HashSet<Vertex>();
			this.children = new HashSet<Vertex>();
		}
		
		//Getters and Setters
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Set<Vertex> getParents() {
			return parents;
		}
		public void setParents(Set<Vertex> parents) {
			this.parents = parents;
		}
		public Set<Vertex> getChildren() {
			return children;
		}
		public void setChildren(Set<Vertex> children) {
			this.children = children;
		}
}
