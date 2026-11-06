package lk.clean.architecture.cart.api.web.DTOs.response;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ClearCartResponseDTO {
    private UUID cartId;
    private boolean isCartEmpty;
}
