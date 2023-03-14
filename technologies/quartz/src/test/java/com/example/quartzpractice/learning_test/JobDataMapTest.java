package com.example.quartzpractice.learning_test;

import org.quartz.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//http://www.quartz-scheduler.org/documentation/quartz-2.3.0/tutorials/tutorial-lesson-03.html
//Job “Instances”부터 다시 하기
public class JobDataMapTest {
    /**
     * Quartz의 기본 JobFactory 구현체는 setter를 자동으로 호출
     */
    static class DumbJob implements Job {

        @Override
        public void execute(JobExecutionContext context) throws JobExecutionException {
            JobKey key = context.getJobDetail().getKey();

//            JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();  // 직렬화되어 클래스 버전관리 문제가 발생하기 쉬움 (직렬화 문제 주의)
            JobDataMap jobDataMap = context.getMergedJobDataMap();
            String jobSays = jobDataMap.getString("jobSays");
            float myFloatValue = jobDataMap.getFloat("myFloatValue");
            List<Date> state = (List<Date>) jobDataMap.get("myStateData");
            state.add(new Date());

            System.err.println("Instance " + key + " of DumbJob says: " + jobSays + ", and val is: " + myFloatValue);
        }
    }

    static class DumbJob2 implements Job {
        String jobSays;
        float myFloatValue;
        List<Date> state;

        @Override
        public void execute(JobExecutionContext context) throws JobExecutionException {
            JobKey key = context.getJobDetail().getKey();

//            JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();  // 직렬화되어 클래스 버전관리 문제가 발생하기 쉬움 (직렬화 문제 주의)
            JobDataMap jobDataMap = context.getMergedJobDataMap();
            state.add(new Date());

            System.err.println("Instance " + key + " of DumbJob says: " + jobSays + ", and val is: " + myFloatValue);
        }

        public void setJobSays(String jobSays) {
            this.jobSays = jobSays;
        }

        public void setMyFloatValue(float myFloatValue) {
            this.myFloatValue = myFloatValue;
        }

        public void setState(List<Date> state) {
            this.state = state;
        }
    }
}
