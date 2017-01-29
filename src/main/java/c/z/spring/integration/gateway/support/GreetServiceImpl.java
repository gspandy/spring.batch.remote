/**
 * 
 */
package c.z.spring.integration.gateway.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import c.z.spring.integration.helloworld.GreetService;
import c.z.spring.integration.helloworld.HelloService;

/**
 * @author sunff
 *
 */
@Service
public class GreetServiceImpl implements GreetService {

	@Autowired
	@Qualifier("helloWorldGateway")
	private HelloService helloWorldGateway;

	@Override
	public void greet(String name) {
		System.out.println(helloWorldGateway.getHelloMessage(name)+"GreetServiceImpl");
	}

}
