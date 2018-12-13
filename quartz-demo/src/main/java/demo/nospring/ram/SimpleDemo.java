package demo.nospring.ram;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class SimpleDemo {
	/*
	 * 三个基本要素：
	 * 
	 * （1）JobDetail
	 * （2）Trigger
	 * （3）Scheduler
	 */
	public static void main(String[] args) {
		try {
								
//			JobDetail jobDetail =JobBuilder.newJob(HelloJob.class)
//					.withIdentity("job1", "job_group1")
//					.build();
//			
//			//定义一个Trigger
//			SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder.repeatSecondlyForTotalCount(7);
//			
//			Trigger trigger = TriggerBuilder.newTrigger()
//					.withIdentity("trigger1", "trigger_group1")
//					.startNow()
//					.withSchedule(simpleScheduleBuilder)
//					.build();
//			
//			//创建 Scheduler
//			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
//			
//			//配置任务和触发器
//			scheduler.scheduleJob(jobDetail, trigger);
//			scheduler.start();
			
			SchedulerFactory schedulerFactory = new StdSchedulerFactory();
			

			JobDetail jobDetail = JobBuilder.newJob(HelloJob.class).withIdentity("1", "JOB_GROUP").build();
			
			Trigger trigger = TriggerBuilder.newTrigger().startNow().withIdentity("1", "TRIGGER_GROUP")
					.withSchedule(CronScheduleBuilder.cronSchedule("*/5 * * * * ?")).build();

			Scheduler scheduler = schedulerFactory.getScheduler();
			scheduler.scheduleJob(jobDetail, trigger);
			scheduler.start(); // 在内存中运行
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
