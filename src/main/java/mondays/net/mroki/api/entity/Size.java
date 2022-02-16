package mondays.net.mroki.api.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Size {

    @Id
    @GeneratedValue
    private  Long id;

    private String size;
    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

}
