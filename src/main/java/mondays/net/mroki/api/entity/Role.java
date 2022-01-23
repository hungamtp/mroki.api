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
public class Role {

    @Id
    @GeneratedValue
    private Long id;

    private String roleName;

    @OneToMany(mappedBy = "role" , fetch = FetchType.LAZY)
    private List<Customer> customers;

}
