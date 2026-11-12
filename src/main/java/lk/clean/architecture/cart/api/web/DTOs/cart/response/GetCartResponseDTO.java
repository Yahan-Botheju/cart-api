package lk.clean.architecture.cart.api.web.DTOs.cart.response;

import lk.clean.architecture.cart.api.web.DTOs.cart.response.separation_records.CartItemSnapshotDTO;
import lk.clean.architecture.cart.api.web.DTOs.cart.response.separation_records.MoneyResponseDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class GetCartResponseDTO {
    private UUID cartId;
    private String currency;
    private List<CartItemSnapshotDTO> cartItemSnapshotDTOS;
    private MoneyResponseDTO total;
    private boolean isCartEmpty;
}
