package example.repositories;

import example.config.CacheConfig;
import example.rest.dto.ClientDTO;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ClientCache {
    @Cacheable(key = "#id", cacheManager = CacheConfig.Client_DTO_Cache_Manager,
            cacheNames = CacheConfig.Client_DTO_Cache_Name)
    public Optional<ClientDTO> getClient(Long id) {
        return Optional.empty();
    }

    @CachePut(key = "#clientDTO.id", cacheManager = CacheConfig.Client_DTO_Cache_Manager,
            cacheNames = CacheConfig.Client_DTO_Cache_Name)
    public ClientDTO saveClientInCache(ClientDTO clientDTO) {
        return clientDTO;
    }

    @CacheEvict(key = "#id", cacheManager = CacheConfig.Client_DTO_Cache_Manager,
            cacheNames = CacheConfig.Client_DTO_Cache_Name)
    public void deleteClientFromCache(Long id) {
    }

}
