/**
 * 
 */
package c.z.spring.batch.remote.jms;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

/**
 * @author sunff
 *
 */
public class JmsTest {

	/**
	 * @param args
	 * @throws JobParametersInvalidException
	 * @throws JobInstanceAlreadyCompleteException
	 * @throws JobRestartException
	 * @throws JobExecutionAlreadyRunningException
	 */
	public static void main(String[] args) throws Exception {

		System.setProperty(
				"org.apache.activemq.SERIALIZABLE_PACKAGES",
				"org.springframework.batch.integration.chunk,java.util,org.springframework.batch.core,org.springframework.batch.integration.chunk,java.lang");

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"/c/z/spring/batch/remote/jms/job-jms.xml");
		context.start();
		final JmsTemplate jmsTemplate = context.getBean("jmsTemplate",
				JmsTemplate.class);

		new Thread() {
			public void run() {
				System.out.println(String.format(
						"thread name is %s running...", Thread.currentThread()
								.getName()));
				int i = 0;
				while (true) {
					final int j = i;
					jmsTemplate.send(new MessageCreator() {

						public Message createMessage(Session session)
								throws JMSException {
							/*
							 * MapMessage mm = session.createMapMessage();
							 * mm.setLong("countTemplate", new
							 * Date().getTime()); return mm;
							 */
							TextMessage mm = session.createTextMessage();
							mm.setText(String
									.format("Threadname is %s ,current Time is %s,now sleep %s second",
											Thread.currentThread().getName(),

											System.currentTimeMillis(), 4));
							return mm;
						}

					});
					try {
						/*
						 * System.out.println(String.format(
						 * "Thread name is %s,now sleep %s second"
						 * ,Thread.currentThread().getName(), i));
						 */
						TimeUnit.SECONDS.sleep(i);
					} catch (InterruptedException e) {

						e.printStackTrace();
					}
					i++;
				}

			}
		}.start();

		final JobService JobService = context.getBean("jobService",
				JobService.class);
		JobService.runJob();
		System.out.println(String.format("ooook,currentTime is %s",
				new Date().toString()));

		TimeUnit.SECONDS.sleep(5);
		System.out.println(String.format("ooook2,currentTime is %s",
				new Date().toString()));
		System.in.read();
	}

	

}
