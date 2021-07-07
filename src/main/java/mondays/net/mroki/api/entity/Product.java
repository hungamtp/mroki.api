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
@Builder
@JsonIgnoreProperties({"comments" , "order" ,"createdDate" , "isDelete" , "modifiedDate" , "shoppingCarts" , "cart" , "isDelete" })
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
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

    @ManyToMany(mappedBy = "product")
    private List<Cart> cart;

    @OneToOne
    private PRNew prNew;

    public Product(Long id, String name, float price, float retail,
                   int quantity, int saleOff, boolean isDelete,
                   LocalDate createdDate, LocalDate modifiedDate,
                   ProductImage productImage, Category category,
                   List<Orders> order, List<Comment> comments, List<Cart> cart, PRNew prNew) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.retail = retail;
        this.quantity = quantity;
        this.saleOff = saleOff;
        this.isDelete = isDelete;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.productImage = productImage;
        this.category = category;
        this.order = order;
        this.comments = comments;
        this.cart = cart;
        this.prNew = prNew;
    }

}
