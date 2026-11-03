package lk.clean.architecture.cart.api.infrastructure.persistence;

import lk.clean.architecture.cart.api._domain.repositories.ProductRepository;
import lk.clean.architecture.cart.api.infrastructure.persistence.persistenceMappers.PersistenceMapper;

public class ProductRepositoryImpl implements ProductRepository {

    //inject required dependencies
    private final ProductRepository productRepository;
    private final PersistenceMapper persistenceMapper;

    public ProductRepositoryImpl(ProductRepository productRepository, PersistenceMapper persistenceMapper) {
        this.productRepository = productRepository;
        this.persistenceMapper = persistenceMapper;
    }
}
