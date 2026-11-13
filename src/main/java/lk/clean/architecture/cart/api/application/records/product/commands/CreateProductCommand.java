package lk.clean.architecture.cart.api.application.records.product.commands;

import lk.clean.architecture.cart.api._domain.models.Money;

public record CreateProductCommand(
        String productName,
        String productDescription,
        Money productPrice
) {
    public CreateProductCommand{
        if (productName == null || productName.isBlank()){
            throw new IllegalArgumentException("Product name cannot be empty!");
        }

        if (productDescription == null || productDescription.isBlank()){
            throw new IllegalArgumentException("Product description cannot be empty!");
        }

        if(productPrice == null){
            throw new IllegalArgumentException("Product price cannot be empty!");
        }
    }
}
