package mondays.net.mroki.api.controller.admin;

import lombok.AllArgsConstructor;
import mondays.net.mroki.api.dto.ProductDTO;
import mondays.net.mroki.api.dto.ProductAddDTO;
import mondays.net.mroki.api.entity.Category;
import mondays.net.mroki.api.entity.Product;
import mondays.net.mroki.api.entity.ProductImage;
import mondays.net.mroki.api.service.impl.ProductServiceImpl;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("admin/product")
@AllArgsConstructor
@CrossOrigin
public class ProductAdminController {

    @Autowired
    private final ProductServiceImpl productService;

    @PostMapping
    public void addProduct(@Valid @RequestBody ProductAddDTO productDTO) {

        ProductImage productImage = ProductImage.builder().
                                    thumbnail(productDTO.getThumbnail()).
                                    image1(productDTO.getImage1()).
                                    image2(productDTO.getImage2()).
                                    build();

        Product product = Product.builder().
                name(productDTO.getName()).
                price(productDTO.getPrice()).
                retail(productDTO.getRetail()).
                description(productDTO.getDescription()).
                quantity(productDTO.getQuantity()).
                saleOff(productDTO.getSaleOff()).
                category(Category.builder().id(productDTO.getCategoryId()).build()).
                productImage(productImage).
                build();

        productService.save(product);
    }

    @PutMapping("/{id}")
    public void updateProduct(@PathVariable Long id , @Valid @RequestBody ProductAddDTO productDTO) {

        if (!Optional.ofNullable(id).isPresent())
            throw new IllegalIdentifierException("Null id");

        ProductImage productImage = ProductImage.builder().
                thumbnail(productDTO.getThumbnail()).
                image1(productDTO.getImage1()).
                image2(productDTO.getImage2()).
                build();

        Product product = Product.builder().
                id(id).
                name(productDTO.getName()).
                price(productDTO.getPrice()).
                retail(productDTO.getRetail()).
                description(productDTO.getDescription()).
                quantity(productDTO.getQuantity()).
                saleOff(productDTO.getSaleOff()).
                category(Category.builder().id(productDTO.getCategoryId()).build()).
                productImage(productImage).
                build();

        productService.updateProduct(product);
    }


    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProductById(id);
    }
}
