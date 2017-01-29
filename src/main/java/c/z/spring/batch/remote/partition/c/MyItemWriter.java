/**
 * 
 */
package c.z.spring.batch.remote.partition.c;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

/**
 * @author sunff
 *
 */
public class MyItemWriter implements ItemWriter<String>{

	
	@Override
	public void write(List<? extends String> items) throws Exception {
		System.out.println(String.format("Threadname=%s,write data into db", Thread.currentThread().getName()));
		
	}

}
