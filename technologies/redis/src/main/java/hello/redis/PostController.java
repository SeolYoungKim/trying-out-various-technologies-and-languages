package hello.redis;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts")
    public List<Post> post() {
        return postService.getPosts();
    }

    @PutMapping("/posts/{id}")
    public Post update(@PathVariable Long id) {
        return postService.update(id);
    }
}
