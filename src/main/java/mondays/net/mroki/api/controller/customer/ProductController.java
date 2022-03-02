package mondays.net.mroki.api.controller.customer;


import lombok.AllArgsConstructor;
import mondays.net.mroki.api.converter.ProductConverter;
import mondays.net.mroki.api.converter.SizeConverter;
import mondays.net.mroki.api.dto.ResponseDTO;
import mondays.net.mroki.api.dto.productDTO.ProductDTO;
import mondays.net.mroki.api.dto.productDTO.ProductDetailDTO;
import mondays.net.mroki.api.dto.productDTO.SortType;
import mondays.net.mroki.api.entity.Product;
import mondays.net.mroki.api.exception.ProductConvertException;
import mondays.net.mroki.api.filter.ProductSpecification;
import mondays.net.mroki.api.filter.ProductSpecificationsBuilder;
import mondays.net.mroki.api.responseCode.ErrorCode;
import mondays.net.mroki.api.responseCode.SuccessCode;
import mondays.net.mroki.api.service.ProductService;
import mondays.net.mroki.api.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.EnumSet;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("user/product")
@AllArgsConstructor
@CrossOrigin
public class ProductController {

    private final ProductService productService;
    private final ProductConverter converter;
    private final SizeService service;
    private final SizeConverter sizeConverter;

    @GetMapping
    public ResponseEntity<ResponseDTO> getAllProduct(@RequestParam Integer page,
                                                     @RequestParam Integer size,
                                                     @RequestParam String sort,
                                                     @RequestParam String search) {

        ResponseDTO response = new ResponseDTO();
        try {
            Pageable pageable;


            pageable = PageRequest.of(Optional.ofNullable(page).orElse(0),
                    Optional.ofNullable(size).orElse(9),
                    Sort.by(Optional.ofNullable(sort).isPresent() ?sort : "id"));

            ProductSpecificationsBuilder builder = new ProductSpecificationsBuilder();
            Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
            Matcher matcher = pattern.matcher(search + ",");
            while (matcher.find()) {
                builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
            }

            Specification<Product> spec = builder.build();


            response.setData(productService.findAllProduct(pageable , spec));

            response.setSuccessCode(SuccessCode.GET_PRODUCT_SUCCESS);
            return ResponseEntity.ok().body(response);
        } catch (ProductConvertException ex) {
            response.setErrorCode(ErrorCode.GET_PRODUCT_FAIL);
            return ResponseEntity.badRequest().body(response);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getProductById(@PathVariable Long id) {

        ResponseDTO response = new ResponseDTO();

        try {

            ProductDetailDTO productDTO = productService.getProductById(id);
            productDTO.setSizes(sizeConverter.entityToDTO(service.findByProduct(id)));
            response.setSuccessCode(SuccessCode.GET_PRODUCT_DETAIL_SUCCESS);
            response.setData(productDTO);

            return ResponseEntity.ok().body(response);

        } catch (Exception ex) {
            response.setErrorCode(ex.getMessage());
            return ResponseEntity.badRequest().body(response);
        }

    }




}
