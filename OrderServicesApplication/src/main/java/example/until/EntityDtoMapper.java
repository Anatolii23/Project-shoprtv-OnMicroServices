package example.until;

import example.entities.DetailedEntity;
import example.entities.OrderEntity;
import example.rest.dto.DetailedDTO;
import example.rest.dto.OrderDTO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class EntityDtoMapper {
    public static DetailedDTO mappedToDetailedDTO(DetailedEntity detailedEntity) {
        DetailedDTO detailedDTO = DetailedDTO.builder().build();
        BeanUtils.copyProperties(detailedEntity, detailedDTO);
        if (detailedEntity.getOrderEntity() != null) {
            detailedDTO.setOrderDTO(EntityDtoMapper.mappedToOrderDTO(detailedEntity.getOrderEntity()));
        }
        return detailedDTO;
    }

    public static DetailedEntity mappedToDetailedEntity(DetailedDTO detailedDTO) {
        DetailedEntity detailedEntity = DetailedEntity.builder().build();
        BeanUtils.copyProperties(detailedDTO, detailedEntity);
        if (detailedDTO.getOrderDTO() != null) {
            detailedEntity.setOrderEntity(EntityDtoMapper.mappedToOrderEntity(detailedDTO.getOrderDTO()));
        }
        return detailedEntity;
    }

    public static OrderDTO mappedToOrderDTO(OrderEntity orderEntity) {
        OrderDTO orderDTO = OrderDTO.builder().build();
        BeanUtils.copyProperties(orderEntity, orderDTO);
        return orderDTO;
    }
    public static List<OrderDTO> mappedToOrderDTOList(List<OrderEntity> orderEntity) {
        List<OrderDTO> orderDTO = new ArrayList<>();
        BeanUtils.copyProperties(orderEntity, orderDTO);
        return orderDTO;
    }

    public static OrderEntity mappedToOrderEntity(OrderDTO orderDTO) {
        OrderEntity orderEntity = OrderEntity.builder().build();
        BeanUtils.copyProperties(orderDTO, orderEntity);
        return orderEntity;
    }
}
