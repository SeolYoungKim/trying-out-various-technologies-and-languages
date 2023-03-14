package com.example.quartzpractice.learning_test;

import com.example.quartzpractice.TestJobA;
import org.junit.jupiter.api.Test;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.*;

public class QuartzTest {
    public static void main(String[] args) {
        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            registerNewJob(scheduler);
            scheduler.start();
            Thread.sleep(60000);
            scheduler.shutdown();


        } catch (SchedulerException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class HelloJob implements Job {
        @Override
        public void execute(JobExecutionContext context) throws JobExecutionException {
            System.out.println("Hello! HelloJob is executing.");
        }
    }

    private static void registerNewJob(final Scheduler scheduler) throws SchedulerException {
        JobDetail job = newJob(HelloJob.class)
                .withIdentity("job1", "group1")
                .build();

        // SimpleTrigger : 주어진 시간에 작업을 한 번만 실행, 혹은 주어진 시간에 작업을 시작하고 지연 시간을 두고 N회 반복해야 하는 경우 유용
        // CronTrigger : 매주 금요일, 정오 또는 매월 10일 10시 15분과 같은 일정에 따라 트리거 하려는 경우에 유용 (달력 기반)
        Trigger trigger = newTrigger()
                .withIdentity("trigger1", "group1")
                .withSchedule(simpleSchedule()
                        .withIntervalInMilliseconds(10)
                        .repeatForever())
                .build();

        scheduler.scheduleJob(job, trigger);
    }
}
