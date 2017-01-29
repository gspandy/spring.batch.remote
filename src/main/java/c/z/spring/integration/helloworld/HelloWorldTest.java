/**
 * 
 */
package c.z.spring.integration.helloworld;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author sunff
 *
 */
public class HelloWorldTest {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"/c/z/spring/integration/helloworld/spring-integration-helloworld.xml");

		GreetService greeterService = applicationContext.getBean(
				"greetServiceImpl", GreetService.class);

		greeterService.greet("Spring Integration!");
		applicationContext.close();	
	}
}
