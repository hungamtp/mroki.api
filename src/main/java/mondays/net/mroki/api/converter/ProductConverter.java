package mondays.net.mroki.api.converter;

import mondays.net.mroki.api.dto.ProductAddDTO;
import mondays.net.mroki.api.dto.ProductDTO;
import mondays.net.mroki.api.entity.Category;
import mondays.net.mroki.api.entity.Product;
import mondays.net.mroki.api.entity.ProductImage;
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
    
    public Product addDtoToEntity(ProductAddDTO dto){

        ProductImage productImage = ProductImage.builder().
                thumbnail(dto.getThumbnail()).
                image1(dto.getImage1()).
                image2(dto.getImage2()).
                build();

        Product product = Product.builder().
                name(dto.getName()).
                price(dto.getPrice()).
                retail(dto.getRetail()).
                description(dto.getDescription()).
                quantity(dto.getQuantity()).
                saleOff(dto.getSaleOff()).
                category(Category.builder().id(dto.getCategoryId()).build()).
                productImage(productImage).
                build();
        return product;
    }


}
