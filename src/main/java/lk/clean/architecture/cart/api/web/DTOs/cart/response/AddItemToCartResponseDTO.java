package lk.clean.architecture.cart.api.web.DTOs.cart.response;

import lk.clean.architecture.cart.api.web.DTOs.cart.response.separation_records.MoneyResponseDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class AddItemToCartResponseDTO {
    private UUID cartId;
    private UUID productId;
    private int quantity;
    private MoneyResponseDTO subtotal;
}
