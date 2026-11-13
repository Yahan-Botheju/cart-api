package lk.clean.architecture.cart.api.application.records.product.results;

import lk.clean.architecture.cart.api._domain.models.Money;

import java.util.UUID;

public record CreateProductResult(
        UUID productId,
        String productName,
        String productDescription,
        Money productPrice,
        boolean isProductAvailable
) {
}
