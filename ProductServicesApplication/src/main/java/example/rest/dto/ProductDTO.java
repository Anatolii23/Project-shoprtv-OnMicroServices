package example.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    @Schema(description = "id of existing product", example = "1")
    private long id;
    @Schema(description = "product name", example = "Apple x10", required = true)
    @NotNull(message = "not be null")
    @NotBlank(message = "not be blank")
    private String name;
    @Schema(description = "product price", example = "650", required = true)
    @NotNull(message = "not be null")
    @Min(0)
    private int price;
    @Schema(description = "type of product", required = true)
    private TypeOfProductDTO typeOfProductDTO;
}

