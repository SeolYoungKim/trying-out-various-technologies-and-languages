package hello.redis;

import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.stream.IntStream;
import org.springframework.stereotype.Component;

@Component
public class DataInjection {
    private final PostRepository postRepository;

    public DataInjection(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    /**
     * postNumber 만큼의 데이터를 저장
     */
    @PostConstruct
    private void init() {
        int postNumber = 5000;
        List<Post> posts = IntStream.rangeClosed(1, postNumber)
                .mapToObj(i -> new Post("title" + i, "content" + i))
                .toList();

        postRepository.saveAll(posts);
    }
}
