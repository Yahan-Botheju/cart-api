package lk.clean.architecture.cart.api.application.records.product.commands;

import lk.clean.architecture.cart.api._domain.models.Money;

import java.math.BigDecimal;

public record UpdateProductCommand(
        String productName,
        String productDescription,
        Money productPrice
) {
    public UpdateProductCommand {

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
