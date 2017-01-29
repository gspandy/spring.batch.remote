/**
 * 
 */
package c.z.spring.batch.remote.jms;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

/**
 * @author sunff
 *
 */
public class JmsChunkWriter implements ItemWriter<String>{

	
	@Override
	public void write(List<? extends String> items) throws Exception {
		for(String s:items){
			System.out.println(String.format("receive message is %s", s));
		}
		
	}

}
