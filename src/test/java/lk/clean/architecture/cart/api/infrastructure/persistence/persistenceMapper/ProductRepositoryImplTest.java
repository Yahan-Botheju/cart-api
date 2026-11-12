package lk.clean.architecture.cart.api.infrastructure.persistence.persistenceMapper;

import lk.clean.architecture.cart.api._domain.repositories.ProductRepository;
import lk.clean.architecture.cart.api.infrastructure.persistence.ProductRepositoryImpl;
import lk.clean.architecture.cart.api.infrastructure.persistence.jpa.ProductJpaRepository;
import lk.clean.architecture.cart.api.infrastructure.persistence.persistenceMappers.PersistenceMapper;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class ProductRepositoryImplTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductJpaRepository productJpaRepository;

    @Mock
    private PersistenceMapper persistenceMapper;

    @InjectMocks
    private ProductRepositoryImpl productRepositoryImpl;
}
