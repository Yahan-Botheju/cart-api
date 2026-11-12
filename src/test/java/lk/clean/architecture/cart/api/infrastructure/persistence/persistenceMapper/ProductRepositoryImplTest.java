package lk.clean.architecture.cart.api.infrastructure.persistence.persistenceMapper;

import lk.clean.architecture.cart.api._domain.models.Money;
import lk.clean.architecture.cart.api._domain.models.Product;
import lk.clean.architecture.cart.api._domain.repositories.ProductRepository;
import lk.clean.architecture.cart.api.infrastructure.persistence.ProductRepositoryImpl;
import lk.clean.architecture.cart.api.infrastructure.persistence.entities.ProductEntity;
import lk.clean.architecture.cart.api.infrastructure.persistence.jpa.ProductJpaRepository;
import lk.clean.architecture.cart.api.infrastructure.persistence.persistenceMappers.PersistenceMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductRepositoryImplTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductJpaRepository productJpaRepository;

    @Mock
    private PersistenceMapper persistenceMapper;

    @InjectMocks
    private ProductRepositoryImpl productRepositoryImpl;

    @Test
    void shouldFindProductByIdSuccessfully() {
        // 1. Given (අවශ්‍ය IDs, Entities සහ Domain Models සකස් කිරීම)
        UUID productId = UUID.randomUUID();

        ProductEntity productEntity = new ProductEntity();
        productEntity.setProductId(productId);

        Product product  = new Product(
                "Test-Product-01",
                "Test-Description-01",
                new Money(new BigDecimal("150.00"), "LKR"));



        when(productJpaRepository.findById(productId)).thenReturn(Optional.of(productEntity));
        when(persistenceMapper.toProductDomainModel(productEntity)).thenReturn(product);

        // 3. Execute the method under test
        Optional<Product> result = productRepositoryImpl.findById(productId);

        // 4. Assertions
        assertTrue(result.isPresent(), "Product should be present");
        assertEquals(productId, result.get().getProductId(), "Product ID should match");
        assertEquals("Test-Product", result.get().getProductName(), "Product name should match");

        // 5. Verifications
        verify(productJpaRepository).findById(productId);
        verify(persistenceMapper).toProductDomainModel(productEntity);
    }
}
