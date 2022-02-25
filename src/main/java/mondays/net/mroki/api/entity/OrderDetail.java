package mondays.net.mroki.api.entity;

import lombok.*;
import org.hibernate.criterion.Order;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDetail {

    @Id
    @GeneratedValue
    private Long id;

    private int quantity;
    private String size;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Orders order;
}
