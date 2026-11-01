package lk.clean.architecture.cart.api.infrastructure.persistence.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "cartItem")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartItemEntity {
    @Id
    @Column(name = "cartItem_id", nullable = false, unique = true)
    private UUID cartItemId;
    @Column(name = "product_id", nullable = false)
    private UUID productId;
    @Column(name = "quantity", nullable = false)
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "cart_id",  nullable = false)
    private CartEntity cart;
}
