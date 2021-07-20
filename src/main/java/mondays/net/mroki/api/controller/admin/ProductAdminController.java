package mondays.net.mroki.api.controller.admin;

import lombok.AllArgsConstructor;
import mondays.net.mroki.api.converter.ProductConverter;
import mondays.net.mroki.api.dto.product.ProductAddDTO;
import mondays.net.mroki.api.dto.ResponseDTO;
import mondays.net.mroki.api.exception.ProductConvertException;
import mondays.net.mroki.api.responseCode.ErrorCode;
import mondays.net.mroki.api.responseCode.SuccessCode;
import mondays.net.mroki.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final ProductService productService;

    @Autowired
    private final ProductConverter converter;

    @PostMapping
    public ResponseEntity<ResponseDTO> addProduct(@Valid @RequestBody ProductAddDTO productDTO) {

        ResponseDTO response = new ResponseDTO();

        try {

            productService.save(converter.addDtoToEntity(productDTO));
            response.setSuccessCode(SuccessCode.SAVE_PRODUCT.toString());

            return ResponseEntity.ok().body(response);
        } catch (ProductConvertException ex) {

            response.setErrorCode(ErrorCode.SAVE_PRODUCT.toString());
            return ResponseEntity.ok().body(response);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductAddDTO productDTO) {

        ResponseDTO response = new ResponseDTO();

        if (!Optional.ofNullable(id).isPresent()) {
            response.setErrorCode(ErrorCode.PRODUCT_NOT_FOUND.toString());
            return ResponseEntity.badRequest().body(response);
        }

        if (!productService.isExist(id)) {
            response.setErrorCode(ErrorCode.PRODUCT_NOT_FOUND.toString());
            return ResponseEntity.badRequest().body(response);
        }

        productDTO.setId(id);
        productService.updateProduct(converter.addDtoToEntity(productDTO));
        response.setSuccessCode(SuccessCode.UPDATE_PRODUCT.toString());
        return ResponseEntity.ok().body(response);


    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deleteProduct(@PathVariable Long id) {

        ResponseDTO response = new ResponseDTO();

        if (productService.isExist(id)) {

            response.setErrorCode(ErrorCode.PRODUCT_NOT_FOUND.toString());
            return ResponseEntity.badRequest().body(response);
        } else {

            productService.deleteProductById(id);
            response.setSuccessCode(SuccessCode.DELETE_PRODUCT.toString());
            return ResponseEntity.ok().body(response);
        }


    }
}
