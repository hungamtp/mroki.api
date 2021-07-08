package mondays.net.mroki.api.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PRNew {

    @Id
    @GeneratedValue
    private Long id;

    @CreatedDate
    private LocalDate publicDate;

    @OneToOne(mappedBy = "prNew")
    private Product product;

    @OneToOne(mappedBy = "prNew")
    private Customer customer;

}
