package com.sample;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultCamelContext;
import com.sample.routes.EmployeeRoutes;

public class ContextDetails {
ProducerTemplate template;
	public void startContext(){
		
		try {
			CamelContext context = new DefaultCamelContext();
			context.addRoutes(new EmployeeRoutes());
			context.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
