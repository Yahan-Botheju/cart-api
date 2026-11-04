package lk.clean.architecture.cart.api.infrastructure.persistence;

import lk.clean.architecture.cart.api._domain.models.Product;
import lk.clean.architecture.cart.api._domain.repositories.ProductRepository;
import lk.clean.architecture.cart.api.infrastructure.persistence.entities.ProductEntity;
import lk.clean.architecture.cart.api.infrastructure.persistence.jpa.ProductJpaRepository;
import lk.clean.architecture.cart.api.infrastructure.persistence.persistenceMappers.PersistenceMapper;

import java.util.Optional;
import java.util.UUID;

public class ProductRepositoryImpl implements ProductRepository {

    //inject required dependencies
    private final ProductJpaRepository productJpaRepository;
    private final PersistenceMapper persistenceMapper;

    public ProductRepositoryImpl(ProductJpaRepository productJpaRepository, PersistenceMapper persistenceMapper) {
        this.productJpaRepository = productJpaRepository;
        this.persistenceMapper = persistenceMapper;
    }

    @Override
    public Optional<Product> findById(UUID productId) {
        Optional<ProductEntity>  productEntity = productJpaRepository.findById(productId);
        if(productEntity.isEmpty()) {
            return Optional.empty();
        }
        Product product = persistenceMapper.toProductDomainModel(productEntity.get());

        return Optional.of(product);
    }
}
