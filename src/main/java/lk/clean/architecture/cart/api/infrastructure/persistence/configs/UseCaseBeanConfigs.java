package lk.clean.architecture.cart.api.infrastructure.persistence.configs;

import lk.clean.architecture.cart.api._domain.repositories.CartRepository;
import lk.clean.architecture.cart.api._domain.repositories.ProductRepository;
import lk.clean.architecture.cart.api.application.usecase.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseBeanConfigs {

    //add to cart usecase
    @Bean
    public AddItemToCartUseCase  addItemToCartUseCase(
            CartRepository cartRepository,
            ProductRepository productRepository
    ) {
        return new AddItemToCartUseCaseImpl(cartRepository, productRepository);
    }

    //create cart
    @Bean
    public CreateCartUseCase createCartUseCase(CartRepository cartRepository) {
        return new CreateCartUseCaseImpl(cartRepository);
    }

    //update cart
    @Bean
    public UpdateCartItemQuantityUseCase updateCartItemQuantityUseCase(CartRepository  cartRepository) {
        return new UpdateCartItemQuantityUseCaseImpl(cartRepository);
    }

    //remove cart
    @Bean
    public RemoveItemFromCartUseCase removeItemFromCartUseCase(CartRepository cartRepository) {
        return new RemoveItemFromCartUseCaseImpl(cartRepository);
    }

    //clear cart
    @Bean
    public ClearCartUseCase clearCartUseCase(CartRepository cartRepository) {
        return new ClearCartUseCaseImpl(cartRepository);
    }
}
