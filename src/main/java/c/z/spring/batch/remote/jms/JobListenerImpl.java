/**
 * 
 */
package c.z.spring.batch.remote.jms;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

/**
 * @author sunff
 *
 */
public class JobListenerImpl implements JobExecutionListener {

	
	private JobService jobService;
	
	/**
	 * @param jobService the jobService to set
	 */
	public void setJobService(JobService jobService) {
		this.jobService = jobService;
	}

	@Override
	public void beforeJob(JobExecution jobExecution) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		System.out.println("after job started value=before");
		jobService.notice();
		System.out.println("after job started value=after");
	

	}

}
