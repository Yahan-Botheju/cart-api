package lk.clean.architecture.cart.api.infrastructure.persistence.persistenceMapper;

import lk.clean.architecture.cart.api._domain.models.*;
import lk.clean.architecture.cart.api.infrastructure.persistence.entities.CartEntity;
import lk.clean.architecture.cart.api.infrastructure.persistence.entities.CartItemEntity;
import lk.clean.architecture.cart.api.infrastructure.persistence.entities.ProductEntity;
import lk.clean.architecture.cart.api.infrastructure.persistence.persistenceMappers.PersistenceMapper;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

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

        CartItemEntity mockFirstItem = mockEntity.getCartItems().get(0);
        CartItemEntity mockSecondItem = mockEntity.getCartItems().get(1);

        //expectations
        assertEquals(mockEntity.getCartId(), mockCart.getCartId(), "Cart IDs should be same");
        assertEquals("LKR", mockEntity.getCurrency(),  "Currency should be LKR");
        assertNull(mockEntity.getCreatedAt(), "Created at should be null");
        assertNull(mockEntity.getUpdatedAt(), "Updated at should be null");
        assertEquals(mockCart.getCartItems().size(), mockEntity.getCartItems().size(), "CartItems should be same");

        assertEquals(product1.getProductId(), mockFirstItem.getProductId(), "Product 1 ID and MockFirstItem ID should be same");
        assertEquals(product2.getProductId(), mockSecondItem.getProductId(), "Product 2 ID and MockSecondItem ID should be same");
    }

    //test cart-entity map to domain-model
    @Test
    void shouldMapCartEntityToCartDomainModelSuccessfully() {
        //create mock entity
        CartEntity mockEntity = new CartEntity();
        mockEntity.setCartId(UUID.randomUUID());
        mockEntity.setCurrency("LKR");
        mockEntity.setCreatedAt(LocalDateTime.now());
        mockEntity.setUpdatedAt(LocalDateTime.now());

        //create mock cartItems
        CartItem mockCartItem1 = new CartItem(new Product(
                "Test-Product-01",
                "Test-Description-01",
                new Money(new BigDecimal("100"), "LKR")), 2
        );
        CartItem mockCartItem2 = new CartItem(new Product(
                "Test-Product-02",
                "Test-Description-02",
                new Money(new BigDecimal("50"), "LKR")), 3
        );
        //inject mapper
        PersistenceMapper persistenceMapper = Mappers.getMapper(PersistenceMapper.class);
        //set values to mapper
        Cart mockCartDomainModel = persistenceMapper.toCartDomainModel(mockEntity, List.of(mockCartItem1, mockCartItem2));
        //get items details
        CartItemSnapshot item1 = mockCartDomainModel.getCartItems().get(0);
        CartItemSnapshot item2 = mockCartDomainModel.getCartItems().get(1);
        //expectations
        assertEquals(mockEntity.getCartId(), mockCartDomainModel.getCartId(), "Cart IDs should be same");
        assertEquals(mockEntity.getCurrency(), mockCartDomainModel.getCurrency(), "Currency should be LKR");
        assertEquals(2, mockCartDomainModel.getCartItems().size(), "CartItems count should be 2");
        assertEquals(mockCartItem1.getProduct().getProductId(), item1.productId(), "Entity 1 product ID and Domain Model 1 product ID should be same");
        assertEquals(mockCartItem1.getQuantity(), item1.quantity(), "Quantity should be same");
        assertEquals(mockCartItem2.getProduct().getProductId(), item2.productId(), "Entity 2 product ID Domain Model 2 product ID should be same");
        assertEquals(mockCartItem2.getQuantity(), item2.quantity(), "Quantity should be same");

        assertNotNull(mockCartDomainModel);
    }

    //check empty entity mapping
    @Test
    void shouldReturnNullWhenCartEntityIsNull() {

        PersistenceMapper persistenceMapper = Mappers.getMapper(PersistenceMapper.class);
        //empty data mapping
        Cart mockCart = persistenceMapper.toCartDomainModel(null, List.of());
        //expectations
        assertNull(mockCart);
    }

    //test product-domain-model to product-entity mapping
    @Test
    void shouldMapProductDomainModelToProductEntitySuccessfully() {
        //mock product
        Product product = new Product(
                "Test-Product-01",
                "Test-Description-01",
                new Money(new BigDecimal("150.00"), "LKR"));
        //mapper initiate
        PersistenceMapper persistenceMapper = Mappers.getMapper(PersistenceMapper.class);
        //map to product-entity
        ProductEntity mockProductEntity = persistenceMapper.toProductEntity(product);
        //expectations
        assertEquals(product.getProductId(), mockProductEntity.getProductId(), "Product IDs should be same");
        assertEquals(product.getProductName(), mockProductEntity.getProductName(), "Product names should be same");
        assertEquals(product.getProductDescription(), mockProductEntity.getProductDescription(), "Product descriptions should be same");
        assertEquals(product.getProductPrice().amount(), mockProductEntity.getPriceAmount(), "Product price amount should be same" );
        assertEquals(product.getProductPrice().currency(), mockProductEntity.getPriceCurrency(), "Product price currency should be same");
        assertTrue(mockProductEntity.isAvailable());
    }

    //test product-entity to product-domain-model
    @Test
    void shouldMapProductEntityToProductDomainModelSuccessfully() {
        //mock product-entity
        ProductEntity mockProductEntity = new ProductEntity();
        mockProductEntity.setProductId(UUID.randomUUID());
        mockProductEntity.setProductName("Test-Product-01");
        mockProductEntity.setProductDescription("Test-Description-01");
        mockProductEntity.setPriceAmount(new BigDecimal("150.00"));
        mockProductEntity.setPriceCurrency("LKR");
        mockProductEntity.setAvailable(true);
        //initiate mapper
        PersistenceMapper persistenceMapper = Mappers.getMapper(PersistenceMapper.class);
        //map product-entity to product-domain-model
        Product product = persistenceMapper.toProductDomainModel(mockProductEntity);
        //expectations
        assertEquals(mockProductEntity.getProductId(), product.getProductId(), "Product IDs should be same");
        assertEquals(mockProductEntity.getProductName(), product.getProductName(), "Product names should be same");
        assertEquals(mockProductEntity.getProductDescription(), product.getProductDescription(), "Product descriptions should be same");
        assertEquals(mockProductEntity.getPriceAmount(), product.getProductPrice().amount(), "Product price amount should be same");
        assertEquals(mockProductEntity.getPriceCurrency(), product.getProductPrice().currency(), "Product price currency should be same as LKR");
        assertTrue(mockProductEntity.isAvailable());


    }
}




































