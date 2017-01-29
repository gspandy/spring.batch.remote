/**
 * 
 */
package c.z.spring.integration.helloworld.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;

import c.z.spring.integration.helloworld.GreetService;

/**
 * @author sunff
 *
 */
@Service
public class GreetServiceImpl implements GreetService {

	@Autowired
	private MessageChannel helloWorldChannel;

	@Override
	public void greet(String name) {
		helloWorldChannel.send(MessageBuilder.withPayload(name).build());
	}

}
