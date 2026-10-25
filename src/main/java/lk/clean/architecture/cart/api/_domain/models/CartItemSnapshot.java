package lk.clean.architecture.cart.api._domain.models;

import java.util.UUID;

public record CartItemSnapshot(
        UUID productId,
        String productName,
        int quantity,
        Money unitPrice,
        Money subTotal
) {}
