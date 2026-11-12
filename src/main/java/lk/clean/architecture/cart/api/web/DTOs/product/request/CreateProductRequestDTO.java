package lk.clean.architecture.cart.api.web.DTOs.product.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProductRequestDTO {
    @NotBlank(message = "Product name cannot be empty")
    private String productName;

    @NotBlank(message = "Product name cannot be empty")
    private String productDescription;

    @NotNull(message = "Money cannot be empty")
    private MoneyRequestDTO priceRequestDTO;
}
