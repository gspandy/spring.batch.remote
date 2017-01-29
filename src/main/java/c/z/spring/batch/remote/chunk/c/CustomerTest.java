package c.z.spring.batch.remote.chunk.c;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CustomerTest {

	public static void main(String[] args) {
		System.setProperty("org.apache.activemq.SERIALIZABLE_PACKAGES", 
				"org.springframework.batch.integration.chunk,java.util,org.springframework.batch.core,org.springframework.batch.integration.chunk");
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"/c/z/spring/batch/remote/chunk/c/job-chunk-remote-c.xml");
		context.start();
		System.out.println("running...");
		try {
			System.in.read();
		} catch (Exception e) {

		}
	}
}