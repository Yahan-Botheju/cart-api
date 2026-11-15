package lk.clean.architecture.cart.api.web.DTOs.product.response;

import lk.clean.architecture.cart.api._domain.models.Money;
import lk.clean.architecture.cart.api.web.DTOs.product.response.separation_records.ProductMoneyResponseDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdateProductResponseDTO {
    private UUID productId;
    private String productName;
    private String productDescription;
    private ProductMoneyResponseDTO productPrice;
    private boolean isProductAvailable;
}
