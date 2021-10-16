package mondays.net.mroki.api.entity;


import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.ArrayList;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "cart" ,
        indexes = @Index(columnList = "customer_id")
)
public class Cart {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;


    @OneToMany(mappedBy = "product")
    private List<CartDetail> cartDetails;
}
