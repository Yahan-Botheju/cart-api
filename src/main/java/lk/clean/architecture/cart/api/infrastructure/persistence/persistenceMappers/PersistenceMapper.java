package lk.clean.architecture.cart.api.infrastructure.persistence.persistenceMappers;

import lk.clean.architecture.cart.api._domain.models.Cart;
import lk.clean.architecture.cart.api._domain.models.CartItem;
import lk.clean.architecture.cart.api.infrastructure.persistence.entities.CartEntity;
import lk.clean.architecture.cart.api.infrastructure.persistence.entities.CartItemEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersistenceMapper {

    /* __CART__ */

    //domain model to entity
    CartEntity toCartEntity(Cart cart);

    //entity to domain model
    Cart toDomainModel(CartEntity cartEntity);

    /* __CART_ITEM__ */

    //domain to entity
    CartItemEntity toCartItemEntity(CartItem cartItem);

    //entity to domain model
    CartItem toDomainModel(CartItemEntity cartItemEntity);
}
