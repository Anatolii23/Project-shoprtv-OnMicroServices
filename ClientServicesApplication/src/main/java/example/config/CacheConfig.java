package example.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfig {
    public static final String Client_DTO_Cache_Manager = "clientResponseCacheManager";
    public static final String Client_DTO_Cache_Name = "clientResponseCache";
    public static final String Address_DTO_Cache_Manager = "AddressEntityCacheManager";
    public static final String Address_DTO_Cache_Name = "AddressEntityResponseCache";


    @Bean(Client_DTO_Cache_Manager)
    @Primary
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        Caffeine<Object, Object> caffeine = Caffeine.newBuilder().expireAfterWrite(120, TimeUnit.SECONDS);
        cacheManager.setCaffeine(caffeine);
        cacheManager.setCacheNames(Collections.singleton(Client_DTO_Cache_Name));
        return cacheManager;
    }
    @Bean(Address_DTO_Cache_Manager)
    public CacheManager cacheManager2() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        Caffeine<Object, Object> caffeine = Caffeine.newBuilder().expireAfterWrite(120, TimeUnit.SECONDS);
        cacheManager.setCaffeine(caffeine);
        cacheManager.setCacheNames(Collections.singleton(Address_DTO_Cache_Name));
        return cacheManager;
    }
}
