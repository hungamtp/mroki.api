package mondays.net.mroki.api.service.impl;

import mondays.net.mroki.api.converter.ProductConverter;
import mondays.net.mroki.api.dto.PageDTO;
import mondays.net.mroki.api.dto.productDTO.ProductDetailDTO;
import mondays.net.mroki.api.entity.Category;
import mondays.net.mroki.api.entity.Product;
import mondays.net.mroki.api.entity.ProductImage;
import mondays.net.mroki.api.filter.ProductSpecification;
import mondays.net.mroki.api.filter.SearchCriteria;
import mondays.net.mroki.api.repository.ProductRepository;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class ProductServiceImplTest {



    @InjectMocks
    private ProductServiceImpl service;

    @Mock
    private ProductRepository repo;

    @Mock
    private ProductConverter converter;



    @Test
    void save() {
    }

    @Test
    void getAllProductByPage() {
        List<Product> products = new ArrayList<>();
        products.add(Product.builder()
                .id(1L).name("p1")
                .rate(4)
                .productImage(ProductImage.builder().thumbnail("thumbnail").build())
                .build());
        products.add(Product.builder()
                .id(2L).name("p2")
                .rate(4)
                .productImage(ProductImage.builder().thumbnail("thumbnail").build())
                .build());
        products.add(Product.builder()
                .id(3L).name("p3")
                .rate(4)
                .productImage(ProductImage.builder().thumbnail("thumbnail").build())
                .build());
        products.add(Product.builder()
                .id(4L).name("p4")
                .rate(4)
                .productImage(ProductImage.builder().thumbnail("thumbnail").build())
                .build());
        Page<Product> pageProduct = new PageImpl<>(products , PageRequest.of(0,4 ),4);
        PageDTO page = converter.entityToProductHomePageDTO(pageProduct);

        SearchCriteria criteria = new SearchCriteria("price150" , ":" , "200");

        ProductSpecification spec = new ProductSpecification(criteria);

        Pageable pageable =PageRequest.of(0 , 4);

        when(repo.findAll(spec,pageable )).thenReturn( pageProduct);

        assertEquals(service.findAllProduct(pageable, spec) , page);


    }


    @Test
    public void getProductByID(){


        Product product = Product.builder()
                .id(5L)
                .name("product")
                .productImage(ProductImage.builder().thumbnail("thumbnail").image2("image2").image1("image1").build())
                .retail(12)
                .saleOff(12)
                .isDelete(false)
                .category(new Category("G"))
                .build();

        ProductDetailDTO dto = converter.entityToProductDetailDto(product);

        when(repo.findById(5L)).thenReturn(Optional.of(product));

        ProductDetailDTO product1 = service.getProductById(5L);

        assertEquals(product1 , dto);
    }


}