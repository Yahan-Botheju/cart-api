package lk.clean.architecture.cart.api._domain.models;

public class CartItem {
    private Product product;
    private int quantity;
    private Money unitPrice;
    private Money totalPrice;

    /*__CONSTRUCTOR__ */

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    /* __GETTERS__ */

    public Product getProduct() { return product; }
    public int getQuantity() { return quantity; }

}
