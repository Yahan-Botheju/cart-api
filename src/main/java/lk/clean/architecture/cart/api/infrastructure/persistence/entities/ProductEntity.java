package lk.clean.architecture.cart.api.infrastructure.persistence.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Check;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "product")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Check(constraints = "price_amount > 0")
public class ProductEntity {
    @Id
    @Column(name = "product_id", nullable = false)
    private UUID productId;
    @Column(name = "product_name", nullable = false)
    private String productName;
    @Column(name = "product_description")
    private String productDescription;
    @Column(name = "price_amount", nullable = false)
    private BigDecimal priceAmount;
    @Column(name = "price_currency", nullable = false)
    private String priceCurrency;
    @Column(name = "available", nullable = false)
    private boolean available;
}
