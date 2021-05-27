package example.repositories;

import example.config.CacheConfig;
import example.entities.AddressEntity;
import example.rest.dto.AddressDTO;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AddressCache {
    @Cacheable(key = "#id", cacheManager = CacheConfig.Address_DTO_Cache_Manager,
            cacheNames = CacheConfig.Address_DTO_Cache_Name)
    public Optional<AddressEntity> getAddress(Long id) {
        return Optional.empty();
    }

    @CachePut(key = "#addressDTO.id", cacheManager = CacheConfig.Address_DTO_Cache_Manager,
            cacheNames = CacheConfig.Address_DTO_Cache_Name)
    public AddressDTO saveAddressInCache(AddressDTO addressDTO) {
        return addressDTO;
    }

    @CacheEvict(key = "#id", cacheManager = CacheConfig.Address_DTO_Cache_Manager,
            cacheNames = CacheConfig.Address_DTO_Cache_Name)
    public void deleteAddressFromCache(Long id) {
    }
}
