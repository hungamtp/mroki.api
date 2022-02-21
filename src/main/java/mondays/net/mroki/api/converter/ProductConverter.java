package mondays.net.mroki.api.converter;

import lombok.AllArgsConstructor;
import mondays.net.mroki.api.dto.PageDTO;
import mondays.net.mroki.api.dto.productDTO.*;
import mondays.net.mroki.api.entity.Category;
import mondays.net.mroki.api.entity.Rate;
import mondays.net.mroki.api.entity.Product;
import mondays.net.mroki.api.entity.ProductImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ProductConverter {

    private SizeConverter sizeConverter;

    public ProductDTO entityToDto(Product product) {
        ProductDTO dto = ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .thumbnail(product.getProductImage().getThumbnail())
                .price(product.getPrice())
                .build();

        dto.setSizes(sizeConverter.entityToDTO(product.getSize()));

        float rate = 0;

        for (Rate c : product.getRates())
            rate += (float) c.getRate();

        dto.setRate(rate / (float) product.getRates().size());

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
                .retailPrice(dto.getRetail())
                .description(dto.getDescription())
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
                .retailPrice(dto.getRetail())
                .description(dto.getDescription())
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




    public ProductDetailDTO entityToProductDetailDto(Product product) {


        var result = ProductDetailDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .thumbnail(product.getProductImage().getThumbnail())
                .retail(product.getPrice())

                .categoryId(product.getCategory().getName())
                .image1(product.getProductImage().getImage1())
                .image2(product.getProductImage().getImage2())
                .build();

        if(product.getDiscount() != null){
             result.setSaleOff(product.getDiscount().getSaleOff());
             return result;
        }
        return  result;
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


    public ProductAdminDTO entityToAddDto(Product product) {
        var result = ProductAdminDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .retail(product.getRetailPrice())
                .description(product.getDescription())
                .categoryName(product.getCategory().getName())
                .thumbnail(product.getProductImage().getThumbnail())
                .image1(product.getProductImage().getImage1())
                .image2(product.getProductImage().getImage2())
                .createdDate(product.getCreatedDate())
                .modifiedDate(product.getModifiedDate())
                .deleted(product.isDelete())
                .build();
        if(product.getDiscount() != null){
            result.setSaleOff(product.getDiscount().getSaleOff());
            return result;
        }
        return  result;
    }
    //    public ProductDetailDTO entityToProductDetailDTO(Product product){
//        float averageRate = 0;
//        int rate1 =0 , rate2=0 , rate3=0 , rate4=0 , rate5=0;
//
//        for(Comment c : product.getComments()){
//            averageRate += c.getRate();
//            switch (c.getRate()){
//                case 1:
//                    rate1 ++;
//                    break;
//                case 2:
//                    rate2 ++;
//                    break;
//                case 3:
//                    rate3 ++;
//                    break;
//                case 4:
//                    rate4 ++;
//                    break;
//                case 5:
//                    rate5 ++;
//                    break;
//            }
//        }
//
//        return ProductDetailDTO.builder()
//                .id(product.getId())
//                .name(product.getName())
//                .thumbnail(product.getProductImage().getThumbnail())
//                .retail(product.getPrice())
//                .saleOff(product.getSaleOff())
//                .categoryId(product.getCategory().getName())
//                .image1(product.getProductImage().getImage1())
//                .image2(product.getProductImage().getImage2())
//                .averageRate(averageRate/(float) product.getComments().size())
//                .rate1(rate1)
//                .rate1(rate2)
//                .rate1(rate3)
//                .rate1(rate4)
//                .rate1(rate5)
//                .totalRate(product.getComments().size())
//                .sizes(product.getSize())
//                .build();
//
//    }
}
