package mondays.net.mroki.api.controller.admin;

import lombok.AllArgsConstructor;
import mondays.net.mroki.api.dto.ResponseDTO;
import mondays.net.mroki.api.dto.discountDTO.AddDiscountDTO;
import mondays.net.mroki.api.dto.discountDTO.AddDiscountForProductDTO;
import mondays.net.mroki.api.dto.productDTO.ProductAddDTO;
import mondays.net.mroki.api.exception.CategoryConverterException;
import mondays.net.mroki.api.exception.DataNotFoundException;
import mondays.net.mroki.api.responseCode.ErrorCode;
import mondays.net.mroki.api.responseCode.SuccessCode;
import mondays.net.mroki.api.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("admin/discount")
@AllArgsConstructor
@CrossOrigin
@PreAuthorize("hasRole('Admin')")
public class DiscountController {

    @Autowired
    private DiscountService discountService;

    @PostMapping
    public ResponseEntity<ResponseDTO> addDiscount(@RequestBody AddDiscountDTO addDiscountDTO){
        ResponseDTO response = new ResponseDTO();
        try {

            discountService.addDiscount(addDiscountDTO);
            response.setSuccessCode(SuccessCode.ADD_DISCOUNT);

        } catch (IllegalStateException ex) {
            response.setErrorCode(ex.getMessage());
        }

        return ResponseEntity.ok().body(response);
    }

    @GetMapping
    public ResponseEntity<ResponseDTO> getAllDiscount(){
        ResponseDTO response = new ResponseDTO();
            response.setData(discountService.getAllDiscount());
            response.setSuccessCode(SuccessCode.ADD_DISCOUNT);
            return ResponseEntity.ok().body(response);
    }

    @PutMapping
    public ResponseEntity<ResponseDTO> addDiscountForProduct(@RequestBody AddDiscountForProductDTO addDiscountForProductDTO){
        ResponseDTO response = new ResponseDTO();
        try{
        discountService.addDiscountForProduct(addDiscountForProductDTO);
        response.setSuccessCode(SuccessCode.ADD_DISCOUNT_FOR_PRODUCT);
        } catch (DataNotFoundException ex){
            response.setErrorCode(ex.getMessage());
        }
        return ResponseEntity.ok().body(response);
    }
}
