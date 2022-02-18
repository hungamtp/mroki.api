package mondays.net.mroki.api.dto.productDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mondays.net.mroki.api.dto.sizeDTO.SizeDTO;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductDTO{
    private Long id;
    private String name;
    private float rate;
    private String thumbnail;
    private float price;
    private List<SizeDTO> sizes;


}
