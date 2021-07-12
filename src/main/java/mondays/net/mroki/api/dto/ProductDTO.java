package mondays.net.mroki.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


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
    private int quantity;

    public ProductDTO(Long id, String name, float rate, String thumbnail, float price) {
        this.id = id;
        this.name = name;
        this.rate = rate;
        this.thumbnail = thumbnail;
        this.price = price;
    }
}
