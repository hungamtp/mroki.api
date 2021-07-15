package mondays.net.mroki.api.controller.customer;


import lombok.AllArgsConstructor;
import mondays.net.mroki.api.dto.ProductDTO;
import mondays.net.mroki.api.dto.ProductDetailDTO;
import mondays.net.mroki.api.dto.ResponseDTO;
import mondays.net.mroki.api.entity.Product;
import mondays.net.mroki.api.responseCode.ErrorCode;
import mondays.net.mroki.api.responseCode.SuccessCode;
import mondays.net.mroki.api.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("user/product")
@AllArgsConstructor
@CrossOrigin
public class ProductController {

    @Autowired
    private final ProductServiceImpl productService;

    @GetMapping
    public ResponseEntity<ResponseDTO> homePage(@RequestParam(required = false) Optional<Integer> page) {

        ResponseDTO response = new ResponseDTO();
        try{
            response.setSuccessCode(SuccessCode.GET_PRODUCT);
            response.setData(productService.getAllProductByPage(page.orElse(0)));
            return ResponseEntity.ok().body(response);
        }catch (Exception ex){
            response.setErrorCode(ErrorCode.GET_PRODUCT);

            return ResponseEntity.badRequest().body(response);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getProductById(@PathVariable Long id) {

        ResponseDTO response = new ResponseDTO();

        try{
            if(productService.isExist(id)){

                ProductDetailDTO productDTO =productService.getProductById(id);
                response.setSuccessCode(SuccessCode.GET_PRODUCT_DETAIL);
                response.setData(productDTO);

                return ResponseEntity.ok().body(response);
            }
            else{

                response.setErrorCode(ErrorCode.PRODUCT_NOT_FOUND);
                return ResponseEntity.badRequest().body(response);
            }

        }catch (Exception ex){
            response.setErrorCode(ErrorCode.GET_PRODUCT_DETAIL);
            return ResponseEntity.badRequest().body(response);
        }

    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<ResponseDTO> getProductByCategory(@PathVariable String categoryId, @RequestParam(required = false) Optional<Integer> page) {

        ResponseDTO response = new ResponseDTO();
        try{

            List<ProductDTO> result= productService.getProductByCategory(categoryId, page.orElse(0));
            response.setSuccessCode(SuccessCode.GET_PRODUCT_BY_CATEGORY);
            response.setData(result);

            return ResponseEntity.ok().body(response);
        }catch (Exception ex){

            response.setErrorCode(ErrorCode.GET_PRODUCT_BY_CATEGORY);

            return ResponseEntity.badRequest().body(response);
        }

    }

    @GetMapping("/name")
    public Page<Product> findByName(@RequestParam String name,
                                    @RequestParam(required = false) Optional<Integer> page) {
        return productService.getProductByName(name, page.orElse(0));
    }


}
