package hello.restassured;

import static org.assertj.core.api.Assertions.*;
import static org.hamcrest.Matchers.equalTo;

import hello.restassured.controller.HelloDto;
import hello.restassured.controller.RequestDto;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)  // https://kth990303.tistory.com/371 이걸 사용하는 이유 참고
public class RestAssuredTest {
    @LocalServerPort
    private int port;

    @Test
    void test() {
        final ExtractableResponse<Response> extract = RestAssured
                .given().port(port).log().all()
                .body(new RequestDto("이름", "메세지"))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().post("/hello")
                .then().log().all()
                .extract();

        assertThat(extract.statusCode()).isEqualTo(200);
        assertThat(extract.as(HelloDto.class)).isEqualTo(new HelloDto("이름", "메세지"));
    }

    @Test
    void test2() {
        RestAssured.given().port(port).log().all()
                .when().get("/hello?name=이름")
                .then().log().all()
                .body("name", equalTo("이름"))
                .body("message", equalTo("Hello 이름"));
    }

    @Test
    void test3() {
        final HelloDto result = RestAssured.given().port(port).log().all()
                .when().get("/hello?name=이름")
                .then().log().all()
                .extract().as(HelloDto.class);

        assertThat(result).isEqualTo(new HelloDto("이름", "Hello 이름"));
    }
}
