package mondays.net.mroki.api.controller.customer;


import lombok.AllArgsConstructor;
import mondays.net.mroki.api.entity.Product;
import mondays.net.mroki.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("user/product")
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




}
