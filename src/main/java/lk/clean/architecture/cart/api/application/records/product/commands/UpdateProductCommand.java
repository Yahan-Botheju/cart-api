package lk.clean.architecture.cart.api.application.records.product.commands;

import lk.clean.architecture.cart.api._domain.models.Money;

import java.math.BigDecimal;
import java.util.UUID;

public record UpdateProductCommand(
        UUID productId,
        String productName,
        String productDescription,
        Money productPrice
) {
    public UpdateProductCommand {

        if (productId == null) {
            throw new IllegalArgumentException("productId cannot be null");
        }
        if (productName == null || productName.trim().isBlank()) {
            throw new IllegalArgumentException("Product name cannot be empty!");
        }
        if (productDescription == null || productDescription.trim().isBlank()) {
            throw new IllegalArgumentException("Product description cannot be empty!");
        }
        if (productPrice == null || BigDecimal.ZERO.compareTo(productPrice.amount()) <= 0) {
            throw new IllegalArgumentException("Product price cannot be negative!");
        }
    }
}
