package mondays.net.mroki.api.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartDetail {

    @Id
    @GeneratedValue
    private Long id;

    private int quantity;

    private int size;

    @ManyToOne
    @JoinTable(name="product_cartdetail")
    private Product product;

    @ManyToOne
    @JoinTable(name="cartdetailt_cart")
    private Cart cart;
}
