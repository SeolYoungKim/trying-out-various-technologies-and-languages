package hello.redis;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts")
    public List<Post> post() throws InterruptedException {
        return postService.getPosts(LocalDateTime.now());
    }

    @GetMapping("/posts/{id}")
    public Post post(@PathVariable Long id, @RequestParam Boolean isAdult) {
        return postService.getPostById(id, isAdult);
    }

    @PutMapping("/posts/{id}")
    public Post update(@PathVariable Long id) {
        return postService.update(id);
    }

    @DeleteMapping("/posts/cache")
    public ResponseEntity<Void> contentUpdated() {
        postService.evictPostCaches();
        return ResponseEntity.ok().build();
    }
}
