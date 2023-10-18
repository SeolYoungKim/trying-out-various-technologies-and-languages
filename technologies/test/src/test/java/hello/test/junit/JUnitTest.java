package hello.test.junit;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class JUnitTest {
    static Set<JUnitTest> testObjects = new HashSet<>();

    @Test
    void objTest1() {
        assertThat(testObjects).doesNotContain(this);
        testObjects.add(this);

        System.out.println("[Test1] testObjects = " + testObjects);
    }

    @Test
    void objTest2() {
        assertThat(testObjects).doesNotContain(this);
        testObjects.add(this);

        System.out.println("[Test2] testObjects = " + testObjects);
    }

    @Test
    void objTest3() {
        assertThat(testObjects).doesNotContain(this);
        testObjects.add(this);

        System.out.println("[Test3] testObjects = " + testObjects);
    }
}
