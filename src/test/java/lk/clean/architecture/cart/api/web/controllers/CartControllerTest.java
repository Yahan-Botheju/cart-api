package lk.clean.architecture.cart.api.web.controllers;

import lk.clean.architecture.cart.api.application.records.commands.*;
import lk.clean.architecture.cart.api.application.records.results.*;
import lk.clean.architecture.cart.api.application.usecase.*;
import lk.clean.architecture.cart.api.web.DTOs.cart.request.AddItemToCartRequestDTO;
import lk.clean.architecture.cart.api.web.DTOs.cart.request.CreateCartRequestDTO;
import lk.clean.architecture.cart.api.web.DTOs.cart.request.UpdateCartItemQuantityRequestDTO;
import lk.clean.architecture.cart.api.web.DTOs.cart.response.*;
import lk.clean.architecture.cart.api.web.DTOs.cart.response.separation_records.MoneyResponseDTO;
import lk.clean.architecture.cart.api.web.webMappers.CartWebMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CartController.class)
public class CartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    //inject mock usecase and mapper

    @MockitoBean
    private AddItemToCartUseCase addItemToCartUseCase;
    @MockitoBean
    private CreateCartUseCase createCartUseCase;
    @MockitoBean
    private GetCartUseCase getCartUseCase;
    @MockitoBean
    private UpdateCartItemQuantityUseCase updateCartItemQuantityUseCase;
    @MockitoBean
    private RemoveItemFromCartUseCase removeItemFromCartUseCase;
    @MockitoBean
    private ClearCartUseCase clearCartUseCase;
    @MockitoBean
    private CartWebMapper cartWebMapper;

    //test-create-cart
    @Test
    void shouldCreateCartSuccessfully() throws Exception {
        //given inputs
        UUID cartId = UUID.randomUUID();
        String currency = "LKR";

        //create request obj
        CreateCartRequestDTO createCartRequestDTO = new CreateCartRequestDTO();
        createCartRequestDTO.setCurrency(currency);

        CreateCartCommand mockCommand = mock(CreateCartCommand.class);
        CreateCartResult mockResult = mock(CreateCartResult.class);

        //set values to response
        CreateCartResponseDTO responseDTO = new CreateCartResponseDTO(cartId, currency, true);

        //make connections
        when(cartWebMapper.toCreateCartCommand(any(CreateCartRequestDTO.class))).thenReturn(mockCommand);
        when(createCartUseCase.createCart(mockCommand)).thenReturn(mockResult);
        when(cartWebMapper.toCreateCartResponseDTO(mockResult)).thenReturn(responseDTO);

        //WHEN & THEN
        mockMvc.perform(post("/api/v1/carts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createCartRequestDTO)))
                //expectations
                .andExpect(status().isCreated()) //201
                .andExpect(jsonPath("$.cartId").value(cartId.toString()))
                .andExpect(jsonPath("$.currency").value("LKR"))
                .andExpect(jsonPath("$.cartEmpty").value(true));
    }

    //test add-item-to-cart
    @Test
    void shouldAddItemToCartSuccessfully() throws Exception {
        //create given inputs
        UUID cartId = UUID.randomUUID();
        UUID productId = UUID.randomUUID();
        int quantity = 2;
        MoneyResponseDTO subtotal = new MoneyResponseDTO();
        subtotal.setCurrency("LKR");
        subtotal.setAmount(new BigDecimal("500.00"));

        //set define values to requestDTO
        AddItemToCartRequestDTO mockRequestDTO = new AddItemToCartRequestDTO();
        mockRequestDTO.setProductId(productId);
        mockRequestDTO.setQuantity(quantity);

        //initiate mock record class
        AddItemToCartCommand mockCommand = mock(AddItemToCartCommand.class);
        AddItemToCartResult mockResult = mock(AddItemToCartResult.class);

        //set initiated values to response
        AddItemToCartResponseDTO responseDTO = new AddItemToCartResponseDTO();
        responseDTO.setCartId(cartId);
        responseDTO.setProductId(productId);
        responseDTO.setQuantity(quantity);
        responseDTO.setSubtotal(subtotal);

        //make connections between layers
        when(cartWebMapper.toAddItemToCartCommand(eq(cartId), any(AddItemToCartRequestDTO.class))).thenReturn(mockCommand);
        when(addItemToCartUseCase.addItemToCart(mockCommand)).thenReturn(mockResult);
        when(cartWebMapper.toAddItemToCartResponseDTO(mockResult)).thenReturn(responseDTO);

        mockMvc.perform(post("/api/v1/carts/{cartId}/items", cartId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mockRequestDTO)))
                .andExpect(status().is2xxSuccessful())
                //expectations
                .andExpect(jsonPath("$.cartId").value(cartId.toString()))
                .andExpect(jsonPath("$.productId").value(productId.toString()))
                .andExpect(jsonPath("$.quantity").value(quantity))
                .andExpect(jsonPath("$.subtotal.amount").value(500.00))
                .andExpect(jsonPath("$.subtotal.currency").value("LKR"));
    }

    //test update-cart-item-quantity
    @Test
    void shouldUpdateCartItemQuantitySuccessfully() throws Exception {
        //create given inputs
        UUID cartId = UUID.randomUUID();
        UUID productId = UUID.randomUUID();
        int newQuantity = 3;

        UpdateCartItemQuantityRequestDTO requestDTO = new UpdateCartItemQuantityRequestDTO();
        requestDTO.setQuantity(newQuantity);

        UpdateCartItemQuantityCommand mockCommand = mock(UpdateCartItemQuantityCommand.class);
        UpdateCartItemQuantityResult mockResult = mock(UpdateCartItemQuantityResult.class);

        //set values to responseDTO
        UpdateCartItemQuantityResponseDTO responseDTO = new UpdateCartItemQuantityResponseDTO();
        responseDTO.setCartId(cartId);
        responseDTO.setProductId(productId);
        responseDTO.setQuantity(newQuantity);

        //Mocking Mapper and UseCases
        when(cartWebMapper.toUpdateCartItemQuantityCommand(eq(cartId), eq(productId), any(UpdateCartItemQuantityRequestDTO.class)))
                .thenReturn(mockCommand);
        when(updateCartItemQuantityUseCase.updateCartItemQuantity(mockCommand)).thenReturn(mockResult);
        when(cartWebMapper.toUpdateCartItemQuantityResponseDTO(mockResult)).thenReturn(responseDTO);

        //When & Then
        mockMvc.perform(patch("/api/v1/carts/{cartId}/items/{productId}", cartId, productId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isOk())
                //expectations
                .andExpect(jsonPath("$.cartId").value(cartId.toString()))
                .andExpect(jsonPath("$.productId").value(productId.toString()))
                .andExpect(jsonPath("$.quantity").value(newQuantity));
    }

    //test get-cart
    @Test
    void shouldGetCartSuccessfully() throws Exception {

        UUID cartId = UUID.randomUUID();
        String currency = "LKR";

        GetCartCommand mockCommand = mock(GetCartCommand.class);
        GetCartResult mockResult = mock(GetCartResult.class);

        MoneyResponseDTO moneyResponseDTO = new MoneyResponseDTO();
        moneyResponseDTO.setAmount(new BigDecimal("1500.00"));
        moneyResponseDTO.setCurrency(currency);

        GetCartResponseDTO responseDTO = new GetCartResponseDTO();
        responseDTO.setCartId(cartId);
        responseDTO.setCurrency(currency);
        responseDTO.setCartItemSnapshotDTOS(List.of());
        responseDTO.setTotal(moneyResponseDTO);
        responseDTO.setCartEmpty(true);

        when(cartWebMapper.toGetCartCommand(eq(cartId))).thenReturn(mockCommand);
        when(getCartUseCase.getCart(mockCommand)).thenReturn(mockResult);
        when(cartWebMapper.toGetCartResponseDTO(mockResult)).thenReturn(responseDTO);


        mockMvc.perform(get("/api/v1/carts/{cartId}", cartId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                //expectations
                .andExpect(jsonPath("$.cartId").value(cartId.toString()))
                .andExpect(jsonPath("$.currency").value(currency))
                .andExpect(jsonPath("$.cartEmpty").value(true))
                .andExpect(jsonPath("$.total.amount").value(1500.00))
                .andExpect(jsonPath("$.total.currency").value("LKR"));
    }

    //test remove-item-from-cart
    @Test
    void shouldRemoveItemFromCartSuccessfully() throws Exception {

        UUID cartId = UUID.randomUUID();
        UUID productId = UUID.randomUUID();

        RemoveItemFromCartCommand mockCommand = mock(RemoveItemFromCartCommand.class);
        RemoveItemFromCartResult mockResult = mock(RemoveItemFromCartResult.class);

        RemoveItemFromCartResponseDTO responseDTO = new RemoveItemFromCartResponseDTO();
        responseDTO.setCartId(cartId);
        responseDTO.setRemovedProductId(productId);
        responseDTO.setCartEmpty(false);

        when(cartWebMapper.toRemoveItemFromCartCommand(eq(cartId), eq(productId))).thenReturn(mockCommand);
        when(removeItemFromCartUseCase.removeItemFromCart(mockCommand)).thenReturn(mockResult);
        when(cartWebMapper.toRemoveItemFromCartResponseDTO(mockResult)).thenReturn(responseDTO);


        mockMvc.perform(delete("/api/v1/carts/{cartId}/items/{productId}", cartId, productId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                //expectations
                .andExpect(jsonPath("$.cartId").value(cartId.toString()))
                .andExpect(jsonPath("$.removedProductId").value(productId.toString()))
                .andExpect(jsonPath("$.cartEmpty").value(false));
    }

    //test clear-cart
    @Test
    void shouldClearCartSuccessfully() throws Exception {

        UUID cartId = UUID.randomUUID();

        ClearCartCommand mockCommand = mock(ClearCartCommand.class);
        ClearCartResult mockResult = mock(ClearCartResult.class);

        ClearCartResponseDTO responseDTO = new ClearCartResponseDTO();
        responseDTO.setCartId(cartId);
        responseDTO.setCartEmpty(true);


        when(cartWebMapper.toClearCartCommand(eq(cartId))).thenReturn(mockCommand);
        when(clearCartUseCase.clearCart(mockCommand)).thenReturn(mockResult);
        when(cartWebMapper.toClearCartResponseDTO(mockResult)).thenReturn(responseDTO);


        mockMvc.perform(delete("/api/v1/carts/{cartId}/items", cartId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                //expectations
                .andExpect(jsonPath("$.cartId").value(cartId.toString()))
                .andExpect(jsonPath("$.cartEmpty").value(true));
    }


}
