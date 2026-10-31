package lk.clean.architecture.cart.api.domain.repository;

import lk.clean.architecture.cart.api._domain.models.Money;
import lk.clean.architecture.cart.api._domain.models.Product;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Optional;

public class ProductRepositoryImplTest {

    //test product find related to its id
    @Test
    void shouldFindProductByIdSuccessfully() {

        ProductRepositoryTest  cartRepositoryTest = new ProductRepositoryTest();
        Product product = new Product(
                "Test-Product-01",
                "Test-Description-01",
                new Money(new BigDecimal("100"), "LKR"));

        cartRepositoryTest.addProduct(product);

        Optional<Product> findProduct = cartRepositoryTest.findById(product.getProductId());

        assertTrue(findProduct.isPresent(), "Product should exist");
    }
}
