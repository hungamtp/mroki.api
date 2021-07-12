package mondays.net.mroki.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties({"password", "email", "phone", "isVerifiedEmail", "role", "comment", "orders", "shoppingCart"})
public class Customer {

    @Id
    @GeneratedValue
    private Long id;


    private String username;

    private String password;

    private String avatar;

    private String email;

    private String phone;

    private boolean isVerifiedEmail;

    @ManyToOne
    private Role role;

    @OneToMany(mappedBy = "customer")
    private List<Orders> orders;

    @OneToMany(mappedBy = "customer")
    private List<Comment> comment;

    @OneToOne
    private PRNew prNew;

    public Customer(Long id) {
        this.id = id;
    }

}
