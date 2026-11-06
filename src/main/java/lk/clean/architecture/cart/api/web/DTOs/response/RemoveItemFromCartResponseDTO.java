package lk.clean.architecture.cart.api.web.DTOs.response;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class RemoveItemFromCartResponseDTO {
    private UUID cartId;
    private UUID removedProductId;
    private boolean isCartEmpty;
}
