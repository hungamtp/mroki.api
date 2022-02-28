package mondays.net.mroki.api.dto.customerDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mondays.net.mroki.api.dto.productDTO.ProductAddToCartDTO;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerOrderDTO {
    private String phoneNumber;

    private String customerName;

    private String address;

    private List<ProductAddToCartDTO> cart;
}
