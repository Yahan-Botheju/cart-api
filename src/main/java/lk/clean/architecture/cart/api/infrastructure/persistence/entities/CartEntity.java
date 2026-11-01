package lk.clean.architecture.cart.api.infrastructure.persistence.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "cart")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartEntity {
    @Id
    @Column(name = "cart_id", nullable = false, unique = true)
    private UUID cartId;
    @Column(name = "currency")
    private String currency;
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @OneToMany(
            mappedBy = "cart",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true)
    private List<CartItemEntity> cartItems;

    void addCartItem(CartItemEntity cartItem) {
        this.cartItems.add(cartItem);
        cartItem.setCart(this);
    }
    void removeCartItem(CartItemEntity cartItem) {
        this.cartItems.remove(cartItem);
        cartItem.setCart(null);
    }
}
