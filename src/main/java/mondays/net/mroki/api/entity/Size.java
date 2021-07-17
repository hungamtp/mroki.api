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
public class Size {

    @Id
    @GeneratedValue
    private  Long id;

    private int size;
    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

}
