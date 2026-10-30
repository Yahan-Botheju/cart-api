package lk.clean.architecture.cart.api.application.usecase;

import lk.clean.architecture.cart.api._domain.models.*;
import lk.clean.architecture.cart.api._domain.repositories.CartRepository;
import lk.clean.architecture.cart.api._domain.repositories.ProductRepository;
import lk.clean.architecture.cart.api.application.records.commands.AddItemToCartCommand;
import lk.clean.architecture.cart.api.application.records.results.AddItemToCartResult;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import java.util.Optional;

public class AddItemToCartUseCaseImpl implements AddItemToCartUseCase {

    //inject required dependence
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public AddItemToCartUseCaseImpl(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    @Override
    public AddItemToCartResult addItemToCart(AddItemToCartCommand addItemToCartCommand) {

        Cart cart = cartRepository.findCartById(addItemToCartCommand.cartId())
                .orElseThrow(() -> new RuntimeException("Cart not found!"));

        Product product = productRepository.findById(addItemToCartCommand.productId())
                .orElseThrow(() -> new RuntimeException("Product not found!"));

        //create new cartItem obj
        CartItem cartItem = new CartItem(product, addItemToCartCommand.quantity());
        //add item to cart
        cart.addItem(cartItem);

        Cart savedCart = cartRepository.save(cart);

        //get subtotal and quantity
        CartItemSnapshot results = savedCart.getCartItems().stream()
                .filter(item -> item.productId().equals(addItemToCartCommand.productId()))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Product not found!!"));

        return new AddItemToCartResult(
                savedCart.getCartId(),
                results.productId(),
                results.quantity(),
                results.subTotal()
        );
    }
}
