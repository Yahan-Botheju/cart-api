package lk.clean.architecture.cart.api.infrastructure.persistence.configs;

import lk.clean.architecture.cart.api._domain.repositories.CartRepository;
import lk.clean.architecture.cart.api._domain.repositories.ProductRepository;
import lk.clean.architecture.cart.api.application.usecase.AddItemToCartUseCase;
import lk.clean.architecture.cart.api.application.usecase.AddItemToCartUseCaseImpl;
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
}
