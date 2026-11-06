package lk.clean.architecture.cart.api.application.records.results;

import lk.clean.architecture.cart.api._domain.models.Money;

import java.util.UUID;

public record CartItemSnapshotResult(
        UUID productId,
        String productName,
        String productDescription,
        int quantity,
        Money unitPrice,
        boolean isAvailable,
        Money subTotal
) {
}
