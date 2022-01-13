package mondays.net.mroki.api.entity;


import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Orders {

    @Id
    @GeneratedValue
    private Long id;

    @CreatedDate
    private LocalDate createdDate;

    private boolean isReceived;

    private boolean isShipping;

    private boolean isInArchiveBox;

    @ManyToOne
    private Customer customer;

    @OneToOne
    private ArchiveBox archiveBox;

    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetails;

}
