package lk.clean.architecture.cart.api.web.DTOs.response;

import lk.clean.architecture.cart.api._domain.models.Money;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdateCartItemQuantityResponseDTO {
    private UUID cartId;
    private UUID productId;
    private int quantity;
    private Money subtotal;

}
