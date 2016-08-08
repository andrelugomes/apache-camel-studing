package br.com.caelum.camel;

import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.amqp.AMQPComponent;
import org.apache.camel.impl.DefaultCamelContext;

public class RotaAMQP {

	public static void main(String[] args) throws Exception {

        /**
         * The amqp: component supports the AMQP 1.0 protocol using the JMS Client API of the Qpid project.
         * In case you want to use AMQP 0.9 (in particular RabbitMQ) you might also be interested in the Camel RabbitMQ component.
         * Please keep in mind that prior to the Camel 2.17.0 AMQP component supported AMQP 0.9 and above, however since Camel 2.17.0 it supports only AMQP 1.0.
         */

        CamelContext context = new DefaultCamelContext();
		context.addComponent("amqp", AMQPComponent.amqpComponent("amqp://localhost:5672", "admin", "password"));

		context.addRoutes(new RouteBuilder() {

			@Override
			public void configure() throws Exception {
				
				from("file:pedidos?delay=5s&noop=true").
                log("Camel trabalhando com amqp!!").
                to("amqp:queue:queue-apollo-teste");
			}
			
		});

		context.start();
		Thread.sleep(2000);
		context.stop();
	}	
}
