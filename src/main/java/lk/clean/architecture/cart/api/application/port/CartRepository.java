package lk.clean.architecture.cart.api.application.port;

import lk.clean.architecture.cart.api._domain.models.Cart;

import java.util.Optional;
import java.util.UUID;

public interface CartRepository {

    //find cart by id
    Optional<Cart> findCartById(UUID cartId);

    //save
    Cart save(Cart cart);
}
