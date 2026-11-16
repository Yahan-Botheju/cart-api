package lk.clean.architecture.cart.api.application.records.product.commands;

import java.util.UUID;

public record FindProductCommand(
        UUID productId
) {
    public FindProductCommand{
        if (productId == null) {
            throw new NullPointerException("productId cannot be empty");
        }
    }
}
