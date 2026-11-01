package lk.clean.architecture.cart.api.infrastructure.persistence.jpa;

import lk.clean.architecture.cart.api.infrastructure.persistence.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductJpaRepository extends JpaRepository<ProductEntity, UUID> {
}
