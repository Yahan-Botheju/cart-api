package lk.clean.architecture.cart.api._domain.models;

import java.math.BigDecimal;
import java.util.*;

public class Cart {
    private final UUID cartId;
    private final String currency;
    private List<CartItem> cartItems;

    /* __CONSTRUCTOR__ */

    public Cart(String currency) {
        this.cartId = UUID.randomUUID();
        if(currency == null || currency.isBlank()) {
            throw new IllegalArgumentException("Current currency cannot empty");
        }
        this.currency = currency.trim();
        this.cartItems = new ArrayList<CartItem>();
    }

    /* __GETTERS__ */

    public UUID getCartId() { return cartId; }
    public String getCurrency() { return currency; }
    public List<CartItemSnapshot> getCartItems() {
        return cartItems.stream()
                .map(items -> new CartItemSnapshot(
                        items.getProduct().getProductId(),
                        items.getProduct().getProductName(),
                        items.getQuantity(),
                        items.getProduct().getProductPrice(),
                        items.calculateSubtotal()
                )).toList();
    }



    /* ------------------------------DOMAIN_LOGICS---------------------------------------*/


    /* __HELPER_METHODS__ */

    //item checking
    private CartItem checkItem(UUID productId){
        return cartItems.stream()
                .filter(item -> item.getProduct().getProductId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("There is no item to be removed!"));
    }

    //check currency
    private boolean currencyCheck(CartItem cartItem){
         return cartItem.getProduct().getProductPrice().currency().equals(this.currency);
    }

    //add item
    private void addNewItem(CartItem newItem) {
        if(newItem == null){
            throw new IllegalArgumentException("Items cannot be empty!");
        }
        //check currency
        if(!currencyCheck(newItem)){
            throw new IllegalStateException("Invalid currency!");
        }

        this.cartItems.add(newItem);
    }


    /* __PUBLIC_METHODS__ */


    //add items without duplicates
    public void addItem(CartItem newItem) {
        if(newItem == null){
            throw new IllegalArgumentException("Items cannot be empty!");
        }
        //check currency
        if(!currencyCheck(newItem)){
            throw new IllegalStateException("Invalid currency!");
        }

        //check product existence
        CartItem existingItem = cartItems.stream()
                .filter(item -> item.getProduct().getProductId().equals(newItem.getProduct().getProductId()))
                .findFirst().orElse(null);

        //call and cartItem quantity mutate method else add new item to array
        if (existingItem != null) {
           existingItem.incrementQuantity(newItem.getQuantity());
        }else{
            addNewItem(newItem);
        }
    }

    //remove an item
    public void removeItem(UUID productId) {
        if(productId == null){
            throw new IllegalArgumentException("There is no item to be removed!");
        }
        //check item existence through helper method
        CartItem existingItem = checkItem(productId);

        //remove item from the list
        cartItems.remove(existingItem);
    }

    //replace quantity
    public void replaceExistingQuantity(UUID productId, int newQuantity) {
        if(productId == null){
            throw new IllegalArgumentException("Required fields cannot be empty!");
        }

        //check item existence through helper method
        CartItem existingItem = checkItem(productId);
        //call cartItem method to replace quantity
        existingItem.replaceQuantity(newQuantity);
    }

    //calculate total
    public Money calculateTotal(){
        //total calculation
        BigDecimal totalAmount = cartItems.stream()
                .map(item -> item.getProduct().getProductPrice().amount().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);

        return new Money(totalAmount, this.currency);
    }

    //clear cart
    public void clearCart() {
        cartItems.clear();
    }

    //check cart it empty
    public boolean isCartEmpty(){
        return cartItems.isEmpty();
    }
}


































