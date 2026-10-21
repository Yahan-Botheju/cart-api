package lk.clean.architecture.cart.api._domain.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

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

}
