package hello.aop.pointcut;

import static org.assertj.core.api.Assertions.assertThat;

import hello.aop.member.MemberServiceImpl;
import java.lang.reflect.Method;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

@Slf4j
public class ExecutionTest {
    AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
    Method helloMethod;
    Method stringValueOfMethod;

    @BeforeEach
    void setUp() throws NoSuchMethodException {
        helloMethod = MemberServiceImpl.class.getMethod("hello", String.class);
        stringValueOfMethod = String.class.getMethod("valueOf", int.class);
    }

    @Test
    void printMethod() {
        // public java.lang.String hello.aop.member.MemberServiceImpl.hello(java.lang.String)
        log.info("helloMethod={}", helloMethod);
    }

    @Test
    void exactMatch() {
        // public java.lang.String hello.aop.member.MemberServiceImpl.hello(java.lang.String)
        pointcut.setExpression("execution(public String hello.aop.member.MemberServiceImpl.hello(String))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
        assertThat(pointcut.matches(stringValueOfMethod, String.class)).isFalse();
    }

    @Test
    void allMatch() {
        pointcut.setExpression("execution(* *(..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
        assertThat(pointcut.matches(stringValueOfMethod, String.class)).isTrue();
    }

    @Test
    void nameMatch() {
        pointcut.setExpression("execution(* hello(..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void nameMatchStar1() {
        pointcut.setExpression("execution(* hel*(..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
        assertThat(pointcut.matches(stringValueOfMethod, String.class)).isFalse();
    }

    @Test
    void nameMatchStar2() {
        pointcut.setExpression("execution(* *el*(..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
        assertThat(pointcut.matches(stringValueOfMethod, String.class)).isFalse();
    }

    @Test
    void nameMatchFalse() {
        pointcut.setExpression("execution(* nono(..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isFalse();
        assertThat(pointcut.matches(stringValueOfMethod, String.class)).isFalse();
    }

    @Test
    void packageExactMatch() {
        pointcut.setExpression("execution(* hello.aop.member.MemberServiceImpl.hello(..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
        assertThat(pointcut.matches(stringValueOfMethod, String.class)).isFalse();
    }

    @Test
    void packageExactMatch2() {
        pointcut.setExpression("execution(* hello.aop.member.*.*(..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
        assertThat(pointcut.matches(stringValueOfMethod, String.class)).isFalse();
    }

    @Test
    void packageExactFalse() {
        pointcut.setExpression("execution(* hello.aop.*.*(..))");  // 정확하게 hello.aop 패키지여야 함
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isFalse();
        assertThat(pointcut.matches(stringValueOfMethod, String.class)).isFalse();
    }

    @Test
    void packageMatchSubPackage1() {
        pointcut.setExpression("execution(* hello.aop.member..*.*(..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
        assertThat(pointcut.matches(stringValueOfMethod, String.class)).isFalse();
    }

    @Test
    void packageMatchSubPackage2() {
        pointcut.setExpression("execution(* hello.aop..*.*(..))");  // aop 패키지와 그 하위에 있는 모든 것
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
        assertThat(pointcut.matches(stringValueOfMethod, String.class)).isFalse();
    }

    @Test
    void typeExactMatch() {
        pointcut.setExpression("execution(* hello.aop.member.MemberServiceImpl.*(..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
        assertThat(pointcut.matches(stringValueOfMethod, String.class)).isFalse();
    }

    @Test
    void typeMatchSuperType() {
        pointcut.setExpression("execution(* hello.aop.member.MemberService.*(..))");  // 상위 타입으로도 매치 가능
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
        assertThat(pointcut.matches(stringValueOfMethod, String.class)).isFalse();
    }

    @Test
    void typeMatchInternal() throws NoSuchMethodException {
        pointcut.setExpression("execution(* hello.aop.member.MemberService.*(..))");  // 상위 타입에 선언된 메서드만 매치 가능 (자식 타입은 매치가 되지만, 자식 타입만의 메서드는 매치 X)
        final Method internalMethod = MemberServiceImpl.class.getMethod("internal", String.class);

        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
        assertThat(pointcut.matches(internalMethod, String.class)).isFalse();
    }

    @Test
    void typeMatchInternal2() throws NoSuchMethodException {
        pointcut.setExpression("execution(* hello.aop.member.MemberServiceImpl.*(..))");
        final Method internalMethod = MemberServiceImpl.class.getMethod("internal", String.class);

        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
        assertThat(pointcut.matches(internalMethod, String.class)).isTrue();
    }

    //String 타입의 파라미터 허용
    @Test
    void argsMatch() {
        pointcut.setExpression("execution(* *(String))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
        assertThat(pointcut.matches(stringValueOfMethod, String.class)).isFalse();
    }

    @Test
    void argsMatchNoArgs() {
        pointcut.setExpression("execution(* *())");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isFalse();
        assertThat(pointcut.matches(stringValueOfMethod, String.class)).isFalse();
    }

    //정확히 하나의 파라미터만 허용, 모든 타입 허용
    @Test
    void argsMatchStar() {
        pointcut.setExpression("execution(* *(*))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
        assertThat(pointcut.matches(stringValueOfMethod, String.class)).isTrue();
    }

    //갯수와 무관하게 모든 파라미터, 모든 타입 허용
    @Test
    void argsMatchAll() {
        pointcut.setExpression("execution(* *(..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
        assertThat(pointcut.matches(stringValueOfMethod, String.class)).isTrue();
    }

    //String 타입으로 시작, 숫자와 무관하게 모든 파라미터, 모든 타입 허용
    //(String), (String, Xxx), (String, Xxx, ...)
    @Test
    void argsMatchComplex() {
        pointcut.setExpression("execution(* *(String, ..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
        assertThat(pointcut.matches(stringValueOfMethod, String.class)).isFalse();
    }
}
