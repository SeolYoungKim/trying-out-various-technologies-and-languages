package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

//Spring의 AOP는 AspectJ의 문법을 차용하고, 프록시 방식의 AOP를 제공함
@Slf4j
@Aspect
public class AspectV1 {
    @Around("execution(* hello.aop.order..*(..))")  //pointcut
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {  //advice
        log.info("[log] {}", joinPoint.getSignature());  //joinPoint 시그니처 (실행 요청된 메서드 정보가 들어와있다.)
        return joinPoint.proceed();  //target 호출
    }
}
