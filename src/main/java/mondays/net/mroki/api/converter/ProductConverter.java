package mondays.net.mroki.api.converter;

import mondays.net.mroki.api.dto.PageDTO;
import mondays.net.mroki.api.dto.productDTO.*;
import mondays.net.mroki.api.entity.Category;
import mondays.net.mroki.api.entity.Comment;
import mondays.net.mroki.api.entity.Product;
import mondays.net.mroki.api.entity.ProductImage;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductConverter {

    public ProductDTO entityToDto(Product product) {
        ProductDTO dto = ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .thumbnail(product.getProductImage().getThumbnail())
                .price(product.getPrice())
                .build();

        float rate = 0;

        for (Comment c : product.getComments())
            rate += (float) c.getRate();

        dto.setRate(rate / (float) product.getComments().size());

        return dto;
    }

    public PageDTO entityToProductHomePageDTO(Page<Product> products) {

        List<ProductDTO> dto = products.stream()
                .map((product -> entityToDto(product)))
                .collect(Collectors.toList());

        return  PageDTO.builder()
                .totalPage(products.getTotalPages())
                .totalElement(products.getTotalElements())
                .data(dto)
                .build();
    }

    public Product addDtoToEntity(ProductAddDTO dto) {

        ProductImage productImage = ProductImage.builder().
                thumbnail(dto.getThumbnail()).
                image1(dto.getImage1()).
                image2(dto.getImage2()).
                build();

        Product product = Product.builder()
                .name(dto.getName())
                .price(dto.getPrice())
                .retail(dto.getRetail())
                .description(dto.getDescription())
                .saleOff(dto.getSaleOff())
                .category(Category.builder().id(dto.getCategoryId()).build())
                .productImage(productImage)
                .createdDate(LocalDate.now())
                .build();
        return product;
    }

    public Product updateDtoToEntity(ProductUpdateDTO dto) {

        ProductImage productImage = ProductImage.builder().
                thumbnail(dto.getThumbnail()).
                image1(dto.getImage1()).
                image2(dto.getImage2()).
                build();

        Product product = Product.builder()
                .id(dto.getId())
                .name(dto.getName())
                .price(dto.getPrice())
                .retail(dto.getRetail())
                .description(dto.getDescription())
                .saleOff(dto.getSaleOff())
                .category(Category.builder().id(dto.getCategoryId()).build())
                .productImage(productImage)
                .createdDate(dto.getCreateDate())
                .modifiedDate(LocalDate.now())
                .build();
        return product;
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

            result.add(new ProductCartDTO(id, name, rate, thumbnail, price, quantity, size));
        });

        return result;

    }


    public ProductDTO dataToProductDto(Object[] data) {
        return ProductDTO.builder()
                .id(((BigInteger) data[0]).longValue())
                .name((String) data[1])
                .thumbnail((String) data[2])
                .price((float) data[3])
                .rate(data[4] == null ? 0 : ((BigDecimal) data[4]).floatValue())
                .build();
    }

    public PageDTO dataPageToPageDto(Page<Object[]> data) {

        List<ProductDTO> list = data.stream().map((product) -> dataToProductDto(product))
                .collect(Collectors.toList());

        return PageDTO.builder()
                .data(list)
                .totalPage(data.getTotalPages())
                .totalElement(data.getTotalElements())
                .build();
    }

    public ProductDetailDTO entityToProductDetailDto(Product product) {


        return ProductDetailDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .thumbnail(product.getProductImage().getThumbnail())
                .retail(product.getPrice())
                .saleOff(product.getSaleOff())
                .categoryId(product.getCategory().getName())
                .image1(product.getProductImage().getImage1())
                .image2(product.getProductImage().getImage2())
                .build();
    }

    public ProductAdminDTO entityToAddDto(Product product) {
        return ProductAdminDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .retail(product.getRetail())
                .description(product.getDescription())
                .saleOff(product.getSaleOff())
                .categoryName(product.getCategory().getName())
                .thumbnail(product.getProductImage().getThumbnail())
                .image1(product.getProductImage().getImage1())
                .image2(product.getProductImage().getImage2())
                .createdDate(product.getCreatedDate())
                .modifiedDate(product.getModifiedDate())
                .deleted(product.isDelete())
                .build();
    }

    public PageDTO entityToPageAddDto(Page<Product> products) {
        List<ProductAdminDTO> list = products.stream()
                .map((product) -> entityToAddDto(product))
                .collect(Collectors.toList());
        return PageDTO.builder()
                .totalElement(products.getTotalElements())
                .totalPage(products.getTotalPages())
                .data(list)
                .build();
    }


}
