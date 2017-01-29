
package c.z.spring.batch.remote.chunk.p;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;


public class ProducerItemReader implements ItemReader<String>{

	
	private static final AtomicInteger index=new AtomicInteger(0);
	public String read() throws Exception, UnexpectedInputException,
			ParseException, NonTransientResourceException {
		int i=index.getAndIncrement();
		System.out.println("read data from db..."+i);
		if(i==10000){
			return null;
		}
		return String.format("this is  %s  message", i);
	}
	
	
}