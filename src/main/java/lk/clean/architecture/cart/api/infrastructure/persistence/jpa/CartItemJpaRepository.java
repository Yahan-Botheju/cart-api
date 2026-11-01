package lk.clean.architecture.cart.api.infrastructure.persistence.jpa;

import lk.clean.architecture.cart.api.infrastructure.persistence.entities.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CartItemJpaRepository extends JpaRepository<CartItemEntity, UUID> {
}
