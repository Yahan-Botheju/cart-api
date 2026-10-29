package lk.clean.architecture.cart.api.application.port;

import lk.clean.architecture.cart.api._domain.models.Product;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepository {

    //get product by id
    Optional<Product> findById(UUID productId);
}
