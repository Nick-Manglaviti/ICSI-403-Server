package csi403proj3;
// Import required java libraries
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.json.*;
import java.util.PriorityQueue;

// Inner Class Defined -----------------------------------------------------------
class Job implements Comparable<Job>{
	// Data Fields
	private String name;
	private int value;
	
	// Constructors for Job
	public Job() { }
	
	public Job(String name, int value) {
		this.name = name;
		this.value = value;
	}

	// Getters and Setters
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	
	public int compareTo(Job other) {
		if(this.equals(other)) {
			return 0;
		} else if (this.getValue() > other.getValue()) {
			return 1;
		} else {
			return -1;
		}
	}
	
	@Override
	public String toString() {
		return name;
	}
}//------------------------------------------------------------------------------


// Extend HttpServlet class
public class Prioritize extends HttpServlet {

  // Standard servlet method 
  public void init() throws ServletException
  {
      // Do any required initialization here - likely none
  }

  // Standard servlet method - we will handle a POST operation
  public void doPost(HttpServletRequest request,
                    HttpServletResponse response)
            throws ServletException, IOException
  {
      doService(request, response); 
  }

  // Standard servlet method - we will not respond to GET
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
            throws ServletException, IOException
  {
      // Set response content type and return an error message
      response.setContentType("application/json");
      PrintWriter out = response.getWriter();
      out.println("{ 'message' : 'Use POST!'}");
  }


  // Our main worker method
  // Parses messages e.g. 
  // {"inList" : [
  // {"cmd" : "enqueue", "name" : "job1", "pri" : 4},
  // {"cmd" : "dequeue"},
  //              ]}
  // Returns the list Prioritize.   
  private void doService(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
				
      // Get received JSON data from HTTP request
      BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
      String jsonStr = "";
      if(br != null) {
          jsonStr = br.readLine();
		}
      
	  // Create JsonReader object
      StringReader strReader = new StringReader(jsonStr);
      JsonReader reader = Json.createReader(strReader);
	  // Get the singular JSON object (name:value pair) in this message.    
      JsonObject obj = reader.readObject();
      // From the object get the array named "inList"
	  JsonArray inArray = obj.getJsonArray("inList");
	  // Send back the response JSON message
      PrintWriter out = response.getWriter();
	  // Set response content type to be JSON
      response.setContentType("application/json");
	  // Out Array
	  JsonArrayBuilder outArrayBuilder = Json.createArrayBuilder();
	  
	  
	  //Init PriQ and OutArray
	  PriorityQueue<Job> queue = new PriorityQueue<Job>();
	  
      for (int i = 0; i < inArray.size(); i++) {
		  String enq = "enqueue";
		  String deq = "dequeue";
		  
		  String command;
		  try {
		  command = inArray.getJsonObject(i).getJsonString("cmd").toString();
		  } catch (Exception e) {
				  out.println("Error: No cmd Command Found at Index " + i);return;
			}
		  command = command.substring(1, command.length()-1);  
		  
		  if (command.equals(enq)) {
			  // Check Arguments
			  if (inArray.getJsonObject(i).size() != 3) {
				  out.println("Error: Invalid Number of Arguments at Index " + i);return;
			  }
			  // Deal with name command
			  String jobName;
			  try {
			  jobName = inArray.getJsonObject(i).getJsonString("name").getString();
			  } catch (Exception e) {
				  out.println("Error: No Name Command Found at Index " + i);return;
			  }
			  if (jobName.equals(null)) {
				  out.println("Error: No Name Given at Index " + i);return;
			  }

			  // Deal with pri command
			  Double jobValue;
			  try {
			  jobValue = (double) inArray.getJsonObject(i).getJsonNumber("pri").doubleValue();
			  } catch (Exception e) {
				  out.println("Error: Could Not Find pri Element in index " + i);return;
			  }
			  if (jobValue % 1 != 0 || jobValue == null) {
				  out.println("Error: No valid Integer at index " + i);return;
			  }
			  int jobIntValue = jobValue.intValue();
			  
			  // Put in Queue
			  Job tempJob = new Job(jobName, jobIntValue);
			  queue.add(tempJob);
		  }
		   else if (command.equals(deq)) {
			   if (inArray.getJsonObject(i).size() != 1) {
				  out.println("Error: Invalid Number of Arguments at Index " + i);return;
			  }
			   if(queue.isEmpty()) {
				   out.println("Error: Cannot Dequeue Empty List");return;
			   }
			   queue.remove();
		   } else {
			  out.println("Error: Invalid Command " + command + " at index " + i);return;
		     }
		 
      }
	  
	  while (!queue.isEmpty()) {
    	  outArrayBuilder.add(queue.remove().toString());
      }
      out.println("{ \"outList\" : " + outArrayBuilder.build().toString() + "}"); 
  }
	  
  // Standard Servlet method
  public void destroy()
  {
      // Do any required tear-down here, likely nothing.
  }
}

