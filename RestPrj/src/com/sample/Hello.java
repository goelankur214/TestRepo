package com.sample;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultCamelContext;

import com.sample.routes.TestRoute;
import com.sun.org.apache.xalan.internal.xsltc.compiler.Template;
// Plain old Java Object it does not extend as class or implements 
// an interface

// The class registers its methods for the HTTP GET request using the @GET annotation. 
// Using the @Produces annotation, it defines that it can deliver several MIME types,
// text, XML and HTML. 

// The browser requests per default the HTML MIME type.

//Sets the path to base URL + /hello
@Path("/hello")
public class Hello {
	ProducerTemplate template;
	public void startContext(){
		
		try {
			CamelContext context = new DefaultCamelContext();
			context.addRoutes(new TestRoute());
			context.start();
			template = context.createProducerTemplate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	// This method is called if TEXT_PLAIN is request(GET method)
	@GET
	@Path("/plain")
	@Produces(MediaType.TEXT_PLAIN)
	public String sayPlainTextHello() {
		
		try {
			startContext();
			 System.out.println("inside ");
			 String response = template.requestBody("direct:test","hi", String.class);
			 System.out.println("final response " + response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Hello Jersey -->plain text";
	}
	
	
	// This method is called if TEXT_PLAIN is request(POST method)
	@POST
	@Path("/plain1")
	@Consumes(MediaType.TEXT_PLAIN)
	public String sayPlainTextHello(String name) {
		startContext();
		
		String str = template.requestBody("direct:test1",name, String.class);
	    System.out.println(" inside sayPlainTextHello method -------");
		return str ;

	}
 
	// This method is called if TEXT_PLAIN is request(GET method with id in the path)
	
	
	@GET
	@Path("/{pid}/plain2/justplain/{cid}")
	@Produces(MediaType.TEXT_PLAIN)
	public String sayPlainTextHello1(
			@PathParam(value = "pid") String pid,
			@PathParam (value = "cid") String cid) {
		Ids id = new Ids();
		id.setPid(pid);
		id.setCid(cid);
		startContext();
Ids str = template.requestBody("direct:test2",id,Ids.class);
		return " success with " + " " + str.getPid() + " " + str.getCid();
	}
	
	// This method is called if JSON is request(GET method)
	
	@GET
	@Path("/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Person sayJSONOutput1(){
		Person p = new Person();
		p.setName("nischitha"); p.setPlace("bangalore"); p.setDob("jan 01");
		startContext();
		System.out.println("inside ");
		 Person response = template.requestBody("direct:test3",p, Person.class);
		 System.out.println("final res " + response.getName() + " " + response.getDob() + " " + response.getPlace());
		
		return p;
	}
	
	
	// This method is called if JSON is request(POST method)
	
	@POST
	@Path("/json1/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public String sayPlainTextHello1(@PathParam("id") String pathParamId) {

		//Person p = new Person();
		System.out.println("done" + pathParamId);
		return pathParamId;

	}

	
	
	// This method is called if JSON is request(POST method)
	
	@POST
	@Path("/json2")
	@Consumes(MediaType.APPLICATION_JSON)
	public String sayJSONOutput(Person p) {

		/*
		 * "Person name =" + p.setName() + "Person place =" + p.getPlace() + "person dob =" + p.getDob()
		 */
		//p.setName("nischitha"); p.setPlace("bangalore"); p.setDob("jan 01");
		  System.out.println(p.getName());

		return "Person name =" + p.getName() + "Person place =" + p.getPlace() + "person dob =" + p.getDob();

	}

	
	// This method is called if XML is request
	@GET
	@Path("/{dataformat}/xml/name/{age}")
	@Produces(MediaType.TEXT_XML)
	public String sayXMLHello(
			@PathParam(value = "dataformat") String dataformat,
			@PathParam(value = "age")int age,
			@QueryParam(value = "name") String name) {
		
		try {
			
			startContext();
			String str = template.requestBody("direct:test2", name ,String.class);
			System.out.println("done " + name);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//return dataformat; 
		return "<?xml version=\"1.0\"?>" + "<hello> Hello Jersey--> XML"
				+ "</hello>" + dataformat + name + age;
	}

	
	
	
	// This method is called if HTML is request
	@GET
	@Path("/html")
	@Produces(MediaType.TEXT_HTML)
	public String sayHtmlHello() {
		return "<html> " + "<title>" + "Neha" + "</title>" + "<body><h1>"
				+ "Hello Jersey---> HTML" + "</body></h1>" + "</html> ";
	}

	@GET
	@Produces(MediaType.TEXT_HTML)
	public String sayHtmlHello1() {
		return "<html> " + "<title>" + "Nischitha" + "</title>" + "<body><h1>"
				+ "Hello---> just GET method" + "</body></h1>"
				+ "</html> ";
	}
	
	

	

}