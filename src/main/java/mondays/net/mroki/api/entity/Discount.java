package mondays.net.mroki.api.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Discount {

    @Id
    @GeneratedValue
    private Long id;

    private int saleOff;
    private LocalDate startDate;
    private LocalDate endDate;

    @OneToMany(mappedBy = "discount")
    private List<Product> products;
}
