package lk.clean.architecture.cart.api.web.DTOs.product.response;

import lk.clean.architecture.cart.api._domain.models.Money;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdateProductResponseDTO {
    private UUID productId;
    private String productName;
    private String productDescription;
    private Money productPrice;
    private boolean isProductAvailable;
}
