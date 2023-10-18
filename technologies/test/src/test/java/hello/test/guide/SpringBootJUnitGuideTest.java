package hello.test.guide;

import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.internal.SessionImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.transaction.annotation.Transactional;

/**
 * SpringBootTest.WebEnvironment.MOCK: 기본 설정. 모의 서블릿 환경을 세팅.
 * SpringBootTest.WebEnvironment.DEFINED_PORT: 지정된 Port를 사용하는 서블릿 컨테이너를 띄움.
 * SpringBootTest.WebEnvironment.RANDOM_PORT: 랜덤 Port를 사용하는 서블릿 컨테이너를 띄움. @LocalServerPort와 함께 사용됨 -> port 설정이 필요한 경우에 사용
 * - 예제는 RANDOM_PORT로 수행
 * - @SpringBootTest를 사용해야 @Autowired 등의 작업이 수행됩니다.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringBootJUnitGuideTest {
    @LocalServerPort
    private int port;

    @Autowired  // 테스트 코드에 한해서는 필드 주입을 사용해도 괜찮습니다. 프로덕트 코드에서는 지양합시다. 필드가 변경될 가능성이 있기 때문입니다. 스프링 빈은 불변성을 확보해야 안전합니다.
    private EntityManager em;

    @BeforeEach
    void setUp() {
        /*
         * 보통 여기서 port 설정이 필요한 작업을 수행합니다.
         * RestAssured와 같은 테스트들은 port 설정을 해줘야하는 경우가 있는데, @LocalServerPort로 주입받은 값을 사용하면 됩니다.
         */
        System.out.println("port = " + port);
    }

    @Transactional  // 해당 애노테이션을 테스트 메소드 or 클래스에 붙이면 테스트수행 후 데이터를 자동으로 롤백 해줍니다.
    @Test
    void test() {
        // 필드주입 받은 객체는 테스트코드에서 바로 사용할 수 있습니다.
        Session unwrappedEm = em.unwrap(SessionImpl.class);
        System.out.println(unwrappedEm);
    }
}
