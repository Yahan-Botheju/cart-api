package lk.clean.architecture.cart.api.infrastructure.persistence.configs;

import lk.clean.architecture.cart.api._domain.repositories.CartRepository;
import lk.clean.architecture.cart.api._domain.repositories.ProductRepository;
import lk.clean.architecture.cart.api.infrastructure.persistence.CartRepositoryImpl;
import lk.clean.architecture.cart.api.infrastructure.persistence.jpa.CartJpaRepository;
import lk.clean.architecture.cart.api.infrastructure.persistence.persistenceMappers.PersistenceMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersistenceBeanConfigs {

    // cart repository
    @Bean
    public CartRepository cartRepository(
            CartJpaRepository cartJpaRepository,
            PersistenceMapper persistenceMapper,
            ProductRepository productRepository
    ) {
        return new CartRepositoryImpl(cartJpaRepository, persistenceMapper, productRepository);
    }
}
