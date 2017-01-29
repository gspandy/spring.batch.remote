/**
 * 
 */
package c.z.spring.batch.remote.partition.p;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;

/**自定义分区
 * @author sunff
 *
 */
public class MyPartitioner implements Partitioner{

	
	@Override
	public Map<String, ExecutionContext> partition(int gridSize) {
		System.out.println("now run mypatitioner..");
		int pageSize=30;
		Map<String, ExecutionContext> results=new HashMap<String,ExecutionContext>();
		for(int i=0;i<gridSize;i++){
			ExecutionContext context=new ExecutionContext();
			context.putLong("startIndex", i*pageSize);
			context.putLong("endIndex", (i+1)*pageSize);
			results.put("partition-"+i, context);
		}
		return results;
	}

}
