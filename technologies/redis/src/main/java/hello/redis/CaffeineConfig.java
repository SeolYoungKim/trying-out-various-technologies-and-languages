package hello.redis;

import com.github.benmanes.caffeine.cache.Caffeine;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableCaching
@Configuration
public class CaffeineConfig {
    @Bean
    public CacheManager defaultCacheManager() {
        CaffeineCache caffeineCache = new CaffeineCache(
                "Post",
                Caffeine.newBuilder()
                        .expireAfterWrite(10, TimeUnit.MINUTES)
                        .build()
        );
        SimpleCacheManager simpleCacheManager = new SimpleCacheManager();
        simpleCacheManager.setCaches(List.of(caffeineCache));
        return simpleCacheManager;
    }
}
