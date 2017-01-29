/**
 * 
 */
package c.z.spring.batch.remote.chunk.p;

import org.springframework.batch.integration.chunk.ChunkResponse;
import org.springframework.batch.integration.chunk.JmsRedeliveredExtractor;
import org.springframework.integration.annotation.Header;
import org.springframework.integration.jms.JmsHeaders;

/**
 * @author sunff
 *
 */
public class MyJmsRedeliveredExtractor  extends JmsRedeliveredExtractor{

	@Override
	public ChunkResponse extract(ChunkResponse input, @Header(JmsHeaders.REDELIVERED) boolean redelivered) {
		ChunkResponse c=super.extract(input, redelivered);
		System.out.println(c.toString());
		return c;
	}
}
