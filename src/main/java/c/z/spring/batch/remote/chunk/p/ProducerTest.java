/**
 * 
 */
package c.z.spring.batch.remote.chunk.p;

import java.util.Date;





import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author sunff
 * 
 */
public class ProducerTest {

	/**
	 * @param args
	 * @throws JobParametersInvalidException 
	 * @throws JobInstanceAlreadyCompleteException 
	 * @throws JobRestartException 
	 * @throws JobExecutionAlreadyRunningException 
	 */
	public static void main(String[] args) throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
	
		System.setProperty("org.apache.activemq.SERIALIZABLE_PACKAGES", 
				"org.springframework.batch.integration.chunk,java.util,org.springframework.batch.core,org.springframework.batch.integration.chunk,java.lang");
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"/c/z/spring/batch/remote/chunk/p/job-chunk-remote-p.xml");
		context.start();
		JobLauncher jobLauncher=	context.getBean("jobLauncher", JobLauncher.class);
		
		Job remoteChunkJob=context.getBean("remoteChunkJob", Job.class);
		JobParametersBuilder builder=new JobParametersBuilder();
		builder.addDate("date", new Date());
		jobLauncher.run(remoteChunkJob, builder.toJobParameters());
		System.out.println("oook...");
	}

}
