# ICSI-403-Server
A restful service that uses AWS to parse JSON posts into Java, operate on them, then return a result back into JSON.

Used Elastic Beanstalk on Amazon Web Services.
Maintained by Tomcat and built using Maven to create a .WAR file.

Contains 5 projects that parse JSON posts into java.
1) Reverse List: A servlet to reverse a JSON list.
2) Prioritize: A servlet to sort a JSON list using Insertion Sort in Java.
3) Topological Sort: A servlet that would take a JSON list of ( "smarter" : ["Name1", "Name2"]), create a graph
off the relations, then perform topological sort on it. Returns a list of people from smart to smartest.
4) Polygon Generator: A servlet that would take x and y coordinates, make a polygon from them, then return all points that
would be eclosed by the polygon.
5) Structure Detector: A servlet that would take a list of nodes and edges, create the shape, then would determine which
of the three shapes it could be. The three topologies are ring, bus, and star.
