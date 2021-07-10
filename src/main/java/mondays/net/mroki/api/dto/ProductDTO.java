package mondays.net.mroki.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductDTO implements Serializable {
    private Long id;
    @NotBlank(message = "name is null")
    private String name;
    private float rate;
    @NotBlank(message = "thumbnail is null")
    private String thumbnail;
    @NotBlank(message = "price is null")
    private float price;
    @NotBlank
    private int quantity;

    public ProductDTO(Long id, String name, float rate, String thumbnail, float price) {
        this.id = id;
        this.name = name;
        this.rate = rate;
        this.thumbnail = thumbnail;
        this.price = price;
    }
}
