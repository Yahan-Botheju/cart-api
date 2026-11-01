package lk.clean.architecture.cart.api.infrastructure.persistence.jpa;

import lk.clean.architecture.cart.api.infrastructure.persistence.entities.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CartJpaRepository extends JpaRepository<CartEntity, UUID> {

}
