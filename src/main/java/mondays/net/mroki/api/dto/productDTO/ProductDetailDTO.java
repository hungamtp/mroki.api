package mondays.net.mroki.api.dto.productDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mondays.net.mroki.api.dto.sizeDTO.SizeDTO;
import mondays.net.mroki.api.entity.Size;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductDetailDTO {

    private Long id;
    private String name;
    private float price;
    private String description;
    private int saleOff;
    private String categoryId;
    private String thumbnail;
    private String image1;
    private String image2;
    private List<SizeDTO> sizes;
//    private int totalRate;
//    private float averageRate;
//    private int rate1;
//    private int rate2;
//    private int rate3;
//    private int rate4;
//    private int rate5;


}
