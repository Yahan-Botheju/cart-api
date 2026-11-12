package lk.clean.architecture.cart.api.infrastructure.persistence;

import lk.clean.architecture.cart.api._domain.models.Cart;
import lk.clean.architecture.cart.api._domain.models.CartItem;
import lk.clean.architecture.cart.api._domain.models.Money;
import lk.clean.architecture.cart.api._domain.models.Product;
import lk.clean.architecture.cart.api._domain.repositories.ProductRepository;
import lk.clean.architecture.cart.api.infrastructure.persistence.entities.CartEntity;
import lk.clean.architecture.cart.api.infrastructure.persistence.entities.CartItemEntity;
import lk.clean.architecture.cart.api.infrastructure.persistence.jpa.CartJpaRepository;
import lk.clean.architecture.cart.api.infrastructure.persistence.persistenceMappers.PersistenceMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class CartRepositoryImplTest {

    @Mock
    private CartJpaRepository cartJpaRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private PersistenceMapper persistenceMapper;

    @InjectMocks
    private CartRepositoryImpl cartRepositoryImpl;

    //test find cart by id
    @Test
    void shouldFindCartByIdSuccessfully() {
        //Create cart and mock products
        Cart cart = new Cart("LKR");

        Product product1 = new Product(
                "Test-Product-01",
                "Test-Description-01",
                new Money(new BigDecimal("100.00"), "LKR"));

        Product product2 = new Product(
                "Test-Product-02",
                "Test-Description-02",
                new Money(new BigDecimal("50.00"), "LKR"));

        //add products to cart
        cart.addItem(new CartItem(product1, 2));
        cart.addItem(new CartItem(product2, 3));

        //prepare mock entities and objects
        CartEntity cartEntity = new CartEntity();

        //setup Mock behaviors
        when(cartJpaRepository.findById(cart.getCartId())).thenReturn(Optional.of(cartEntity));
        when(persistenceMapper.toCartDomainModel(any(CartEntity.class), anyList())).thenReturn(cart);

        //execute the method under test
        Optional<Cart> result = cartRepositoryImpl.findCartById(cart.getCartId());

        //expectations
        assertTrue(result.isPresent(), "Cart should be present");

        Cart resultCart = result.get();

        assertEquals(cart.getCartId(), resultCart.getCartId(), "Cart ID should be the same");
        assertEquals("LKR", resultCart.getCurrency(), "Currency should be LKR");
        assertEquals(2, resultCart.getCartItems().size(), "Cart should contain 2 items");
        assertEquals(product1.getProductId(), resultCart.getCartItems().get(0).productId());
        assertEquals(2, resultCart.getCartItems().get(0).quantity());
        assertEquals(product2.getProductId(), resultCart.getCartItems().get(1).productId());
        assertEquals(3, resultCart.getCartItems().get(1).quantity());

        //verifications
        verify(cartJpaRepository).findById(cart.getCartId());
    }

    //test cart-save successfully
    @Test
    void shouldSaveCartSuccessfully() {
        //create cart
        Cart cart = new Cart("LKR");

        Product product1 = new Product(
                "Test-Product-01",
                "Test-Description-01",
                new Money(new BigDecimal("100.00"), "LKR"));

        cart.addItem(new CartItem(product1, 2));

        //create entity
        CartEntity cartEntity = new CartEntity();
        CartEntity savedCartEntity = new CartEntity();

        //add values to entity
        CartItemEntity cartItemEntity = new CartItemEntity();
        cartItemEntity.setProductId(product1.getProductId());
        cartItemEntity.setQuantity(2);
        savedCartEntity.setCartItems(List.of(cartItemEntity));

        //initiate mock behaviors
        when(persistenceMapper.toCartEntity(cart)).thenReturn(cartEntity);
        when(cartJpaRepository.save(cartEntity)).thenReturn(savedCartEntity);
        when(productRepository.findById(product1.getProductId())).thenReturn(Optional.of(product1));
        when(persistenceMapper.toCartDomainModel(eq(savedCartEntity), anyList())).thenReturn(cart);


        Cart savedCart = cartRepositoryImpl.save(cart);

        //expectations
        assertNotNull(savedCart, "Saved cart should not be null");
        assertEquals("LKR", savedCart.getCurrency(), "Currency should match");
        assertEquals(1, savedCart.getCartItems().size(), "Cart should contain 1 item");
        assertEquals(product1.getProductId(), savedCart.getCartItems().get(0).productId());
        assertEquals(2, savedCart.getCartItems().get(0).quantity());

        //verifications
        verify(persistenceMapper).toCartEntity(cart);
        verify(cartJpaRepository).save(cartEntity);
        verify(productRepository).findById(product1.getProductId());
        verify(persistenceMapper).toCartDomainModel(eq(savedCartEntity), anyList());
    }
}








































