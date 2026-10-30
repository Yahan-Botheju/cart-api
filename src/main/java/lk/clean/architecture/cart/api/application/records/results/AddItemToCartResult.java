package lk.clean.architecture.cart.api.application.records.results;

import lk.clean.architecture.cart.api._domain.models.Money;

import java.util.UUID;

public record AddItemToCartResult(
        UUID cartId,
        UUID productId,
        int quantity,
        Money subtotal
) {

    public AddItemToCartResult{

        if(cartId == null){
            throw new IllegalArgumentException("cartId cannot be null");
        }

        if(productId == null){
            throw new IllegalArgumentException("productId cannot be null");
        }
        if(quantity <= 0){
            throw new IllegalArgumentException("quantity cannot be negative");
        }
        if(subtotal == null){
            throw new IllegalArgumentException("subtotal cannot be null");
        }
    }
}
