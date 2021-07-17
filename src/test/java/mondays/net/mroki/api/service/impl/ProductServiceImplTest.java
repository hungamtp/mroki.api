package mondays.net.mroki.api.service.impl;

import mondays.net.mroki.api.dto.ProductDTO;
import mondays.net.mroki.api.entity.Product;
import mondays.net.mroki.api.entity.ProductImage;
import mondays.net.mroki.api.repository.ProductRepository;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class ProductServiceImplTest {

    private static final int PAGE_SIZE = 4;

    @InjectMocks
    private ProductServiceImpl service;

    @Mock
    private ProductRepository repo;




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

        when((List<Product>)repo.findAllProduct(PageRequest.of(0 , 4) ).get()).thenReturn( products);


        assertEquals(service.findAllProduct(PageRequest.of(0 , 4)).getSize(), 4);
    }
}