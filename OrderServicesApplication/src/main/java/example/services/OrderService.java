package example.services;

import example.entities.DetailedEntity;
import example.entities.OrderEntity;
import example.errors.ServiceNotFoundException;
import example.repositories.DetailedRepository;
import example.repositories.OrderRepository;
import example.repositories.StatusRepository;
import example.rest.dto.ClientDTO;
import example.rest.dto.DetailedDTO;
import example.rest.dto.OrderDTO;
import example.rest.dto.ProductDTO;
import example.until.EntityDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final String Client_Service_URL = "http://192.168.0.11:8084/client?id=";
    private final String Product_Service_URL = "http://192.168.0.11:8083/product?id=";
    private final DetailedRepository detailedRepository;
    private final RestTemplate restTemplate = new RestTemplate();

    public List<OrderDTO> getOrderByClientId(Long id) {
        ClientDTO clientDTO = restTemplate.getForObject(Client_Service_URL + id, ClientDTO.class);
        if (clientDTO == null) {
            return new ArrayList<>();
        }
        List<OrderEntity> orderEntities = orderRepository.findAllByClientId(id);
        return EntityDtoMapper.mappedToOrderDTOList(orderEntities);
    }

    public void addOrder(DetailedDTO detailedDTO, Long clientId, Long productId) {
        ClientDTO clientDTO = restTemplate.getForObject(Client_Service_URL + clientId, ClientDTO.class);
        ProductDTO productDTO = restTemplate.getForObject(Product_Service_URL + productId, ProductDTO.class);
        OrderEntity orderEntity = EntityDtoMapper.mappedToOrderEntity(detailedDTO.getOrderDTO());
        DetailedEntity detailedEntity = EntityDtoMapper.mappedToDetailedEntity(detailedDTO);
        detailedEntity.setOrderEntity(orderEntity);
        assert productDTO != null;
        detailedEntity.setProductId(productDTO.getId());
        orderEntity.setData(Timestamp.valueOf(LocalDateTime.now()));
        orderEntity.setStatus(detailedDTO.getOrderDTO().getStatus());
        assert clientDTO != null;
        orderEntity.setClientId(clientDTO.getId());
        orderEntity.setPrice(calculatePrice(detailedDTO, productDTO));
        List<DetailedEntity> detailedEntityList = new ArrayList<>();
        detailedEntityList.add(detailedEntity);
        orderEntity.setDetailedEntityList(detailedEntityList);
        orderRepository.save(orderEntity);
        detailedRepository.save(detailedEntity);
    }

    public void deleteOrderById(Long id) {
        orderRepository.findById(id).orElseThrow(() -> new ServiceNotFoundException(id));

    }

    public OrderDTO editOrder(Long id, OrderDTO orderDTO) {
        Optional<OrderEntity> orderEntity = orderRepository.findById(id);
        if (orderEntity.isPresent()) {
            OrderEntity order = orderEntity.get();
            order.setStatus(orderDTO.getStatus());
            OrderEntity save = orderRepository.save(order);
            return EntityDtoMapper.mappedToOrderDTO(save);
        } else throw new ServiceNotFoundException(id);

    }

    public int calculatePrice(DetailedDTO detailedDTO, ProductDTO productDTO) {
        return detailedDTO.getQuantity() * productDTO.getPrice();
    }

    public List<OrderDTO> getOrder(Integer maxPrice, Integer minPrice, Long id, String status) {
        return orderRepository.findAll().stream()
                .filter(orderEntity -> maxPrice == null || orderEntity.getPrice() <= maxPrice)
                .filter(orderEntity -> minPrice == null || orderEntity.getPrice() >= minPrice)
                .filter(orderEntity -> id == null || orderEntity.getId() == id)
                .filter(orderEntity -> status == null || orderEntity.getStatus().equals(StatusRepository.valueOf(status)))
                .map(EntityDtoMapper::mappedToOrderDTO)
                .collect(Collectors.toList());
    }

}
