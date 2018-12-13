package demo.nospring.process;

import java.util.HashMap;

import org.quartz.SchedulerException;

public class ProcessTest {
	
	public static void main(String[] args) throws SchedulerException {
		
		
		// 1.编辑Process参数：
		HashMap<String, String> processParses = new HashMap<>();
		processParses.put("processId", "001");
		processParses.put("cron", "*/3 * * * * ?");
		processParses.put("name", "Process_01");
		
		
		// 2.加入后，自动启动一个Scheduler并启动
		PlanScheduler planScheduler = new PlanScheduler();
		planScheduler.addPlan(processParses);
	}
}
