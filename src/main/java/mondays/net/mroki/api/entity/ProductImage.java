package mondays.net.mroki.api.entity;


import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductImage {

    private String thumbnail;
    private String image1;
    private String image2;


}
