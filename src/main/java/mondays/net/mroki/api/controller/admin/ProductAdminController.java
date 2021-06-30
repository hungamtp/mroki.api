package mondays.net.mroki.api.controller.admin;

import lombok.AllArgsConstructor;
import mondays.net.mroki.api.entity.Product;
import mondays.net.mroki.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin")
@AllArgsConstructor
public class ProductAdminController {

    @Autowired
    private final ProductService productService;

    @PostMapping
    public void addProduct(@RequestBody Product product){
        productService.save(product);
    }

    @PutMapping("/{id}")
    public void updateProduct(@RequestBody Product product){
        productService.save((product));
    }


    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProductById(id);
    }
}
