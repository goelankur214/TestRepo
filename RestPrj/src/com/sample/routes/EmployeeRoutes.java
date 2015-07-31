package com.sample.routes;

import org.apache.camel.builder.RouteBuilder;

public class EmployeeRoutes extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
		from("direct:emp1").to("mock:result");
		System.out.println("hello");
	}

}
