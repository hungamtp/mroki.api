package mondays.net.mroki.api.controller.customer;


import lombok.AllArgsConstructor;
import mondays.net.mroki.api.converter.ProductConverter;
import mondays.net.mroki.api.dto.ResponseDTO;
import mondays.net.mroki.api.dto.product.ProductDTO;
import mondays.net.mroki.api.dto.product.ProductDetailDTO;
import mondays.net.mroki.api.dto.product.SortDTO;
import mondays.net.mroki.api.dto.product.SortType;
import mondays.net.mroki.api.entity.Product;
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
import java.util.List;
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
                                                @RequestBody(required = false) SortDTO sortDTO) {

        ResponseDTO response = new ResponseDTO();
        try {
            Pageable pageable;

            if (Optional.ofNullable(sortDTO).isPresent()) {
                pageable = PageRequest.of(Optional.ofNullable(page).orElse(0), PAGE_SIZE, Sort.by(sortDTO.getSortType().toLowerCase()));
            } else {
                pageable = PageRequest.of(Optional.ofNullable(page).orElse(0), PAGE_SIZE, Sort.by("id"));
            }

            response.setData(converter.pageEntityToPage(productService.findAllProduct(pageable)));
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
            if (productService.isExist(id)) {

                ProductDetailDTO productDTO = productService.getProductById(id);
                response.setSuccessCode(SuccessCode.GET_PRODUCT_DETAIL.toString());
                response.setData(productDTO);

                return ResponseEntity.ok().body(response);
            } else {

                response.setErrorCode(ErrorCode.PRODUCT_NOT_FOUND.toString());
                return ResponseEntity.badRequest().body(response);
            }

        } catch (Exception ex) {
            response.setErrorCode(ErrorCode.GET_PRODUCT_DETAIL.toString());
            return ResponseEntity.badRequest().body(response);
        }

    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<ResponseDTO> getProductByCategory(@PathVariable String categoryId,
                                                            @RequestParam(required = false) Optional<Integer> page) {

        ResponseDTO response = new ResponseDTO();
        try {
            Pageable pageable = PageRequest.of(page.orElse(0), PAGE_SIZE);
            Page<Product> products = productService.getProductByCategory(categoryId, pageable);
            List<ProductDTO> productDTOS = converter.pageEntityToList(products);

            response.setData(productDTOS);
            response.setSuccessCode(SuccessCode.GET_PRODUCT_BY_CATEGORY.toString());
            return ResponseEntity.ok().body(response);
        } catch (ProductConvertException ex) {

            response.setErrorCode(ErrorCode.GET_PRODUCT_BY_CATEGORY.toString());
            return ResponseEntity.badRequest().body(response);
        }

    }

    @GetMapping("/name")
    public Page<Product> findByName(@RequestParam String name,
                                    @RequestParam(required = false) Optional<Integer> page) {
        return productService.getProductByName(name, page.orElse(0));
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
