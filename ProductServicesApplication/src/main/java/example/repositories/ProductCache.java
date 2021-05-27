package example.repositories;

import example.config.CacheConfig;
import example.rest.dto.ProductDTO;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ProductCache {
    @Cacheable(key = "#id", cacheManager = CacheConfig.Product_DTO_Cache_Manager,
            cacheNames = CacheConfig.Product_DTO_Cache_Name)
    public Optional<ProductDTO> getProduct(Long id) {
        return Optional.empty();
    }

    @CachePut(key = "#productDTO.id", cacheManager = CacheConfig.Product_DTO_Cache_Manager,
            cacheNames = CacheConfig.Product_DTO_Cache_Name)
    public ProductDTO saveProductInCache(ProductDTO productDTO) {
        return productDTO;
    }

    @CacheEvict(key = "#id", cacheManager = CacheConfig.Product_DTO_Cache_Manager,
            cacheNames = CacheConfig.Product_DTO_Cache_Name)
    public void deleteProductFromCache(Long id) {
    }
}
