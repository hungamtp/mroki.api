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

    private String phoneNumber;

    private String customerName;

    private String address;

    @Column(nullable = false)
    private double totalBill;

    @ManyToOne
    private Customer customer;

    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetails;

    public Orders(Long id) {
        this.id = id;
    }
}
