package lk.clean.architecture.cart.api.web.DTOs.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class MoneyResponseDTO {
    private BigDecimal amount;
    private String currency;
}
