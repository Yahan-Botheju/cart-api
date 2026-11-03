package lk.clean.architecture.cart.api.infrastructure.persistence;

import lk.clean.architecture.cart.api.infrastructure.persistence.jpa.CartJpaRepository;
import lk.clean.architecture.cart.api.infrastructure.persistence.persistenceMappers.PersistenceMapper;

public class CartRepositoryImpl {

    private final CartJpaRepository cartJpaRepository;
    private final PersistenceMapper persistenceMapper;

    public CartRepositoryImpl(CartJpaRepository cartJpaRepository, PersistenceMapper persistenceMapper) {
        this.cartJpaRepository = cartJpaRepository;
        this.persistenceMapper = persistenceMapper;
    }
}
