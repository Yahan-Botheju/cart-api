package lk.clean.architecture.cart.api.infrastructure.persistence;

import lk.clean.architecture.cart.api._domain.repositories.ProductRepository;
import lk.clean.architecture.cart.api.infrastructure.persistence.jpa.CartJpaRepository;
import lk.clean.architecture.cart.api.infrastructure.persistence.persistenceMappers.PersistenceMapper;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

public class CartRepositoryImplTest {

    @MockitoBean
    private CartJpaRepository cartJpaRepository;

    @MockitoBean
    private ProductRepository productRepository;

    @MockitoBean
    private PersistenceMapper persistenceMapper;


}
