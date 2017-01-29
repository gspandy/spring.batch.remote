/**
 * 
 */
package c.z.spring.integration.gateway;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import c.z.spring.integration.helloworld.GreetService;

/**
 * @author sunff
 *
 */
public class GatewayTest {

	
	public static void main(String[] args) {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"/c/z/spring/integration/gateway/spring-integration-gateway.xml");

		GreetService greeterService = applicationContext.getBean(
				"greetServiceImpl", GreetService.class);

		greeterService.greet("Spring Integration(with response)!");
		//applicationContext.close();	
	}
}
