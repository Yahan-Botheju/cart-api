package lk.clean.architecture.cart.api.web.DTOs.response.separation_records;

import lk.clean.architecture.cart.api.application.records.results.separation_records.MoneyResult;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CartItemSnapshotDTO {
    private UUID productId;
    private String productName;
    private String productDescription;
    private int quantity;
    private MoneyResult unitPrice;
    private boolean isAvailable;
    private MoneyResponseDTO subTotal;
}
