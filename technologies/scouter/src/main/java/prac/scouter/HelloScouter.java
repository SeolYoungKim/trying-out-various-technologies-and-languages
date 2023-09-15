package prac.scouter;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloScouter {
    @GetMapping("/")
    public String hello() {
        return "hello, scouter!";
    }
}
