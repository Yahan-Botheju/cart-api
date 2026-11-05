package lk.clean.architecture.cart.api.web.DTOs.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Data
@AllArgsConstructor
public class CreateCartResponseDTO {
    private UUID cartId;
    private String currency;
    private boolean isCartEmpty;
}
