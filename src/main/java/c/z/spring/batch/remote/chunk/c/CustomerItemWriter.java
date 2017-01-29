

package c.z.spring.batch.remote.chunk.c;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

public class CustomerItemWriter implements ItemWriter<String>{

	public void write(List<? extends String> strings) throws Exception {
		for(String s:strings){
			System.out.println(s);
		}
		System.out.println("this is remote  customer writer...");
	}
	
	
	
}