package mondays.net.mroki.api.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"product" })
public class Comment {

    @Id
    @GeneratedValue
    private Long id;

    private String content;

    @Column(nullable = false)
    private int rate;

    @ManyToOne(cascade = CascadeType.REMOVE)
    private Product product;

    @ManyToOne
    private Customer customer;

}
