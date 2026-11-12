package lk.clean.architecture.cart.api.application.records.cart.results.separation_records;


import java.util.UUID;

public record CartItemSnapshotResult(
        UUID productId,
        String productName,
        String productDescription,
        int quantity,
        MoneyResult unitPrice,
        boolean isAvailable,
        MoneyResult subTotal
) {
}
