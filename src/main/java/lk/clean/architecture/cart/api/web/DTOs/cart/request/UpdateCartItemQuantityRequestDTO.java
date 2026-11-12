package lk.clean.architecture.cart.api.web.DTOs.cart.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCartItemQuantityRequestDTO {
    @Min(value = 1, message = "Add at least one item as quantity")
    @NotNull(message = "Quantity cannot be empty")
    private Integer quantity;
}
