package mondays.net.mroki.api.controller;


import lombok.AllArgsConstructor;
import mondays.net.mroki.api.entity.Product;
import mondays.net.mroki.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("product")
@AllArgsConstructor
public class ProductController {


    @Autowired
    private final ProductService productService;


    @GetMapping
    public Page<Product> homePage(@RequestParam(required = false) Optional<Integer> page){

        return productService.findAllProduct(page.orElse(0));
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) throws Exception {
        return productService.getProductById(id);
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
