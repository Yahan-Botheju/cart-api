package lk.clean.architecture.cart.api._domain.models;

import java.math.BigDecimal;

public record Money(BigDecimal amount, String currency) {

    public Money{
        if(amount == null || amount.compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("Invalid Money Amount");
        }
        if(currency == null || currency.trim().isEmpty()){
            throw new IllegalArgumentException("Invalid Money Currency");
        }
    }
}
