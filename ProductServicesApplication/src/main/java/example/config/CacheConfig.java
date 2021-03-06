package example.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfig {
    public static final String Product_DTO_Cache_Manager = "productResponseCacheManager";
    public static final String Product_DTO_Cache_Name = "productResponseCache";

    @Bean(Product_DTO_Cache_Manager)
    public CacheManager cacheManager1() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        Caffeine<Object, Object> caffeine = Caffeine.newBuilder().expireAfterWrite(120, TimeUnit.SECONDS);
        cacheManager.setCaffeine(caffeine);
        cacheManager.setCacheNames(Collections.singleton(Product_DTO_Cache_Name));
        return cacheManager;
    }
}

