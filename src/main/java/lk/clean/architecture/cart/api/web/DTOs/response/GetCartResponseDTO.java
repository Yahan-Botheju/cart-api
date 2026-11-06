package lk.clean.architecture.cart.api.web.DTOs.response;

import lk.clean.architecture.cart.api._domain.models.CartItemSnapshot;
import lk.clean.architecture.cart.api._domain.models.Money;
import lk.clean.architecture.cart.api.application.records.results.CartItemSnapshotResult;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class GetCartResponseDTO {
    private UUID cartId;
    private String currency;
    private List<CartItemSnapshotResult> cartItemSnapshotResults;
    private Money total;
    private boolean isCartEmpty;
}
