package example.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TypeOfProductDTO {
    @Schema(description = "id of existing type of product", example = "1")
    private long id;
    @Schema(description = "type of product", example = "smartphone")
    @NotNull(message = "not be null")
    @NotBlank(message = "not be blank")
    private String type;
}