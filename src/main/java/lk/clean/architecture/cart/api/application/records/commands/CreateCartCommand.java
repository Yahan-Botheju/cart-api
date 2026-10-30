package lk.clean.architecture.cart.api.application.records.commands;

public record CreateCartCommand(String currency) {

    public CreateCartCommand {

        if (currency == null || currency.isBlank()) {
            throw new IllegalArgumentException("Currency cannot be blank");
        }
    }
}
