package lk.clean.architecture.cart.api.web.controllers;

import jakarta.validation.Valid;
import lk.clean.architecture.cart.api.application.records.cart.commands.*;
import lk.clean.architecture.cart.api.application.records.cart.results.*;
import lk.clean.architecture.cart.api.application.usecase.cart.*;
import lk.clean.architecture.cart.api.web.DTOs.cart.request.AddItemToCartRequestDTO;
import lk.clean.architecture.cart.api.web.DTOs.cart.request.CreateCartRequestDTO;
import lk.clean.architecture.cart.api.web.DTOs.cart.request.UpdateCartItemQuantityRequestDTO;
import lk.clean.architecture.cart.api.web.DTOs.cart.response.*;
import lk.clean.architecture.cart.api.web.webMappers.CartWebMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/carts")
public class CartController {

    //inject required dependencies
    private final AddItemToCartUseCase addItemToCartUseCase;
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


    //view-cart
    @GetMapping("/{cartId}")
    public ResponseEntity<GetCartResponseDTO> getCart(
            @PathVariable UUID cartId
    ){
        GetCartCommand toGetCartCommand = cartWebMapper.toGetCartCommand(cartId);
        GetCartResult toGetCartResult = getCartUseCase.getCart(toGetCartCommand);
        GetCartResponseDTO toGetCartResponseDTO = cartWebMapper.toGetCartResponseDTO(toGetCartResult);

        return ResponseEntity.ok(toGetCartResponseDTO);
    }


    //create-cart-endpoint
    @PostMapping
    public ResponseEntity<CreateCartResponseDTO> createCart(
            @Valid @RequestBody CreateCartRequestDTO createCartRequestDTO
    ){
        CreateCartCommand toCreateCommand = cartWebMapper.toCreateCartCommand(createCartRequestDTO);
        CreateCartResult toCreateResult = createCartUseCase.createCart(toCreateCommand);
        CreateCartResponseDTO toCreateResponse = cartWebMapper.toCreateCartResponseDTO(toCreateResult);

        return ResponseEntity.status(HttpStatus.CREATED).body(toCreateResponse);
    }

    //add-item-to-cart
    @PostMapping("/{cartId}/items")
    public ResponseEntity<AddItemToCartResponseDTO> addItemToCart(
            @PathVariable UUID cartId,
            @Valid @RequestBody AddItemToCartRequestDTO addItemToCartRequestDTO
    ){
        AddItemToCartCommand toAddItemToCartCommand = cartWebMapper.toAddItemToCartCommand(cartId, addItemToCartRequestDTO);
        AddItemToCartResult toAddItemToCartResult = addItemToCartUseCase.addItemToCart(toAddItemToCartCommand);
        AddItemToCartResponseDTO toAaddItemToCartResponseDTO =  cartWebMapper.toAddItemToCartResponseDTO(toAddItemToCartResult);

        return ResponseEntity.status(HttpStatus.OK).body(toAaddItemToCartResponseDTO);
    }

    //update cart-item-quantity
    @PatchMapping("/{cartId}/items/{productId}")
    public ResponseEntity<UpdateCartItemQuantityResponseDTO> updateCartItemQuantity(
            @PathVariable UUID cartId,
            @PathVariable UUID productId,
            @Valid @RequestBody UpdateCartItemQuantityRequestDTO updateCartItemQuantityRequestDTO
    ){
        UpdateCartItemQuantityCommand toUpdateCartItemQuantityCommand = cartWebMapper
                .toUpdateCartItemQuantityCommand(cartId, productId, updateCartItemQuantityRequestDTO);
        UpdateCartItemQuantityResult toUpdateCartItemQuantityResult = updateCartItemQuantityUseCase
                .updateCartItemQuantity(toUpdateCartItemQuantityCommand);
        UpdateCartItemQuantityResponseDTO responseDTO = cartWebMapper
                .toUpdateCartItemQuantityResponseDTO(toUpdateCartItemQuantityResult);

        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    //remove-item-from-cart
    @DeleteMapping("/{cartId}/items/{productId}")
    public ResponseEntity<RemoveItemFromCartResponseDTO> removeItemFromCart(
            @PathVariable UUID cartId,
            @PathVariable UUID productId
    ){
        RemoveItemFromCartCommand toRemoveItemFromCartCommand = cartWebMapper
                .toRemoveItemFromCartCommand(cartId, productId);
        RemoveItemFromCartResult toRemoveItemFromCartResult = removeItemFromCartUseCase
                .removeItemFromCart(toRemoveItemFromCartCommand);
        RemoveItemFromCartResponseDTO responseDTO =  cartWebMapper
                .toRemoveItemFromCartResponseDTO(toRemoveItemFromCartResult);

        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }

    //clear-cart
    @DeleteMapping("/{cartId}/items")
    public ResponseEntity<ClearCartResponseDTO> clearCart(
            @PathVariable UUID cartId
    ){
        ClearCartCommand toClearCartCommand = cartWebMapper.toClearCartCommand(cartId);
        ClearCartResult toClearCartResult = clearCartUseCase.clearCart(toClearCartCommand);
        ClearCartResponseDTO responseDTO = cartWebMapper
                .toClearCartResponseDTO(toClearCartResult);

        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }
}
