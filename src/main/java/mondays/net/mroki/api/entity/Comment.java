package mondays.net.mroki.api.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "comment" ,
        indexes = @Index(columnList = "product_id")
)
public class Comment {

    @Id
    @GeneratedValue
    private Long id;

    private String content;

    @Column(nullable = false)
    private int rate;

    @ManyToOne(cascade = CascadeType.REMOVE , fetch = FetchType.LAZY)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

}
