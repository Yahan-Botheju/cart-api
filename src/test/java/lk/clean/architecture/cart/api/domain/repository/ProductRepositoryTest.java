package lk.clean.architecture.cart.api.domain.repository;

import lk.clean.architecture.cart.api._domain.models.Product;
import lk.clean.architecture.cart.api._domain.repositories.ProductRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class ProductRepositoryTest implements ProductRepository {

    Map<UUID, Product> products = new HashMap<>();

    @Override
    public Optional<Product> findById(UUID productId) {
        return Optional.ofNullable(products.get(productId));
    }

    //initiate method for add values into map
    public Product addProduct(Product product) {
        products.put(product.getProductId(), product);
        return product;
    }
}
