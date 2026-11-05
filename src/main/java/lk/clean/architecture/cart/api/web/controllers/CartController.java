package lk.clean.architecture.cart.api.web.controllers;

import jakarta.validation.Valid;
import lk.clean.architecture.cart.api.application.records.commands.CreateCartCommand;
import lk.clean.architecture.cart.api.application.records.results.CreateCartResult;
import lk.clean.architecture.cart.api.application.usecase.*;
import lk.clean.architecture.cart.api.web.DTOs.request.CreateCartRequestDTO;
import lk.clean.architecture.cart.api.web.DTOs.response.CreateCartResponseDTO;
import lk.clean.architecture.cart.api.web.webMappers.CartWebMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    //create cart endpoint
    @PostMapping
    public ResponseEntity<CreateCartResponseDTO> createCart(
            @Valid @RequestBody CreateCartRequestDTO createCartRequestDTO
    ){
        CreateCartCommand toCreateCommand = cartWebMapper.toCreateCartCommand(createCartRequestDTO);
        CreateCartResult toCreateResult = createCartUseCase.createCart(toCreateCommand);
        CreateCartResponseDTO toCreateResponse = cartWebMapper.toCreateCartResponseDTO(toCreateResult);

        return ResponseEntity.status(HttpStatus.CREATED).body(toCreateResponse);
    }
}
