package mondays.net.mroki.api.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "product" ,
        indexes = @Index(columnList = "id")
)
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String description;
    private float price;
    private float retail;
    private int saleOff;
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

    @ManyToMany(fetch =FetchType.LAZY)
    private List<Orders> order;

    @OneToMany(mappedBy = "product" , fetch = FetchType.LAZY)
    private List<Comment> comments;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Cart> cart ;

    @OneToMany(mappedBy = "product" , fetch = FetchType.LAZY)
    private List<Size> size;

}
