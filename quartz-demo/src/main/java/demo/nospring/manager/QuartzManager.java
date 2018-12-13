package demo.nospring.manager;
//https://www.cnblogs.com/Chiler/p/7765889.html

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzManager {
	private static SchedulerFactory sFactory = new StdSchedulerFactory();
	private static String JOB_GROUP_NAME = "FH_JOBGROUP_NAME";
	private static String TRIGGER_GROUP_NAME = "FH_TRIGGERGROUP_NAME";
	
	/**
	 * 添加一个定时任务，使用默认的任务组名，触发器名，触发器组名。
	 * @param jobName
	 * @param clazz
	 * @param time
	 */
	public static void addJob(String jobName, Class<? extends Job> clazz, String time){
		try {
			JobDetail jobDetail = JobBuilder.newJob(clazz)
									.withIdentity(jobName, JOB_GROUP_NAME)
									.build();
			
			CronTrigger trigger = (CronTrigger) TriggerBuilder.newTrigger()
									.withIdentity(jobName,TRIGGER_GROUP_NAME)
									.withSchedule(CronScheduleBuilder.cronSchedule(time))
									.build();
				
			Scheduler scheduler = sFactory.getScheduler();
			
			scheduler.scheduleJob(jobDetail, trigger);//配置
			
			scheduler.start();
			
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		
	}
}
