package hello.test.guide;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

/**
 * ParameterizedTest, RepeatedTest 등에 대한 작성 방법입니다.
 */
public class JUnitGuideTest {
    @DisplayName("@ParameterizedTest 사용 방법")
    @Nested
    class ParameterizedTestExample {
        @DisplayName("String에 대해 테스트합니다.")
        @ParameterizedTest(name = "value={arguments}")
        @ValueSource(strings = {"a", "b", "c"})
            // 이 외에도, 여러 타입의 값들이 올 수 있습니다.
        void test1(String value) {
            System.out.println(value);
        }

        @DisplayName("EnumSource에 대해 테스트합니다.")
        @ParameterizedTest(name = "value={arguments}")
        @EnumSource(OrderStatus.class)
        void test2(OrderStatus orderStatus) {
            System.out.println(orderStatus);
        }

        @DisplayName("CsvSource에 대해 테스트합니다. type=String")
        @ParameterizedTest(name = "value={arguments}")
        @CsvSource(value = {"a,b", "c,d", "e,f"})
        void test3(String first, String second) {
            System.out.println("first = " + first);
            System.out.println("second = " + second);
        }

        @DisplayName("CsvSource에 대해 테스트합니다. type=int")
        @ParameterizedTest(name = "value={arguments}")
        @CsvSource(value = {"1,2", "3,4", "5,6"})
        void test4(int first, int second) {
            System.out.println("first = " + first);
            System.out.println("second = " + second);
        }

        @DisplayName("CsvSource에 대해 테스트합니다. type=enum")
        @ParameterizedTest(name = "value={arguments}")
        @CsvSource(value = {"ORDER,CANCEL", "SHIPPING,DELIVERY_COMPLETED"})
        void test5(OrderStatus first, OrderStatus second) {
            System.out.println("first = " + first);
            System.out.println("second = " + second);
        }

        @DisplayName("MethodSource에 대해 테스트합니다.")
        @ParameterizedTest(name = "value={arguments}")
        @MethodSource("generateValues")
        void test6(String first, OrderStatus second) {
            System.out.println(first + " = " + second);
        }

        static Stream<Arguments> generateValues() {
            return Stream.of(
                    Arguments.of("order", OrderStatus.ORDER),
                    Arguments.of("cancel", OrderStatus.CANCEL),
                    Arguments.of("shipping", OrderStatus.SHIPPING),
                    Arguments.of("delivery_completed", OrderStatus.DELIVERY_COMPLETED)
            );
        }
    }
}
