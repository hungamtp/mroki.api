package mondays.net.mroki.api.controller.admin;

import lombok.AllArgsConstructor;
import mondays.net.mroki.api.converter.ProductConverter;
import mondays.net.mroki.api.dto.ResponseDTO;
import mondays.net.mroki.api.dto.productDTO.ProductAddDTO;
import mondays.net.mroki.api.dto.productDTO.ProductUpdateDTO;
import mondays.net.mroki.api.entity.Product;
import mondays.net.mroki.api.exception.DuplicatedDataException;
import mondays.net.mroki.api.exception.ProductConvertException;
import mondays.net.mroki.api.filter.ProductSpecificationsBuilder;
import mondays.net.mroki.api.responseCode.ErrorCode;
import mondays.net.mroki.api.responseCode.SuccessCode;
import mondays.net.mroki.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("admin/product")
@AllArgsConstructor
@CrossOrigin
@PreAuthorize("hasRole('Admin')")
public class ProductAdminController {

    private final ProductService productService;
    private final ProductConverter converter;

    @GetMapping
    public ResponseEntity<ResponseDTO> getAllProduct(@RequestParam Integer page,
                                                     @RequestParam Integer size,
                                                     @RequestParam String sort,
                                                     @RequestParam String search) {
        ResponseDTO response = new ResponseDTO();

        if (!Optional.ofNullable(sort).isPresent()) sort = "id";
        Pageable pageable = PageRequest.of(Optional.ofNullable(page).orElse(0), size, Sort.by(sort));

        ProductSpecificationsBuilder builder = new ProductSpecificationsBuilder();
        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
        Matcher matcher = pattern.matcher(search + ",");
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
        }

        Specification<Product> spec = builder.build();

        response.setData(productService.findAllProductAdmin(pageable , spec));

        return ResponseEntity.ok().body(response);
    }


    @PostMapping
    public ResponseEntity<ResponseDTO> addProduct(@Valid @RequestBody ProductAddDTO productDTO) {

        ResponseDTO response = new ResponseDTO();

        try {

            productService.save(converter.addDtoToEntity(productDTO));
            response.setSuccessCode(SuccessCode.SAVE_PRODUCT_SUCCESS);

            return ResponseEntity.ok().body(response);
        } catch (ProductConvertException ex) {

            response.setErrorCode(ErrorCode.SAVE_PRODUCT_FAIL);
            return ResponseEntity.ok().body(response);
        }

    }

    @PutMapping()
    public ResponseEntity<ResponseDTO> updateProduct(@Valid @RequestBody ProductUpdateDTO product) {

        ResponseDTO response = new ResponseDTO();

        productService.updateProduct(product);
        response.setSuccessCode(SuccessCode.UPDATE_PRODUCT_SUCCESS);
        return ResponseEntity.ok().body(response);


    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deleteProduct(@PathVariable Long id) {

        ResponseDTO response = new ResponseDTO();

        if (productService.isExist(id)) {

            productService.deleteProductById(id);
            response.setSuccessCode(SuccessCode.DELETE_PRODUCT_SUCCESS);
            return ResponseEntity.ok().body(response);

        } else {
            response.setErrorCode(ErrorCode.PRODUCT_NOT_FOUND);
            return ResponseEntity.badRequest().body(response);

        }

    }
}
