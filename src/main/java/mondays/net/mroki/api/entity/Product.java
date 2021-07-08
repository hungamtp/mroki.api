package mondays.net.mroki.api.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties({"comments" , "order" ,"createdDate" , "isDelete" , "modifiedDate" , "shoppingCarts" , "cart" , "isDelete" })
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String description;
    private float price;
    private float retail;
    private int quantity;
    private int saleOff;
    private boolean isDelete;

    @CreatedDate
    private LocalDate createdDate;

    @LastModifiedDate
    private LocalDate modifiedDate;

    @Embedded
    private ProductImage productImage;

    @ManyToOne
    private Category category;

    @ManyToMany
    private List<Orders> order;

    @OneToMany(mappedBy = "product")
    private List<Comment> comments;

    @OneToOne
    private PRNew prNew;


}
