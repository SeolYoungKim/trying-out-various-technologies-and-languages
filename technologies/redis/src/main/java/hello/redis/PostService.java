package hello.redis;

import jakarta.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class PostService {
    private final PostRepository postRepository;
    private final CacheManager cacheManager;

    public PostService(PostRepository postRepository, CacheManager cacheManager) {
        this.postRepository = postRepository;
        this.cacheManager = cacheManager;
    }

    @Transactional(readOnly = true)
    @Cacheable(cacheNames = "Post", key = "'all'", cacheManager = "defaultCacheManager")
    public List<Post> getPosts(LocalDateTime localDateTime) {
        return postRepository.findAll();
    }

    @Cacheable(cacheNames = "Post", key = "#id.toString() + #isAdult", cacheManager = "defaultCacheManager")
    public Post getPostById(Long id, boolean isAdult) {
        return postRepository.findById(id).orElseThrow(IllegalArgumentException::new);
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

    @PostConstruct
    void init() {
        IntStream.rangeClosed(1, 50)
                .mapToObj(i -> new Post("title" + i, "content" + i))
                .forEach(postRepository::save);
    }

    @Scheduled(fixedDelay = 5000)
    public void evictAllCachesAtIntervals() {
        Cache cache = cacheManager.getCache("Post");
        if (cache != null) {
            for (int i = 0; i < 50; i++) {
                Post post = cache.get(i + "true", Post.class);
                log.info("id:{}, cachedPost: {}", i, post);
            }
        }
    }
}
