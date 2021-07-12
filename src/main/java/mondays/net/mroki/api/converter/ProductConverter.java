package mondays.net.mroki.api.converter;

import mondays.net.mroki.api.dto.ProductDTO;
import mondays.net.mroki.api.entity.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductConverter {

    public ProductDTO entityToDto(Product product){
        ProductDTO dto = ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .rate(product.getRate())
                .thumbnail(product.getProductImage().getThumbnail())
                .price(product.getPrice())
                .build();

        return dto;
    }

    public List<ProductDTO> entityToDto(List<Product> products){

        return products.stream().map(product -> entityToDto(product)).collect(Collectors.toList());
    }


}
