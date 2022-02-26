package mondays.net.mroki.api.controller.admin;

import lombok.AllArgsConstructor;
import mondays.net.mroki.api.converter.SizeConverter;
import mondays.net.mroki.api.dto.ResponseDTO;
import mondays.net.mroki.api.dto.sizeDTO.AddSizeDTO;
import mondays.net.mroki.api.dto.sizeDTO.UpdateSizeDTO;
import mondays.net.mroki.api.dto.sizeDTO.SizeUpdateDTO;
import mondays.net.mroki.api.exception.SizeConvertException;
import mondays.net.mroki.api.responseCode.ErrorCode;
import mondays.net.mroki.api.responseCode.SuccessCode;
import mondays.net.mroki.api.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin/size")
@AllArgsConstructor
@CrossOrigin
@PreAuthorize("hasRole('Admin')")
public class SizeAdminController {

    @Qualifier("sizeServiceImpl")
    @Autowired
    private SizeService service;

    @Autowired
    private SizeConverter converter;

    @GetMapping("/{productId}")
    public ResponseEntity<ResponseDTO> getSizeByProduct(@PathVariable Long productId) {
        ResponseDTO response = new ResponseDTO();

        try {
            List<SizeUpdateDTO> sizeDto = converter.dtoToEntity(service.findByProduct(productId));
            response.setSuccessCode(SuccessCode.GET_SIZE_SUCCESS);
            response.setData(sizeDto);
            return ResponseEntity.ok().body(response);
        } catch (SizeConvertException ex) {
            response.setErrorCode(ErrorCode.GET_SIZE_FAIL);
            return ResponseEntity.badRequest().body(response);
        }

    }

    @PatchMapping
    public ResponseEntity<ResponseDTO> updateSizeQuantity(@RequestBody UpdateSizeDTO updateSizeDTO){
        ResponseDTO response = new ResponseDTO();
        try {
            service.updateSize(updateSizeDTO);
            response.setSuccessCode(SuccessCode.UPDATE_SIZE);
            return ResponseEntity.ok().body(response);
        } catch (Exception ex) {
            response.setErrorCode(ErrorCode.UPDATE_SIZE);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> addSize(@RequestBody AddSizeDTO addSizeDTO){
        ResponseDTO response = new ResponseDTO();
        try {
            service.addSize(addSizeDTO);
            response.setSuccessCode(SuccessCode.ADD_SIZE);
            return ResponseEntity.ok().body(response);
        } catch (Exception ex) {
            response.setErrorCode(ex.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }


}
