package lk.clean.architecture.cart.api.infrastructure.persistence;

import lk.clean.architecture.cart.api._domain.models.Cart;
import lk.clean.architecture.cart.api._domain.models.CartItem;
import lk.clean.architecture.cart.api._domain.models.Money;
import lk.clean.architecture.cart.api._domain.models.Product;
import lk.clean.architecture.cart.api._domain.repositories.CartRepository;
import lk.clean.architecture.cart.api._domain.repositories.ProductRepository;
import lk.clean.architecture.cart.api.infrastructure.persistence.entities.CartEntity;
import lk.clean.architecture.cart.api.infrastructure.persistence.entities.CartItemEntity;
import lk.clean.architecture.cart.api.infrastructure.persistence.jpa.CartJpaRepository;
import lk.clean.architecture.cart.api.infrastructure.persistence.persistenceMappers.PersistenceMapper;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class CartRepositoryImplTest {

    @MockitoBean
    private CartJpaRepository cartJpaRepository;

    @MockitoBean
    private ProductRepository productRepository;

    @MockitoBean
    private CartRepository cartRepository;

    @MockitoBean
    private PersistenceMapper persistenceMapper;


    @Test
    void shouldFindCartByIdSuccessfully() {

        UUID cartId = UUID.randomUUID();

        CartEntity cartEntity = new CartEntity();
        cartEntity.setCartId(cartId);
        cartEntity.setCurrency("LKR");
        cartEntity.setCartItems(new ArrayList<>());

        Product product1 = new Product(
                "Test-Product-01",
                "Test-Description-01",
                new Money(new BigDecimal("100.00"), "LKR"));

        Product product2 = new Product(
                "Test-Product-02",
                "Test-Description-02",
                new Money(new BigDecimal("50.00"), "LKR"));

        CartItemEntity cartItemEntity1 = new CartItemEntity();
        CartItemEntity cartItemEntity2 = new CartItemEntity();

        cartItemEntity1.setProductId(product1.getProductId());
        cartItemEntity1.setQuantity(2);

        cartItemEntity2.setProductId(product2.getProductId());
        cartItemEntity2.setQuantity(3);

        cartItemEntity1.setCart(cartEntity);
        cartItemEntity2.setCart(cartEntity);

        cartEntity.setCartItems(List.of(cartItemEntity1, cartItemEntity2));

        when(cartJpaRepository.findById(cartId)).thenReturn(Optional.of(cartEntity));
        when(productRepository.findById(product1.getProductId())).thenReturn(Optional.of(product1));
        when(productRepository.findById(product2.getProductId())).thenReturn(Optional.of(product2));

        Cart cart = new Cart("LKR");

        CartItem cartItem1 = new CartItem(product1, 2);
        CartItem cartItem2 = new CartItem(product2, 3);

        List<CartItem> mockCartItems = List.of(cartItem1, cartItem2);

        when(persistenceMapper.toCartDomainModel(eq(cartEntity), anyList())).thenReturn(cart);

       Optional<Cart> result = cartRepo
    }
}
