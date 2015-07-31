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

// The class registers its methods for the HTTP GET request using the @GET annotation.
// Using the @Produces annotation, it defines that it can deliver several MIME types,
// text, XML and HTML.

// The browser requests per default the HTML MIME type.

//Sets the path to base URL + /hello
@Path("/hello")
public class Hello {
	ProducerTemplate template;
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


	@POST
	@Path("/json2")
	@Consumes(MediaType.APPLICATION_JSON)
	public String sayJSONOutput(final Person p) {

		/*
		 * "Person name =" + p.setName() + "Person place =" + p.getPlace() + "person dob =" + p.getDob()
		 */
		//p.setName("nischitha"); p.setPlace("bangalore"); p.setDob("jan 01");
		  System.out.println(p.getName());

		return "Person name =" + p.getName() + "Person place =" + p.getPlace() + "person dob =" + p.getDob();

	}

	// This method is called if TEXT_PLAIN is request(GET method with id in the path)


	@GET
	@Path("/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Person sayJSONOutput1(){
		final Person p = new Person();
		p.setName("nischitha"); p.setPlace("bangalore"); p.setDob("jan 01");
		startContext();
		System.out.println("inside ");
		 final Person response = template.requestBody("direct:test3",p, Person.class);
		 System.out.println("final res " + response.getName() + " " + response.getDob() + " " + response.getPlace());

		return p;
	}

	// This method is called if JSON is request(GET method)

	// This method is called if TEXT_PLAIN is request(GET method)
	@GET
	@Path("/plain")
	@Produces(MediaType.TEXT_PLAIN)
	public String sayPlainTextHello() {

		try {
			startContext();
			 System.out.println("inside ");
			 final String response = template.requestBody("direct:test","hi", String.class);
			 System.out.println("final response " + response);
		} catch (final Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Hello Jersey -->plain text";
	}


	// This method is called if JSON is request(POST method)

	// This method is called if TEXT_PLAIN is request(POST method)
	@POST
	@Path("/plain1")
	@Consumes(MediaType.TEXT_PLAIN)
	public String sayPlainTextHello(final String name) {
		startContext();

		final String str = template.requestBody("direct:test1",name, String.class);
	    System.out.println(" inside sayPlainTextHello method -------");
		return str ;

	}



	// This method is called if JSON is request(POST method)

	@POST
	@Path("/json1/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public String sayPlainTextHello1(@PathParam("id") final String pathParamId) {

		//Person p = new Person();
		System.out.println("done" + pathParamId);
		return pathParamId;

	}


	@GET
	@Path("/{pid}/plain2/justplain/{cid}")
	@Produces(MediaType.TEXT_HTML)
	public String sayPlainTextHello1(
			@PathParam(value = "pid") final String pid,
			@PathParam (value = "cid") final String cid) {
		final Ids id = new Ids();
		id.setPid(pid);
		id.setCid(cid);
		startContext();
final Ids str = template.requestBody("direct:test2",id,Ids.class);
		return " success with " + " " + str.getPid() + " " + str.getCid();
	}




	// This method is called if XML is request
	@GET
	@Path("/{dataformat}/xml/name/{age}")
	@Produces(MediaType.TEXT_HTML)
	public String sayXMLHello(
			@PathParam(value = "dataformat") final String dataformat,
			@PathParam(value = "age") final int age,
			@QueryParam(value = "name") final String name) {

		try {

			startContext();
			template.requestBody("direct:test2", name ,String.class);
			System.out.println("done " + name);
		} catch (final Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//return dataformat;
		return "<?xml version=\"1.0\"?>" + "<hello> Hello Jersey--> XML"
				+ "</hello>" + dataformat + name + age;
	}

	public void startContext(){

		try {
			final CamelContext context = new DefaultCamelContext();
			context.addRoutes(new TestRoute());
			context.start();
			template = context.createProducerTemplate();
		} catch (final Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}





}