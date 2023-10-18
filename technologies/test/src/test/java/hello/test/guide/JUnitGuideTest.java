package hello.test.guide;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

/**
 * JUnit5의 전반적인 사용 방법에 대한 테스트 코드 입니다.
 * <br/>
 * 더 많은 내용은
 * <a href="https://junit.org/junit5/docs/current/user-guide/#overview">JUnit5 공식문서</a>를 참고하세요.
 */
public class JUnitGuideTest {
    @BeforeAll
    static void beforeAll() {
        /*
         * Test Data를 모두 삭제하는 데 사용하기 용이합니다.
         */
        System.out.println("[BeforeAll] 테스트 클래스의 메서드가 실행되기 전에 1회 실행");
        System.out.println("=====================================================");
    }

    @AfterAll
    static void afterAll() {
        /*
         * Test Data를 모두 삭제하는 데 사용하기 용이합니다.
         * - 하지만, 보통은 실행 전에 삭제하는 것이 선호됩니다. 실행 후 삭제는 다른 테스트가 실행될 때 영향을 줄 수도 있어서 찝찝하다는 게 보통의 이유더라구요.😅
         */
        System.out.println("=====================================================");
        System.out.println("[AfterAll] 테스트 클래스의 모든 테스트가 끝난 후 1회 실행");
    }

    @BeforeEach
    void setUp() {
        /*
         * 공통적인 세팅에 주로 사용됩니다.
         * ex: port 세팅
         */
        System.out.println("[BeforeEach] 테스트 메서드 실행 전 1회 실행");
    }

    @AfterEach
    void tearDown() {
        /*
         * 어디에 쓸 수 있을지는 아직 생각 안해봤습니다. 알게되면 추가를..
         */
        System.out.println("[AfterEach] 테스트 메서드 실행 후 1회 실행");
    }

    @DisplayName("Boolean 검증 테스트")
    @Test
    void test() {
        assertThat(1 == 1).isTrue();
        assertThat(1 != 1).isFalse();
    }

    @DisplayName("값 검증 테스트")
    @Nested
    class ValueTest {
        @DisplayName("숫자 검증")
        @Test
        void test() {
            int x = 1;
            int y = 2;

            assertThat(x + y).isEqualTo(3);  // 동등성 (equalTo)
            assertThat(x + y).isSameAs(3);   // 동일성 (==)
        }

        @DisplayName("문자열 검증")
        @Test
        void test2() {
            String str = "abcabc";

            assertThat(str).isNotBlank();
            assertThat(str).isAlphabetic();
            assertThat(str).contains("ab");
            assertThat(str).containsOnlyOnce("ca");
            assertThat(str).startsWith("ab");
        }

        @DisplayName("객체 검증")
        @Test
        void test3() {
            Car 벤츠1 = new Car("벤츠");
            Car 벤츠2 = new Car("벤츠");

            assertThat(벤츠1).isEqualTo(벤츠2);
            assertThat(벤츠1).isNotSameAs(벤츠2);
        }
    }



    @DisplayName("Collections 검증 테스트")
    @Test
    void test3() {
        List<Integer> numbers = List.of(1, 2, 3);

        assertThat(numbers).hasSize(3);
        assertThat(numbers).containsExactly(1, 2, 3);  // 들어있는 순서도 정확히 지켜져야 합니다.
        assertThat(numbers).containsOnly(1, 3, 2);  // 들어있는 순서는 상관 없습니다.
    }

    @DisplayName("Map 검증 테스트")
    @Test
    void test4() {
        Map<String, String> map = Map.of("key1", "val1",
                "key2", "val2",
                "key3", "val3"
        );

        assertThat(map).hasSize(3);
        assertThat(map).containsOnly(entry("key1", "val1"), entry("key2", "val2"), entry("key3", "val3"));  // HashMap은 순서를 보장하지 않으므로, containsExactly에 대한 테스트는 작성하지 않았습니다.
        assertThat(map).containsKey("key1");
        assertThat(map).containsKeys("key1", "key2");
        assertThat(map).containsOnlyKeys("key1", "key2", "key3");
    }

    @DisplayName("예외 검증 테스트")
    @Test
    void test5() {
        assertThatThrownBy(this::throwRuntimeException)
                .isInstanceOf(RuntimeException.class)
                .hasMessage("런타임 예외 발생!");
    }

    void throwRuntimeException() {
        throw new RuntimeException("런타임 예외 발생!");
    }
}
