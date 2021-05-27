package example.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DetailedDTO {
    @Schema(description = "id of existing detailed", example = "1")
    private long id;
    @Schema(description = "quantity of ordered products in the order", example = "3", required = true)
    @NotNull(message = "not be null")
    @Min(0)
    private int quantity;
    @Schema(description = "detailed in this order ", required = true)
    private OrderDTO orderDTO;

}
