package mondays.net.mroki.api.entity;


import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "product" ,
        indexes = @Index(columnList = "id,name,price")
)
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String description;
    private float price;
    private float retailPrice;
    private boolean isDelete;

    @Transient
    private float rate;

    @CreatedDate
    private LocalDate createdDate;

    @LastModifiedDate
    private LocalDate modifiedDate;

    @Embedded
    private ProductImage productImage;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @OneToMany(mappedBy = "product" , fetch = FetchType.LAZY)
    private List<Rate> rates;

    @OneToMany(mappedBy = "product" , fetch = FetchType.LAZY)
    private List<Size> size;

    @OneToMany(mappedBy = "product")
    private List<CartDetail> cartDetails;

    @ManyToOne
    private Discount discount;

    @OneToMany(mappedBy = "product")
    private List<OrderDetail> orderDetails;

    public Product(Long id) {
        this.id = id;
    }
}
