/**
 * 
 */
package c.z.spring.batch.remote.jms;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author sunff
 *
 */
@Service
public class JobService {

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private Job remoteChunkJob;

	public void notice() {		
		runJob();
	}

	
	private ExecutorService executor=Executors.newFixedThreadPool(2);
	public void runJob() {
		executor.execute(new Runnable() {
			
			@Override
			public void run(){
				System.out.println("run job...."+Thread.currentThread().getName());
				JobParametersBuilder builder = new JobParametersBuilder();
				builder.addLong("job-run-id", System.currentTimeMillis());
				try {
					jobLauncher.run(remoteChunkJob, builder.toJobParameters());
				} catch (JobExecutionAlreadyRunningException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JobRestartException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JobInstanceAlreadyCompleteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JobParametersInvalidException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	
		
	}
}
