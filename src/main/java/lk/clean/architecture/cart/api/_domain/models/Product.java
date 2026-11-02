package lk.clean.architecture.cart.api._domain.models;

import java.math.BigDecimal;
import java.util.UUID;

public class Product {
    private final UUID  productId;
    private String productName;
    private String productDescription;
    private Money productPrice;
    private boolean isProductAvailable;

    /* __CONSTRUCTOR__ */

    public Product(String productName, String productDescription, Money productPrice) {
        this.productId = UUID.randomUUID();
        if (productName == null || productName.trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be null or empty");
        }
        if (productPrice == null || productPrice.amount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Product price cannot be null or negative");
        }
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.isProductAvailable = true;
    }

    /* __GETTERS__ */

    public UUID getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public Money getProductPrice() {
        return productPrice;
    }

    public boolean isProductAvailable() {
        return isProductAvailable;
    }

    /* __PRIVATE_CONSTRUCTOR__ */

    private Product(UUID productId, String productName, String productDescription, Money productPrice, boolean isProductAvailable) {
        if (productId == null){
            throw new IllegalArgumentException("Product id cannot be null");
        }
        if (productName == null || productName.trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be null or empty");
        }
        if (productPrice == null || productPrice.amount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Product price cannot be null or negative");
        }
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.isProductAvailable = isProductAvailable;
    }

    /* __FACTORY_METHOD__ */

    public static Product reconstruct(UUID productId, String productName, String productDescription, Money productPrice, boolean isProductAvailable){
         return new Product(productId, productName, productDescription, productPrice,  isProductAvailable);
    }

    /* __DOMAIN_LOGICS__ */

    //make product available
    public void setProductAvailable() {
        this.isProductAvailable = true;
    }

    //make product unavailable
    public void setProductUnavailable() {
        this.isProductAvailable = false;
    }


}
