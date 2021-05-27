package example.services;

import example.entities.ProductEntity;
import example.entities.TypeOfProductEntity;
import example.errors.ServiceNotFoundException;
import example.repositories.ProductCache;
import example.repositories.ProductRepository;
import example.repositories.TypeOfProductRepository;
import example.rest.dto.ProductDTO;
import example.until.EntityDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductCache productCache;
    private final TypeOfProductRepository typeOfProductRepository;

    public ProductDTO addProduct(ProductDTO productDTO) {
        ProductEntity productEntity = EntityDtoMapper.mappedToProductEntity(productDTO);
        TypeOfProductEntity typeOfProductEntity = EntityDtoMapper.mappedToTypeOfProductEntity(productDTO.getTypeOfProductDTO());
        Optional<TypeOfProductEntity> byId = typeOfProductRepository.findById(productDTO.getTypeOfProductDTO().getId());
        if (byId.isPresent()) {
            typeOfProductEntity = byId.get();
        }
        productEntity.setTypeOfProductEntity(typeOfProductEntity);
        Set<ProductEntity> productEntityList = new HashSet<>();
        productEntityList.add(productEntity);
        typeOfProductEntity.setProductEntityList(productEntityList);
        typeOfProductRepository.save(typeOfProductEntity);
        ProductEntity save = productRepository.save(productEntity);
        ProductDTO productDTO1 = EntityDtoMapper.mappedToProductDTO(save);
        productDTO1.setTypeOfProductDTO(productDTO.getTypeOfProductDTO());
        productCache.saveProductInCache(productDTO1);
        return EntityDtoMapper.mappedToProductDTO(save);
    }

    public void deleteProductById(Long id) {
        productRepository.findById(id).orElseThrow(() -> new ServiceNotFoundException(id));

    }

    public ProductDTO editProduct(Long id, ProductDTO productDTO) {
        Optional<ProductEntity> productEntity = productRepository.findById(id);
        if (productEntity.isPresent()) {
            ProductEntity product = productEntity.get();
            product.setName(productDTO.getName());
            product.setPrice(productDTO.getPrice());
            product.setTypeOfProductEntity(EntityDtoMapper.mappedToTypeOfProductEntity(productDTO.getTypeOfProductDTO()));
            typeOfProductRepository.save(product.getTypeOfProductEntity());
            ProductEntity save = productRepository.save(product);
            productCache.saveProductInCache(EntityDtoMapper.mappedToProductDTO(save));
            return EntityDtoMapper.mappedToProductDTO(save);
        } else throw new ServiceNotFoundException(id);
    }

    public ProductDTO findProductById(Long id) {
        return productCache.getProduct(id).orElseGet(()->productRepository.findById(id)
                .map(EntityDtoMapper::mappedToProductDTO).orElseThrow(()-> new ServiceNotFoundException(id)));
    }

    public List<ProductDTO> getProduct(Integer maxPrice, Integer minPrice, Long id, String name) {
        return productRepository.findAll().stream()
                .filter(productEntity -> maxPrice == null || productEntity.getPrice() <= maxPrice)
                .filter(productEntity -> minPrice == null || productEntity.getPrice() >= minPrice)
                .filter(productEntity -> id == null || productEntity.getId() == id)
                .filter(productEntity -> name == null || productEntity.getName().equals(name))
                .map(EntityDtoMapper::mappedToProductDTO)
                .collect(Collectors.toList());
    }

}
