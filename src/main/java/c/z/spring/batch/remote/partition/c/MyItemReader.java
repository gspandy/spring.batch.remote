/**
 * 
 */
package c.z.spring.batch.remote.partition.c;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

/**
 * @author sunff
 *
 */
public class MyItemReader implements ItemReader<String> {

	private Long startIndex;
	private Long endIndex;
	
	public void setStartIndex(Long startIndex) {
		this.startIndex = startIndex;
	}

	public void setEndIndex(Long endIndex) {
		this.endIndex = endIndex;
	}

	@Override
	public String read() throws Exception, UnexpectedInputException,
			ParseException, NonTransientResourceException {
	System.out.println(String.format("read random data between %s and %s,threadname=%s", startIndex,endIndex,Thread.currentThread().getName()));
		return String.format("threadname=%s,startIndex=%s,endIndex=%s",Thread.currentThread().getName(), startIndex,endIndex);
	}

}
