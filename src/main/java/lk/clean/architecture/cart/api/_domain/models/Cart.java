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
    public List<CartItem> getCartItems() {
        return Collections.unmodifiableList(cartItems);
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
    private boolean currencyCheck(){
        return cartItems.stream()
                .allMatch(item -> item.getProduct().getProductPrice().currency().equals(this.currency));
    }



    /* __PUBLIC_METHODS__ */

    //add item
    public void addItem(CartItem newItem) {
        if(newItem == null){
            throw new IllegalArgumentException("Items cannot be empty!");
        }
        //check currency
        if(!currencyCheck()){
            throw new IllegalStateException("Invalid currency!");
        }

        this.cartItems.add(newItem);
    }

    //add items without duplicates
    public void addItemWithoutDuplicates(CartItem newItem) {
        if(newItem == null){
            throw new IllegalArgumentException("Items cannot be empty!");
        }
        //check currency
        if(!currencyCheck()){
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
            addItem(newItem);
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
        if(productId == null || newQuantity <= 0){
            throw new IllegalArgumentException("Required fields cannot be empty!");
        }

        //check item existence through helper method
        CartItem existingItem = checkItem(productId);
        //call cartItem method to replace quantity
        existingItem.replaceQuantity(newQuantity);
    }

    //calculate total
    public Money calculateTotal(){
        //check currency
        if(!currencyCheck()){
            throw new IllegalStateException("Invalid currency!");
        }
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


































