package mondays.net.mroki.api.controller.customer;


import lombok.AllArgsConstructor;
import mondays.net.mroki.api.entity.Product;
import mondays.net.mroki.api.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("user/product")
@AllArgsConstructor
@CrossOrigin
public class ProductController {

    @Autowired
    private final ProductServiceImpl productService;

    @GetMapping
    public Page<Product> homePage(@RequestParam(required = false) Optional<Integer> page){

        return productService.getAllProduct(page.orElse(0));
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) throws Exception {
        return productService.getProductById(id);
    }

    @GetMapping("/category/{categoryId}")
    public Page<Product> getProductByCategory(@PathVariable String categoryId , @RequestParam(required = false) Optional<Integer> page){

        return productService.getProductByCategory(categoryId ,page.orElse(0));
    }

    @GetMapping("/name")
    public Page<Product> findByName(@RequestParam String name , @RequestParam(required = false) Optional<Integer> page){
       return productService.getProductByName(name , page.orElse(0));
    }




}
