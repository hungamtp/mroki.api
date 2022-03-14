package mondays.net.mroki.api.dto.productDTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductAdminDTO {
    private Long id;
    private String name;
    private Float price;
    private Float retail;
    private String description;
    private Integer saleOff;
    private LocalDate createdDate;
    private LocalDate modifiedDate;
    private String categoryName;
    private String thumbnail;
    private String image1;
    private String image2;
    private boolean deleted;
    private float rate;
}
