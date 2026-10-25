package lk.clean.architecture.cart.api._domain.models;

import java.math.BigDecimal;

public class CartItem {
    private final int MAX_ALLOWED_QUANTITY = 100;
    private Product product;
    private int quantity;

    /*__CONSTRUCTOR__ */

    public CartItem(Product product, int quantity) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be empty!");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity cannot be empty!");
        }
        this.product = product;
        this.quantity = quantity;
    }

    /* __GETTERS__ */

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }


    /* __DOMAIN_LOGIC__ */

    //replace quantity
    public void replaceQuantity(int newQuantity) {
        if (newQuantity <= 0) {
            throw new IllegalArgumentException("Invalid quantity!");
        }
        this.quantity = newQuantity;
    }

    //increase cartItem quantity
    public void incrementQuantity(int newQuantity) {
        if (newQuantity <= 0 || newQuantity > MAX_ALLOWED_QUANTITY) {
            throw new IllegalArgumentException("Invalid quantity!");
        }
        int checkQuantity = this.quantity + newQuantity;
        //check quantity exceed maximum qty
        if (checkQuantity > MAX_ALLOWED_QUANTITY) {
            throw new IllegalArgumentException("Invalid quantity!");
        }
        this.quantity = checkQuantity;
    }

    //calculate subtotal
    public Money calculateSubtotal() {
        return new Money(
                product.getProductPrice()
                        .amount()
                        .multiply(new BigDecimal(quantity)),
                product.getProductPrice().currency()
        );
    }
}
