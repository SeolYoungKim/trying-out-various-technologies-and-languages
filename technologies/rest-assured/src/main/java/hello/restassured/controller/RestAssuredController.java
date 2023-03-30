package hello.restassured.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestAssuredController {
    @GetMapping("/hello")
    public HelloDto hello(@RequestParam String name) {
        return new HelloDto(name, "Hello " + name);
    }

    @PostMapping("/hello")
    public HelloDto helloPost(@RequestBody RequestDto requestDto) {
        return new HelloDto(requestDto.name(), requestDto.message());
    }
}
