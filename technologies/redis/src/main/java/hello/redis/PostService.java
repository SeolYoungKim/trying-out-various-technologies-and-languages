package hello.redis;

import java.time.LocalDateTime;
import java.util.List;
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

    @CacheEvict(cacheNames = "Post", key = "'all'", cacheManager = "defaultCacheManager")
    public Post update(Long id) {
        Post post = postRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        post.setTitle("updatedTitle");

        return post;
    }

    @CacheEvict(cacheNames = "Post", key = "'all'", cacheManager = "defaultCacheManager")
    public void evictPostCaches() {
    }

    @Scheduled(fixedRate = 2000)
    public void cache() {
        Cache postCache = cacheManager.getCache("Post");
        log.info("cache: {}", postCache);

        if (postCache != null) {
            log.info("cache: {}", postCache.get("all"));
        }
    }
}
