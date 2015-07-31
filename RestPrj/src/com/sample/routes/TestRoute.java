package com.sample.routes;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

import com.sample.Ids;
import com.sample.Person;

public class TestRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
		from("direct:test").process(new Processor() {

			@Override
			public void process(final Exchange ex) throws Exception {
				// TODO Auto-generated method stub
				final String str = ex.getIn().getBody(String.class);
				System.out.println("Exchange body " + str);
				System.out.println("hello");
			}
		}).to("mock:result");



		from("direct:test1").process(new Processor() {

			@Override
			public void process(final Exchange ex) throws Exception {
				// TODO Auto-generated method stub
				System.out.println("HI from route");
				final String str = ex.getIn().getBody(String.class);
				System.out.println(str);
			}
		}).to("mock:result");


		from("direct:test2").process(new Processor() {

			@Override
			public void process(final Exchange ex1) throws Exception {
				// TODO Auto-generated method stub
				final Ids str = ex1.getIn().getBody(Ids.class);
				System.out.println("Exchange body " + str.getPid());
			}
		}).to("mock:result");


		from("direct:test3").process(new Processor() {

			@Override
			public void process(final Exchange ex1) throws Exception {
				// TODO Auto-generated method stub
				final Person str = ex1.getIn().getBody(Person.class);
				System.out.println("Exchange body " + str.getName());
			}
		}).to("mock:result");

		/*.setHeader(Exchange.HTTP_METHOD, constant(HttpMethod.GET))
				.to("http://localhost:8080/RestPrj/rest/hello/plain2")
				.process(new Processor() {

					@Override
					public void process(Exchange arg0) throws Exception {
						// TODO Auto-generated method stub
						String str = arg0.getIn().getBody(String.class);
						System.out.println("Exchange body" + str);

						arg0.getIn().setBody(str);
					}
				});
	}*/

}
}
