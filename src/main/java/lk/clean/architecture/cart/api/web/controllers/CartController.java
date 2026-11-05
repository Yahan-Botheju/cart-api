package lk.clean.architecture.cart.api.web.controllers;

import lk.clean.architecture.cart.api.application.usecase.*;
import lk.clean.architecture.cart.api.web.webMappers.CartWebMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/carts")
public class CartController {

    //inject required dependencies
    private final AddItemToCartUseCase  addItemToCartUseCase;
    private final CreateCartUseCase createCartUseCase;
    private final GetCartUseCase getCartUseCase;
    private final UpdateCartItemQuantityUseCase updateCartItemQuantityUseCase;
    private final RemoveItemFromCartUseCase removeItemFromCartUseCase;
    private final ClearCartUseCase clearCartUseCase;
    private final CartWebMapper cartWebMapper;

    public CartController(
            AddItemToCartUseCase addItemToCartUseCase,
            CreateCartUseCase createCartUseCase,
            GetCartUseCase getCartUseCase,
            UpdateCartItemQuantityUseCase updateCartItemQuantityUseCase,
            RemoveItemFromCartUseCase removeItemFromCartUseCase,
            ClearCartUseCase clearCartUseCase,
            CartWebMapper cartWebMapper
    ) {
        this.addItemToCartUseCase = addItemToCartUseCase;
        this.createCartUseCase = createCartUseCase;
        this.getCartUseCase = getCartUseCase;
        this.updateCartItemQuantityUseCase = updateCartItemQuantityUseCase;
        this.removeItemFromCartUseCase = removeItemFromCartUseCase;
        this.clearCartUseCase = clearCartUseCase;
        this.cartWebMapper = cartWebMapper;
    }
}
