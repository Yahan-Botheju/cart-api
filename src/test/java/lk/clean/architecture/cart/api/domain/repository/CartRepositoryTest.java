package lk.clean.architecture.cart.api.domain.repository;

import lk.clean.architecture.cart.api._domain.models.Cart;
import lk.clean.architecture.cart.api._domain.repositories.CartRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

class CartRepositoryTest implements CartRepository {

    Map<UUID, Cart> carts = new HashMap<>();

    @Override
    public Optional<Cart> findCartById(UUID cartId) {
        return Optional.ofNullable(carts.get(cartId));
    }

    @Override
    public Cart save(Cart cart) {
        carts.put(cart.getCartId(), cart);
        return cart;
    }
}
