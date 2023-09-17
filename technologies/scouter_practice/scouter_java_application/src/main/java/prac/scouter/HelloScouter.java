package prac.scouter;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HelloScouter {
    @GetMapping("/")
    public String hello() {
        List<DummyObject> dummyObjects = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            // 객체를 생성해보자. GC도 측정이 되나?
            dummyObjects.add(new DummyObject());
        }

        log.info("dummyObjects.size()={}", dummyObjects.size());
        return "hello, scouter!";
    }

    private record DummyObject() {

    }
}
