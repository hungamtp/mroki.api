package mondays.net.mroki.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    @JsonIgnoreProperties({"username" ,"avatar" , "verifiedEmail"})
    @OneToOne
    private Customer customer;

    @JsonIgnoreProperties({"category"})
    @ManyToMany
    private List<Product> product;
}
