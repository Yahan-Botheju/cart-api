package lk.clean.architecture.cart.api.web.DTOs.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class AddItemToCartRequestDTO {
    @NotNull(message = "Product ID cannot be empty")
    private UUID productId;
    @Min(value = 1, message = "Add at least one quantity")
    @NotNull(message = "Quantity cannot be empty")
    private Integer quantity;
}
