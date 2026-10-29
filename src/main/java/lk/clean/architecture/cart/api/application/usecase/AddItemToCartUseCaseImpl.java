package lk.clean.architecture.cart.api.application.usecase;

import lk.clean.architecture.cart.api._domain.models.Cart;
import lk.clean.architecture.cart.api._domain.models.CartItem;
import lk.clean.architecture.cart.api._domain.models.Product;
import lk.clean.architecture.cart.api._domain.repositories.CartRepository;
import lk.clean.architecture.cart.api._domain.repositories.ProductRepository;

import java.util.UUID;

public class AddItemToCartUseCaseImpl implements AddItemToCartUseCase {

    //inject required dependence
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public AddItemToCartUseCaseImpl(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Cart addItemToCart(UUID cartId, UUID productId, int quantity) {

        Cart cart = cartRepository.findCartById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found!"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found!"));

        //create new cartItem obj
        CartItem cartItem = new CartItem(product, quantity);
        //add item to cart
        cart.addItem(cartItem);

        return cartRepository.save(cart);
    }
}
