package lk.clean.architecture.cart.api._domain.models;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import java.util.*;
import java.util.stream.Collectors;

public class Cart {
    private final UUID cartId;
    private List<CartItem> cartItems;

    /* __CONSTRUCTOR__ */

    public Cart() {
        this.cartId = UUID.randomUUID();
        this.cartItems = new ArrayList<CartItem>();
    }

    /* __GETTERS__ */

    public UUID getCartId() { return cartId; }

    public List<CartItem> getCartItems() {
        return Collections.unmodifiableList(cartItems);
    }


    /* __DOMAIN_LOGIC__ */


    //add item
    public void addItem(CartItem newItem) {
        if(newItem == null){
            throw new IllegalArgumentException("Items cannot be empty!");
        }
        this.cartItems.add(newItem);
    }


    //add items without duplicates
    public void addItemWithoutDuplicates(CartItem newItem) {
        if(newItem == null){
            throw new IllegalArgumentException("Items cannot be empty!");
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

    /* --HELPER_METHOD-- */

    private CartItem checkItem(UUID productId){
        return cartItems.stream()
                .filter(item -> item.getProduct().getProductId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("There is no item to be removed!"));
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

}
