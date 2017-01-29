package c.z.spring.integration.gateway.support;

import org.springframework.stereotype.Service;

import c.z.spring.integration.helloworld.HelloService;

/**
 * @author sunff
 *
 */
@Service("helloServiceImpl")
public class HelloServiceImpl implements HelloService{

	
	@Override
	public void hello(String name) {
		
		
	}

	
	@Override
	public String getHelloMessage(String name) {
		//System.out.println( "Hello, " + name );
		return "hello,"+name;
	}

}
