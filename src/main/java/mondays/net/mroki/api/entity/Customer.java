package mondays.net.mroki.api.entity;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "customer" ,
        indexes = @Index(columnList = "id, username , email , phone")
)
public class Customer {

    @Id
    @GeneratedValue
    private Long id;


    private String username;

    private String password;

    private String avatar;

    private String email;

    private String phone;

    @Column(nullable = false)
    private boolean isActive;

    private boolean isVerifiedEmail;

    @ManyToOne(fetch = FetchType.EAGER)
    private Role role;

    @OneToMany(mappedBy = "customer" , fetch = FetchType.LAZY)
    private List<Orders> orders;

    @OneToMany(mappedBy = "customer" , fetch = FetchType.LAZY)
    private List<Rate> rate;


    @OneToOne(mappedBy = "customer" ,fetch = FetchType.LAZY)
    private Cart cart;

    public Customer(Long id) {
        this.id = id;
    }

}
