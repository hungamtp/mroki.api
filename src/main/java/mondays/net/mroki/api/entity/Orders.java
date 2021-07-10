package mondays.net.mroki.api.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Orders {

    @Id
    @GeneratedValue
    private Long id;

    @CreatedDate
    private LocalDate createdDate;

    private boolean isReceived;

    private boolean isShipping;

    private boolean isInArchiveBox;

    @ManyToOne
    @JsonIgnore
    private Customer customer;

    @ManyToMany
    private List<Product> product;

    @OneToOne
    private ArchiveBox archiveBox;

}
