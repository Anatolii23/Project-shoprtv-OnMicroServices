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
public class AddressDTO {
    @Schema(description = "id of existing client", example = "1")
    private long id;
    @Schema(description = "client city", example = "London", required = true)
    @NotNull(message = "not be null")
    @NotBlank(message = "not be blank")
    private String city;
    @Schema(description = "client country", example = "Germany", required = true)
    @NotNull(message = "not be null")
    @NotBlank(message = "not be blank")
    private String country;
}
