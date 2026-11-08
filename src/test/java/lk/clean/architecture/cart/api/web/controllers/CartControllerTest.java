package lk.clean.architecture.cart.api.web.controllers;

import lk.clean.architecture.cart.api.application.records.commands.CreateCartCommand;
import lk.clean.architecture.cart.api.application.records.results.CreateCartResult;
import lk.clean.architecture.cart.api.application.usecase.*;
import lk.clean.architecture.cart.api.web.DTOs.request.CreateCartRequestDTO;
import lk.clean.architecture.cart.api.web.DTOs.response.CreateCartResponseDTO;
import lk.clean.architecture.cart.api.web.webMappers.CartWebMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.data.relational.core.sql.When;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.web.servlet.function.RequestPredicates.POST;

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
}
