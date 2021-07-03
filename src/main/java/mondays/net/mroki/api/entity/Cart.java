package mondays.net.mroki.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {

    @Id
    @GeneratedValue
    private Long id;

    @JsonIgnore
    @OneToOne
    private Customer customer;

    @ManyToMany
    List<Product> product;
}
