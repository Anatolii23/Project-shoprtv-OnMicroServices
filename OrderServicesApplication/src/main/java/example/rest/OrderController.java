package example.rest;

import example.rest.dto.DetailedDTO;
import example.rest.dto.OrderDTO;
import example.services.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderServices;

    @GetMapping("/order")
    @Operation(description = "get order by client id")
    public List<OrderDTO> getOrderByClientId(@Parameter(description = "the id of client whose order you find")
                                             @RequestParam(name = "id") Long id) {
        return orderServices.getOrderByClientId(id);

    }

    @PostMapping("/addorder")
    @Operation(description = "add order")
    public ResponseEntity<DetailedDTO> addOrder(@Valid @RequestBody DetailedDTO detailedDTO,
                                                @RequestParam(name = "clientId") Long clientId,
                                                @RequestParam(name = "productId") Long productId) {
        orderServices.addOrder(detailedDTO, clientId, productId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/orderremove")
    @Operation(description = "delete order")
    public ResponseEntity<OrderDTO> removeOrder(@Parameter(description = " the id of order whose remove")
                                                @RequestParam(name = "id") Long id) {
        orderServices.deleteOrderById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/order/{id}")
    @Operation(description = "change order")
    public OrderDTO editOrder(@Parameter(description = "the id of order whose change")
                              @PathVariable(name = "id") Long id,
                              @Valid @RequestBody OrderDTO orderDTO) {
        return orderServices.editOrder(id, orderDTO);
    }

    @GetMapping("/orders")
    @Operation(description = "find orders by parameters ")
    public List<OrderDTO> findOrderByParam(@Parameter(description = " the status of order whose find")
                                           @RequestParam(name = "name", required = false) String status,
                                           @Parameter(description = " the id of order whose find")
                                           @RequestParam(name = "id", required = false) Long id,
                                           @Parameter(description = " the order of product whose find")
                                           @RequestParam(name = "minPrice", required = false) Integer minPrice,
                                           @Parameter(description = " the order of product whose find")
                                           @RequestParam(name = "maxPrice", required = false) Integer maxPrice) {
        return orderServices.getOrder(maxPrice, minPrice, id, status);
    }
}
