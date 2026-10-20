package lk.clean.architecture.cart.api._domain.models;

public class CartItem {
    private Product product;
    private int quantity;
//    private Money unitPrice;
//    private Money totalPrice;

    /*__CONSTRUCTOR__ */

    public CartItem(Product product, int quantity) {
        if(product == null){
            throw new IllegalArgumentException("Product cannot be empty!");
        }
        if(quantity <= 0){
            throw new IllegalArgumentException("Quantity cannot be empty!");
        }
        this.product = product;
        this.quantity = quantity;
    }

    /* __GETTERS__ */

    public Product getProduct() { return product; }
    public int getQuantity() { return quantity; }


    /* __DOMAIN_LOGIC__ */

    //replace quantity
    public void replaceQuantity(int newQuantity) {
        if(newQuantity <= 0 ){
            throw new IllegalArgumentException("Invalid quantity!");
        }
        this.quantity = newQuantity;
    }

}
