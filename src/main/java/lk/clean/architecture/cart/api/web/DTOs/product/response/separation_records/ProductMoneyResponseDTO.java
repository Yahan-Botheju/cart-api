package lk.clean.architecture.cart.api.web.DTOs.product.response.separation_records;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductMoneyResponseDTO {
    private BigDecimal amount;
    private String currency;
}
