package lk.clean.architecture.cart.api.application.records.results.separation_records;

import java.math.BigDecimal;

public record MoneyResult(
        BigDecimal amount,
        String currency
) {
}
