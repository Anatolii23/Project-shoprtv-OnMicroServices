package example.rest;


import example.rest.dto.DetailedDTO;
import example.services.DetailedService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class DetailedController {
    private final DetailedService detailedServices;
    @GetMapping("/detailed")
    @Operation(description = "get detailed of order by id")
    public DetailedDTO getOrderDetailedById(@Parameter(description = "the id of order detailed whose you find")
                                            @RequestParam(name = "id") Long id) {
        return detailedServices.getDetailedById(id);

    }
    @PutMapping("/changedetailed")
    @Operation(description = "change detailed of the order")
    public DetailedDTO editOrder(@Parameter(description = "the id of order detailed whose change")
                                 @RequestParam(name = "id") Long id,
                                 @Valid @RequestBody DetailedDTO detailedDTO) {
        return detailedServices.editDetailed(id, detailedDTO);
    }
}