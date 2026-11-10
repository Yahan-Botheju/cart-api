package lk.clean.architecture.cart.api.infrastructure.persistence.persistenceMapper;

import lk.clean.architecture.cart.api._domain.models.Cart;
import lk.clean.architecture.cart.api._domain.models.CartItem;
import lk.clean.architecture.cart.api._domain.models.Money;
import lk.clean.architecture.cart.api._domain.models.Product;
import lk.clean.architecture.cart.api.infrastructure.persistence.entities.CartEntity;
import lk.clean.architecture.cart.api.infrastructure.persistence.persistenceMappers.PersistenceMapper;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

public class PersistenceMapperTest {

    //test Domain-Model to Cart-Entity Mapper
    @Test
    void shouldMapCartDomainModelToCartEntitySuccessfully() {
        //mock cart
        Cart mockCart = new Cart("LKR");

        //mock products
        Product product1 = new Product(
                "Test-Product-01",
                "Test-Description-01",
                new Money(new BigDecimal("100"), "LKR"));
        Product product2 = new Product(
                "Test-Product-02",
                "Test-Description-02",
                new Money(new BigDecimal("50"), "LKR"));
        //mock product add to cart
        mockCart.addItem(new CartItem(product1, 2));
        mockCart.addItem(new CartItem(product2, 3));
        //initiate mapper
        PersistenceMapper persistenceMapper = Mappers.getMapper(PersistenceMapper.class);
        //turn to entity
        CartEntity mockEntity = persistenceMapper.toCartEntity(mockCart);
        //expectations
        assertEquals(mockEntity.getCartId(), mockCart.getCartId(), "Cart IDs should be same");
        assertEquals("LKR", mockEntity.getCurrency(),  "Currency should be LKR");
        assertNull(mockEntity.getCreatedAt(), "Created at should be null");
        assertNull(mockEntity.getUpdatedAt(), "Updated at should be null");
        assertEquals(mockCart.getCartItems().size(), mockEntity.getCartItems().size(), "CartItems should be same");
    }
}
