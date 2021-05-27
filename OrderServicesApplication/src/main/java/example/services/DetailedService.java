package example.services;

import example.entities.DetailedEntity;
import example.entities.OrderEntity;
import example.errors.ServiceNotFoundException;
import example.repositories.DetailedRepository;
import example.repositories.OrderRepository;
import example.rest.dto.DetailedDTO;
import example.until.EntityDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DetailedService {
    private final DetailedRepository detailedRepository;
    private final OrderRepository orderRepository;
    public DetailedDTO editDetailed(Long id, DetailedDTO detailedDTO) {
        Optional<DetailedEntity> detailedEntity = detailedRepository.findById(id);
        if (detailedEntity.isPresent()) {
            DetailedEntity detailed = detailedEntity.get();
            detailed.setQuantity(detailedDTO.getQuantity());
            detailed.setOrderEntity(detailed.getOrderEntity());
            List<DetailedEntity> detailedEntitySet = new ArrayList<>();
            detailedEntitySet.add(detailed);
            OrderEntity orderEntity = detailed.getOrderEntity();
            orderEntity.setDetailedEntityList(detailedEntitySet);
            orderRepository.save(orderEntity);
            DetailedEntity save = detailedRepository.save(detailed);
            return EntityDtoMapper.mappedToDetailedDTO(save);
        } else throw new ServiceNotFoundException(id);
    }
    public DetailedDTO getDetailedById(Long id) {
        return detailedRepository.findById(id)
                .map(EntityDtoMapper::mappedToDetailedDTO)
                .orElseThrow(()-> new ServiceNotFoundException(id));
    }
}
