package lk.clean.architecture.cart.api.application.records.cart.commands;

import java.util.UUID;

public record ClearCartCommand(UUID cartId) {
    public ClearCartCommand {
        if (cartId == null) {
            throw new IllegalArgumentException("Cart ID cannot be null");
        }
    }
}
