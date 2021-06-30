package mondays.net.mroki.api.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"comments" , "orders" ,"createdDate" , "isDelete" , "modifiedDate" })
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private float price;
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
    private List<Orders> orders;

    @OneToMany(mappedBy = "product")
    private List<Comment> comments;

    public Product(Long id){
        this.id = id;
    }

}
