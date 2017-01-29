/**
 * 
 */
package c.z.spring.integration.helloworld.support;

import org.springframework.stereotype.Service;

import c.z.spring.integration.helloworld.HelloService;

/**
 * @author sunff
 *
 */
@Service
public class HelloServiceImpl implements HelloService{

	
	@Override
	public void hello(String name) {
		System.out.println( "Hello, " + name );
		
	}

	
	@Override
	public String getHelloMessage(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
