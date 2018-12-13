package demo.nospring.process;

import java.util.HashMap;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class PlanScheduler {

	private static SchedulerFactory schedulerFactory = new StdSchedulerFactory();
	private static String JOB_GROUP = "DEFAULT";
	private static String TRIGGER_GROUP = "DEFAULT";

	/**
	 * 通过addPlan()方法,给某个流程添加定时器，添加并开启一个【Schedule】
	 * 
	 * 
	 * @param variableMap
	 *            流程的参数集合
	 * @throws SchedulerException
	 */
	public void addPlan(HashMap<String, String> processParses) throws SchedulerException {

		JobDetail jobDetail = JobBuilder.newJob(PlanJob.class)
								.withIdentity("001", JOB_GROUP)
								.build();		
		jobDetail.getJobDataMap().put("processParses", processParses);// 流程表参数

		CronTrigger trigger = TriggerBuilder.newTrigger()
				.withIdentity("001", TRIGGER_GROUP)
				.withSchedule(CronScheduleBuilder.cronSchedule(processParses.get("cron")))
				.build();
		
		Scheduler scheduler = schedulerFactory.getScheduler();
		scheduler.scheduleJob(jobDetail, trigger);
		scheduler.start(); // 在内存中运行		
		
	}

	/**
	 * 内部类实现
	 *
	 * @author HONG
	 * @date: 2018年12月13日 下午4:34:11
	 */
	public static class PlanJob implements Job {	//不是static的话，JobBuilder.newJob(PlanJob.class)获取不到

		@Override
		public void execute(JobExecutionContext context) throws JobExecutionException {
			HashMap<String, String> process = (HashMap<String, String>) context.getJobDetail().getJobDataMap()
					.get("processParses");

			System.out.println("流程引擎启动：");

			System.out.println("流程ID：" + process.get("processId"));
			System.out.println("流程名字：" + process.get("name"));

			System.out.println("....");
		}

	}
}
