package mondays.net.mroki.api.controller.admin;

import lombok.AllArgsConstructor;
import mondays.net.mroki.api.converter.ProductConverter;
import mondays.net.mroki.api.dto.ProductAddDTO;
import mondays.net.mroki.api.entity.Product;
import mondays.net.mroki.api.exception.ProductConvertException;
import mondays.net.mroki.api.service.impl.ProductServiceImpl;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    private final ProductConverter converter;

    @PostMapping
    public ResponseEntity<String> addProduct(@Valid @RequestBody ProductAddDTO productDTO) {

        try{
            productService.save(converter.addDtoToEntity(productDTO));
            return ResponseEntity.ok().body("ADD_PRODUCT_SUCCESSFULLY");
        }catch (ProductConvertException ex){
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ADD_PRODUCT_FAIL");
        }

    }

    @PutMapping("/{id}")
    public void updateProduct(@PathVariable Long id , @Valid @RequestBody ProductAddDTO productDTO) {

        if (!Optional.ofNullable(id).isPresent())
            throw new IllegalIdentifierException("Null id");

        productDTO.setId(id);
       Product product = converter.addDtoToEntity(productDTO);

        productService.updateProduct(product);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {

        if(!Optional.ofNullable(id).isPresent()){
            return ResponseEntity.badRequest().body("ID_NULL");
        }
        else{
            productService.deleteProductById(id);
            return ResponseEntity.ok().body("DELETE_SUCCESSFULLY");
        }

    }
}
