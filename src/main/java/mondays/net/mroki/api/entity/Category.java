package mondays.net.mroki.api.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category {

    @Id
    private String id;

    private String name;

    private boolean delete;

    @OneToMany(mappedBy = "category" , fetch = FetchType.LAZY)
    private List<Product> products;

}
