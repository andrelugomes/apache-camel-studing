package br.com.caelum.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class RotaRabbitMQ {

	public static void main(String[] args) throws Exception {

		/**
		 * https://www.rabbitmq.com/specification.html
		 */
		CamelContext context = new DefaultCamelContext();

		context.addRoutes(new RouteBuilder() {

			@Override
			public void configure() throws Exception {
				
				from("file:pedidos?delay=5s&noop=true").
                log("Camel trabalhando com RabbitMQ!!").
				to("rabbitmq://192.168.99.100:5672/topic-exchange-test?exchangeType=topic&autoDelete=false&username=user&password=password&routingKey=queue-test");
				
			}
			
		});

		context.start();
		Thread.sleep(2000);
		context.stop();
	}	
}
