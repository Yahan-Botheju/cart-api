package lk.clean.architecture.cart.api.infrastructure.persistence.persistenceMappers;

import lk.clean.architecture.cart.api._domain.models.Cart;
import lk.clean.architecture.cart.api._domain.models.CartItem;
import lk.clean.architecture.cart.api._domain.models.Money;
import lk.clean.architecture.cart.api._domain.models.Product;
import lk.clean.architecture.cart.api.infrastructure.persistence.entities.CartEntity;
import lk.clean.architecture.cart.api.infrastructure.persistence.entities.CartItemEntity;
import lk.clean.architecture.cart.api.infrastructure.persistence.entities.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PersistenceMapper {

    /* __CART__ */

    //domain model to entity
    CartEntity toCartEntity(Cart cart);

    //entity to domain model
    Cart toDomainModel(CartEntity cartEntity);

    /* __CART_ITEM__ */

    //domain to entity
    @Mapping(target = "cartItemId", ignore = true)
    @Mapping(source = "cartItem.product.productId", target = "productId")
    @Mapping(source = "cartItem.quantity", target = "quantity")
    @Mapping(target = "cart", ignore = true)
    CartItemEntity toCartItemEntity(CartItem cartItem);

    //entity to domain model
    CartItem toDomainModel(CartItemEntity cartItemEntity);

    /* __PRODUCT__ */

    //domain to entity
    @Mapping(source = "productId", target = "productId")
    @Mapping(source = "productName", target = "productName")
    @Mapping(source = "productDescription", target = "productDescription")
    @Mapping(source = "productPrice.amount", target = "priceAmount")
    @Mapping(source = "productPrice.currency", target = "priceCurrency")
    @Mapping(source = "productAvailable", target = "available")
    ProductEntity toProductEntity(Product product);

    //entity to domain model
    default Product toDomainModel(ProductEntity productEntity){
        if(productEntity == null){
            return null;
        }
        Money money = new Money(
                productEntity.getPriceAmount(),
                productEntity.getPriceCurrency()
        );
        return Product.reconstruct(
                productEntity.getProductId(),
                productEntity.getProductName(),
                productEntity.getProductDescription(),
                money,
                productEntity.isAvailable()
        );
    }


}
