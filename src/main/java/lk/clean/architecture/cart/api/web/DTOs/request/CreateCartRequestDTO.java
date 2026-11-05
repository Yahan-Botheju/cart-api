package lk.clean.architecture.cart.api.web.DTOs.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCartRequestDTO {
    @NotBlank(message = "Currency cannot be empty")
    private String currency;
}
