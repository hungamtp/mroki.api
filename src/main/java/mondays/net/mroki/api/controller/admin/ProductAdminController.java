package mondays.net.mroki.api.controller.admin;

import lombok.AllArgsConstructor;
import mondays.net.mroki.api.entity.Product;
import mondays.net.mroki.api.service.impl.ProductServiceImpl;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("admin/product")
@AllArgsConstructor
@CrossOrigin
public class ProductAdminController {

    @Autowired
    private final ProductServiceImpl productService;

    @PostMapping
    public void addProduct(@RequestBody Product product){
        productService.save(product);
    }

    @PutMapping
    public void updateProduct(@RequestBody Product product){

        if(!Optional.ofNullable(product.getId()).isPresent())
            throw new IllegalIdentifierException("Null id");

        productService.updateProduct(product);
    }


    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProductById(id);
    }
}
