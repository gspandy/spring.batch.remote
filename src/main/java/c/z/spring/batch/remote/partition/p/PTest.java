/**
 * 
 */
package c.z.spring.batch.remote.partition.p;

import java.io.IOException;
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
public class PTest {

	private static void addPackage(StringBuilder sb,String p){
		if(sb.length()>0){
			sb.append(",");
			sb.append(p);
		}else{
			sb.append(p);
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		StringBuilder sb=new StringBuilder();
		addPackage(sb, "org.springframework.batch.integration.chunk");
		addPackage(sb, "java.util");
		addPackage(sb, "org.springframework.batch.core");
		addPackage(sb, "org.springframework.batch.integration.chunk");
		addPackage(sb, "org.springframework.batch.integration.partition");
		addPackage(sb, "java.lang");
		System.setProperty("org.apache.activemq.SERIALIZABLE_PACKAGES", 
				sb.toString());
		
		ClassPathXmlApplicationContext context=
				new ClassPathXmlApplicationContext("/c/z/spring/batch/remote/partition/p/spring-partition-remote-p.xml");
		context.start();
		System.out.println("running...");
		
JobLauncher jobLauncher=	context.getBean("jobLauncher", JobLauncher.class);
		
		Job remoteChunkJob=context.getBean("partitionRemoteJob", Job.class);
		JobParametersBuilder builder=new JobParametersBuilder();
		builder.addDate("date", new Date());
		jobLauncher.run(remoteChunkJob, builder.toJobParameters());
		System.out.println("oook...");
		
		try {
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}

}
