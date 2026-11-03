package lk.clean.architecture.cart.api.infrastructure.persistence;

import lk.clean.architecture.cart.api._domain.models.Cart;
import lk.clean.architecture.cart.api._domain.repositories.CartRepository;
import lk.clean.architecture.cart.api._domain.repositories.ProductRepository;
import lk.clean.architecture.cart.api.infrastructure.persistence.jpa.CartJpaRepository;
import lk.clean.architecture.cart.api.infrastructure.persistence.persistenceMappers.PersistenceMapper;

import java.util.Optional;
import java.util.UUID;

public class CartRepositoryImpl implements CartRepository {

    //inject required dependencies
    private final CartJpaRepository cartJpaRepository;
    private final PersistenceMapper persistenceMapper;
    private final ProductRepository productRepository;

    public CartRepositoryImpl(
            CartJpaRepository cartJpaRepository,
            PersistenceMapper persistenceMapper,
            ProductRepository productRepository
    ) {
        this.cartJpaRepository = cartJpaRepository;
        this.persistenceMapper = persistenceMapper;
        this.productRepository = productRepository;
    }

    //cart find by id
    @Override
    public Optional<Cart> findCartById(UUID cartId) {
        return cartJpaRepository.findById(cartId)
                .map(persistenceMapper::toDomainModel);
    }

    @Override
    public Cart save(Cart cart) {
        return null;
    }
}
