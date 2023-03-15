package com.example.quartzpractice;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Comment;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TestJobA extends QuartzJobBean {
    /**
     * execute 메서드를 호출하기 전에 매번 새 인스턴스를 만든다.
     * 실행 완료 후에는 Job 클래스 인스턴스에 대한 참조가 삭제되고, 인스턴스가 GC된다.
     * - 기본 생성자가 있어야 함.
     * - 상태를 갖는 데이터필드를 정의하는 것이 아무런 의미가 없음. (매번 새로 생성되어 값이 보존되지 않기 떄문)
     */
    public TestJobA() {
        log.info("TestJobA 객체 생성");
        log.info("TestJobA class: {}", this.getClass());
        log.info("TestJobA hashCode: {}", this.hashCode());
    }

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        log.info("10초마다 Job이 실행된다.");
    }
}
