package br.com.caelum.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class RotaStomp {

	public static void main(String[] args) throws Exception {

        /**
         * http://camel.apache.org/stomp.html
         */

        CamelContext context = new DefaultCamelContext();
		context.addRoutes(new RouteBuilder() {

			@Override
			public void configure() throws Exception {
				
				from("file:pedidos?delay=5s&noop=true").
                log("Camel trabalhando com stomp!!").
				to("stomp:queue:queue-apollo-stomp?brokerURL=tcp://localhost:61613&login=admin&passcode=password");
				
			}
			
		});

		context.start();
		Thread.sleep(2000);
		context.stop();
	}	
}
