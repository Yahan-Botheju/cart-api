package lk.clean.architecture.cart.api.application.records.cart.commands;

import java.util.UUID;

public record GetCartCommand(UUID cartId) {

    public GetCartCommand {
        if (cartId == null) {
            throw new IllegalArgumentException("cartId cannot be null");
        }
    }
}
