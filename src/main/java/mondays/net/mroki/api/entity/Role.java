package mondays.net.mroki.api.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Role {

    @Id
    @Column(length = 1)
    private String id;

    private String role_name;

    @OneToMany(mappedBy = "role")
    private List<Customer> customers;


}
