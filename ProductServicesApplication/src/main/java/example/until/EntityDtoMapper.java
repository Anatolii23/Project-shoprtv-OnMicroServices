package example.until;

import example.entities.ProductEntity;
import example.entities.TypeOfProductEntity;
import example.rest.dto.ProductDTO;
import example.rest.dto.TypeOfProductDTO;
import org.springframework.beans.BeanUtils;

public class EntityDtoMapper {
    public static ProductDTO mappedToProductDTO(ProductEntity productEntity) {
        ProductDTO productDTO = ProductDTO.builder().build();
        BeanUtils.copyProperties(productEntity, productDTO);
        if (productEntity.getTypeOfProductEntity() != null) {
            productDTO.setTypeOfProductDTO(EntityDtoMapper.mappedToTypeOfProductDTO(productEntity.getTypeOfProductEntity()));
        }
        return productDTO;
    }

    public static ProductEntity mappedToProductEntity(ProductDTO productDTO) {
        ProductEntity productEntity = ProductEntity.builder().build();
        BeanUtils.copyProperties(productDTO, productEntity);
        if (productDTO.getTypeOfProductDTO() != null) {
            productEntity.setTypeOfProductEntity(EntityDtoMapper.mappedToTypeOfProductEntity(productDTO.getTypeOfProductDTO()));
        }
        return productEntity;
    }

    public static TypeOfProductDTO mappedToTypeOfProductDTO(TypeOfProductEntity typeOfProductEntity) {
        TypeOfProductDTO typeOfProductDTO = TypeOfProductDTO.builder().build();
        BeanUtils.copyProperties(typeOfProductEntity, typeOfProductDTO);
        return typeOfProductDTO;
    }

    public static TypeOfProductEntity mappedToTypeOfProductEntity(TypeOfProductDTO typeOfProductDTO) {
        TypeOfProductEntity typeOfProductEntity = TypeOfProductEntity.builder().build();
        BeanUtils.copyProperties(typeOfProductDTO, typeOfProductEntity);
        return typeOfProductEntity;
    }
}
