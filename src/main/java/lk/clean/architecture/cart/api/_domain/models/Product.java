package lk.clean.architecture.cart.api._domain.models;

import java.math.BigDecimal;
import java.util.UUID;

public class Product {
    private final UUID productId;
    private String productName;
    private String productDescription;
    private BigDecimal productPrice;
    private boolean isProductAvailable;

    /* __CONSTRUCTOR__ */

    public Product( String productName, String productDescription, BigDecimal productPrice) {
        this.productId = UUID.randomUUID();
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.isProductAvailable = true;
    }

    /* __GETTERS__ */

    public UUID getProductId() { return productId; }
    public String getProductName() { return productName; }
    public String getProductDescription() { return productDescription; }
    public BigDecimal getProductPrice() { return productPrice; }
    public boolean isProductAvailable() { return isProductAvailable; }


}
