package hello.redis;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Transactional(readOnly = true)
    @Cacheable(cacheNames = "Post", key = "'all'", cacheManager = "defaultCacheManager")
    public List<Post> getPosts(LocalDateTime localDateTime) {
        return postRepository.findAll();
    }

    @CacheEvict(cacheNames = "Post", key = "'all'", cacheManager = "defaultCacheManager")
    public Post update(Long id) {
        Post post = postRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        post.setTitle("updatedTitle");

        return post;
    }

    @CacheEvict(cacheNames = "Post", key = "'all'", cacheManager = "defaultCacheManager")
    public void evictPostCaches() {
    }
}
