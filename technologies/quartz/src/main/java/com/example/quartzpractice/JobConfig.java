package com.example.quartzpractice;

import jakarta.annotation.PostConstruct;
import org.quartz.*;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

import static org.quartz.JobBuilder.newJob;

@Configuration
public class JobConfig {
    private final Scheduler scheduler;

    public JobConfig(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    @PostConstruct
    public void init() {
        final JobDetail detail = runJobDetail(TestJobA.class, new HashMap<>());

        try {
            scheduler.scheduleJob(detail, runJobTrigger("0/10 * * * * ?"));
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    private Trigger runJobTrigger(String scheduleExp) {
        return TriggerBuilder.newTrigger()
                .withSchedule(CronScheduleBuilder.cronSchedule(scheduleExp))
                .build();
    }

    private JobDetail runJobDetail(Class<? extends Job> job, Map<? extends String, ?> params) {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.putAll(params);

        return newJob(job).usingJobData(jobDataMap).build();
    }
}
