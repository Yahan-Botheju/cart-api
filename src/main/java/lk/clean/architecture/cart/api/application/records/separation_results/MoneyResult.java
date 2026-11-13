package lk.clean.architecture.cart.api.application.records.separation_results;

import java.math.BigDecimal;

public record MoneyResult(
        BigDecimal amount,
        String currency
) {
}
