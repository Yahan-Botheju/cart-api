package lk.clean.architecture.cart.api.infrastructure.persistence.persistenceMappers;

import lk.clean.architecture.cart.api._domain.models.Cart;
import lk.clean.architecture.cart.api.infrastructure.persistence.entities.CartEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersistenceMapper {

    /* __CART__ */

    //domain model to entity
    CartEntity toCartEntity(Cart cart);

    //entity to domain model
    Cart toDomainModel(CartEntity cartEntity);
}
