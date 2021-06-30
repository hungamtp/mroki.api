package mondays.net.mroki.api.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductImage {

    private String thumbnail;
    private String image1;
    private String image2;


}
