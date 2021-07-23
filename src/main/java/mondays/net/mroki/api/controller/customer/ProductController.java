package mondays.net.mroki.api.controller.customer;


import lombok.AllArgsConstructor;
import mondays.net.mroki.api.converter.ProductConverter;
import mondays.net.mroki.api.dto.ResponseDTO;
import mondays.net.mroki.api.dto.productDTO.ProductDTO;
import mondays.net.mroki.api.dto.productDTO.ProductDetailDTO;
import mondays.net.mroki.api.dto.productDTO.SortType;
import mondays.net.mroki.api.exception.ProductConvertException;
import mondays.net.mroki.api.responseCode.ErrorCode;
import mondays.net.mroki.api.responseCode.SuccessCode;
import mondays.net.mroki.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.EnumSet;
import java.util.Optional;

@RestController
@RequestMapping("user/product")
@AllArgsConstructor
@CrossOrigin
public class ProductController {

    private final int PAGE_SIZE = 9;
    @Autowired
    private final ProductService productService;

    @Autowired
    private final ProductConverter converter;

    @GetMapping
    public ResponseEntity<ResponseDTO> getAllProduct(@RequestParam(required = false) Integer page,
                                                     @RequestParam(required = false) Integer size,
                                                     @RequestBody(required = false) String sort) {

        ResponseDTO response = new ResponseDTO();
        try {
            Pageable pageable;


            pageable = PageRequest.of(Optional.ofNullable(page).orElse(0),
                    Optional.ofNullable(size).orElse(9),
                    Sort.by(Optional.ofNullable(sort).isPresent() ?sort : "id"));


            response.setData(productService.findAllProduct(pageable));
            response.setSuccessCode(SuccessCode.GET_PRODUCT.toString());
            return ResponseEntity.ok().body(response);
        } catch (ProductConvertException ex) {
            response.setErrorCode(ErrorCode.GET_PRODUCT.toString());
            return ResponseEntity.badRequest().body(response);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getProductById(@PathVariable Long id) {

        ResponseDTO response = new ResponseDTO();

        try {

            ProductDetailDTO productDTO = productService.getProductById(id);
            response.setSuccessCode(SuccessCode.GET_PRODUCT_DETAIL.toString());
            response.setData(productDTO);

            return ResponseEntity.ok().body(response);

        } catch (Exception ex) {
            response.setErrorCode(ErrorCode.GET_PRODUCT_DETAIL.toString());
            return ResponseEntity.badRequest().body(response);
        }

    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<ResponseDTO> getProductByCategory(@PathVariable String categoryId,
                                                            @RequestParam(required = false) Integer page,
                                                            @RequestParam(required = false) Integer size) {

        ResponseDTO response = new ResponseDTO();
        try {
            Pageable pageable = PageRequest.of(Optional.ofNullable(page).orElse(0), Optional.ofNullable(size).orElse(9));
            Page<ProductDTO> products = productService.getProductByCategory(categoryId, pageable);

            response.setData(products);
            response.setSuccessCode(SuccessCode.GET_PRODUCT_BY_CATEGORY.toString());
            return ResponseEntity.ok().body(response);
        } catch (ProductConvertException ex) {

            response.setErrorCode(ErrorCode.GET_PRODUCT_BY_CATEGORY.toString());
            return ResponseEntity.badRequest().body(response);
        }

    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ResponseDTO> findByName(@PathVariable String name,
                                                  @RequestParam(required = false) Optional<Integer> page) {
        ResponseDTO response = new ResponseDTO();
        response.setData(productService.getProductByName(name, page.orElse(0)));
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/sortType")
    public EnumSet<SortType> getSortType() {
        EnumSet<SortType> sortType = EnumSet.allOf(SortType.class);
        return sortType;
    }

    @GetMapping("/totalPage")
    public int getTotalPage() {

        int totalPage = productService.countTotalElement();

        return totalPage % PAGE_SIZE == 0 ? totalPage / PAGE_SIZE : totalPage / PAGE_SIZE + 1;
    }


}
