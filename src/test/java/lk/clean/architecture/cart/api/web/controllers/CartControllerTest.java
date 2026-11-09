package lk.clean.architecture.cart.api.web.controllers;

import lk.clean.architecture.cart.api.application.records.commands.AddItemToCartCommand;
import lk.clean.architecture.cart.api.application.records.commands.CreateCartCommand;
import lk.clean.architecture.cart.api.application.records.commands.GetCartCommand;
import lk.clean.architecture.cart.api.application.records.results.AddItemToCartResult;
import lk.clean.architecture.cart.api.application.records.results.CreateCartResult;
import lk.clean.architecture.cart.api.application.records.results.GetCartResult;
import lk.clean.architecture.cart.api.application.usecase.*;
import lk.clean.architecture.cart.api.web.DTOs.request.AddItemToCartRequestDTO;
import lk.clean.architecture.cart.api.web.DTOs.request.CreateCartRequestDTO;
import lk.clean.architecture.cart.api.web.DTOs.response.AddItemToCartResponseDTO;
import lk.clean.architecture.cart.api.web.DTOs.response.CreateCartResponseDTO;
import lk.clean.architecture.cart.api.web.DTOs.response.GetCartResponseDTO;
import lk.clean.architecture.cart.api.web.DTOs.response.separation_records.CartItemSnapshotDTO;
import lk.clean.architecture.cart.api.web.DTOs.response.separation_records.MoneyResponseDTO;
import lk.clean.architecture.cart.api.web.webMappers.CartWebMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
        int  quantity = 2;
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
        when(cartWebMapper.toAddItemToCartCommand(eq(cartId),any(AddItemToCartRequestDTO.class))).thenReturn(mockCommand);
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
}
