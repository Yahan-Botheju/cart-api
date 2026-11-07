package lk.clean.architecture.cart.api.application.records.results;

import java.math.BigDecimal;

public record MoneyResult(
        BigDecimal amount,
        String currency
) {
}
