package mondays.net.mroki.api.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "category" ,
        indexes = @Index(columnList = "id, name")
)
public class Category {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private boolean isDeleted;


    @OneToMany(mappedBy = "category" , fetch = FetchType.LAZY)
    private List<Product> products;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.REMOVE, orphanRemoval = true , fetch = FetchType.EAGER)
    private List<Category> subCategories = new ArrayList<Category>();

    public Category(Long id) {
        this.id = id;
    }
}
