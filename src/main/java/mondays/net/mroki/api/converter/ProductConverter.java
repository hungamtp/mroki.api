package mondays.net.mroki.api.converter;

import mondays.net.mroki.api.dto.product.ProductAddDTO;
import mondays.net.mroki.api.dto.product.ProductCartDTO;
import mondays.net.mroki.api.dto.product.ProductDTO;
import mondays.net.mroki.api.dto.product.ProductDetailDTO;
import mondays.net.mroki.api.entity.Category;
import mondays.net.mroki.api.entity.Product;
import mondays.net.mroki.api.entity.ProductImage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductConverter {

    public ProductDTO entityToDto(Product product) {
        ProductDTO dto = ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .rate(product.getRate())
                .thumbnail(product.getProductImage().getThumbnail())
                .price(product.getPrice())
                .build();

        return dto;
    }

    public List<ProductDTO> entityToDto(List<Product> products) {

        return products.stream()
                .map(product -> entityToDto(product))
                .collect(Collectors.toList());
    }

    public Product addDtoToEntity(ProductAddDTO dto) {

        ProductImage productImage = ProductImage.builder().
                thumbnail(dto.getThumbnail()).
                image1(dto.getImage1()).
                image2(dto.getImage2()).
                build();

        Product product = Product.builder().
                id(dto.getId()).
                name(dto.getName()).
                price(dto.getPrice()).
                retail(dto.getRetail()).
                description(dto.getDescription()).
                saleOff(dto.getSaleOff()).
                category(Category.builder().id(dto.getCategoryId()).build()).
                productImage(productImage).
                build();
        return product;
    }

    public ProductDetailDTO entityToDetailDto(Product product) {

        return ProductDetailDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .retail(product.getRetail())
                .description(product.getDescription())
                .saleOff(product.getSaleOff())
                .rate(product.getRate())
                .categoryId(product.getCategory().getId())
                .thumbnail(product.getProductImage().getThumbnail())
                .image1(product.getProductImage().getImage1())
                .image2(product.getProductImage().getImage2())
                .build();
    }

    public List<ProductDTO> dataToDto(List<Object[]> data) {

        List<ProductDTO> result = new ArrayList<>();

        data.stream().forEach((product) -> {

            Long id = ((BigInteger) product[0]).longValue();
            String name = (String) product[1];
            String thumbnail = (String) product[2];
            float price = (float) product[3];
            int quantity = (int) product[4];
            float rate = ((BigDecimal) product[5]).floatValue();

            result.add(new ProductDTO(id, name, rate, thumbnail, price, quantity));
        });

        return result;

    }

    public List<ProductCartDTO> dataToCartDto(List<Object[]> data) {

        List<ProductCartDTO> result = new ArrayList<>();

        data.stream().forEach((product) -> {

            Long id = ((BigInteger) product[0]).longValue();
            String name = (String) product[1];
            String thumbnail = (String) product[2];
            float price = (float) product[3];
            int quantity = (int) product[4];
            int size = (int) product[5];
            float rate = ((BigDecimal) product[6]).floatValue();

            result.add(new ProductCartDTO(id, name, rate, thumbnail, price, quantity , size));
        });

        return result;

    }

    public Page<ProductDTO> pageEntityToPage(Page<Product> products){

        System.out.println(products.getTotalPages());//3
        List<ProductDTO> productDTOS=products.stream()
                .map((product -> entityToDto(product)))
                .collect(Collectors.toList());

        Page<ProductDTO> result = new PageImpl<>(productDTOS , products.getPageable() , products.getSize());
        System.out.println(result.getTotalPages()); // 2
        return result;
    }

    public List<ProductDTO> pageEntityToList(Page<Product> products){


        Pageable pageable = products.getPageable();
        List<ProductDTO> productDTOS=products.stream()
                .map((product -> entityToDto(product)))
                .collect(Collectors.toList());


        return productDTOS;
    }

    public ProductDTO dataToProductDto(Object[] data){
        return ProductDTO.builder()
                .id(((BigInteger) data[0]).longValue())
                .name((String) data[1])
                .thumbnail((String) data[2])
                .price((float) data[3])
                .rate(data[4]==null ?0:(float) data[4])
                .build();
    }

    public Page<ProductDTO> dataPageToPageDto(Page<Object[]> data){

        List<ProductDTO> list = data.stream().map((product) ->dataToProductDto(product))
                .collect(Collectors.toList());

        return new PageImpl<>(list , data.getPageable() , list.size());
    }


}
