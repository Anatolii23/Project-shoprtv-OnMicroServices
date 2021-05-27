package example.rest.dto;

import example.repositories.StatusRepository;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    @Schema(description = "id of existing order", example = "1")
    private long id;
    @Schema(description = "actual status in the order", example = "Accept", required = true)
    @NotNull(message = "not be null")
    @Enumerated(EnumType.STRING)
    private StatusRepository status;
    @Schema(description = "price of all order")
    @NotNull(message = "not be null")
    @Min(0)
    private int price;
    @Schema(description = "data of order")
    @DateTimeFormat
    private Timestamp data;
}
