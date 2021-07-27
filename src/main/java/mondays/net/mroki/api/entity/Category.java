package mondays.net.mroki.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category {

    @Id
    private String id;

    private String name;

    private boolean delete;


    @OneToMany(mappedBy = "category" , fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Product> products;

    @OneToMany(cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Set<Category> subCategories = new HashSet<>();

    public Category(String id) {
        this.id = id;
    }
}
