package lk.clean.architecture.cart.api.infrastructure.persistence;

import lk.clean.architecture.cart.api._domain.models.*;
import lk.clean.architecture.cart.api._domain.repositories.CartRepository;
import lk.clean.architecture.cart.api._domain.repositories.ProductRepository;
import lk.clean.architecture.cart.api.application.exception.ProductNotFoundException;
import lk.clean.architecture.cart.api.infrastructure.persistence.entities.CartEntity;
import lk.clean.architecture.cart.api.infrastructure.persistence.jpa.CartJpaRepository;
import lk.clean.architecture.cart.api.infrastructure.persistence.persistenceMappers.PersistenceMapper;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class CartRepositoryImpl implements CartRepository {

    //inject required dependencies
    private final CartJpaRepository cartJpaRepository;
    private final PersistenceMapper persistenceMapper;
    private final ProductRepository productRepository;

    public CartRepositoryImpl(
            CartJpaRepository cartJpaRepository,
            PersistenceMapper persistenceMapper,
            ProductRepository productRepository
    ) {
        this.cartJpaRepository = cartJpaRepository;
        this.persistenceMapper = persistenceMapper;
        this.productRepository = productRepository;
    }

    //cart find by id
    @Override
    public Optional<Cart> findCartById(UUID cartId) {
        //get entity
        Optional<CartEntity> cartEntity = cartJpaRepository.findById(cartId);
        //if entity unavailable return empty
        if(cartEntity.isEmpty()){
            return Optional.empty();
        }
        //get entity
        CartEntity getCartItem = cartEntity.get();
        //stream each item
        List<CartItem> cartItems = getCartItem.getCartItems().stream()
                .map(cartItemEntity -> {
                    //find product
                    Product Products = productRepository.findById(cartItemEntity.getProductId())
                            .orElseThrow(() -> new ProductNotFoundException("Product not found"));
                    //create domain model and create list
                    return new  CartItem(Products, cartItemEntity.getQuantity());
                }).toList();
        //turn to domain model
        Cart cart = persistenceMapper.toDomainModel(getCartItem, cartItems);

        return Optional.of(cart);
    }

    //save cart
    @Override
    public Cart save(Cart cart) {

        CartEntity cartEntity = persistenceMapper.toCartEntity(cart);
        CartEntity savedCartEntity = cartJpaRepository.save(cartEntity);

        List<CartItem> cartItems = savedCartEntity.getCartItems().stream()
                .map(cartItemEntity -> {
                    Product product = productRepository.findById(cartItemEntity.getProductId())
                            .orElseThrow(() -> new ProductNotFoundException("Product not found"));

                    return new  CartItem(product, cartItemEntity.getQuantity());
                }).toList();

        return persistenceMapper.toDomainModel(savedCartEntity, cartItems);
    }
}
