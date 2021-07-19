package mondays.net.mroki.api.controller.admin;

import lombok.AllArgsConstructor;
import mondays.net.mroki.api.converter.SizeConverter;
import mondays.net.mroki.api.dto.ResponseDTO;
import mondays.net.mroki.api.dto.size.SizeUpdateDTO;
import mondays.net.mroki.api.exception.SizeConvertException;
import mondays.net.mroki.api.responseCode.ErrorCode;
import mondays.net.mroki.api.responseCode.SuccessCode;
import mondays.net.mroki.api.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin/size")
@AllArgsConstructor
@CrossOrigin
public class SizeAdminController {

    @Autowired
    private final SizeService service;

    @Autowired
    private final SizeConverter converter;

    @GetMapping("/{productId}")
    public ResponseEntity<ResponseDTO> getSizeByProduct(@PathVariable Long productId) {
        ResponseDTO response = new ResponseDTO();

        try {
            List<SizeUpdateDTO> sizeDto = converter.dtoToEntity(service.findByProduct(productId));
            response.setSuccessCode(SuccessCode.GET_SIZE);
            response.setData(sizeDto);
            return ResponseEntity.ok().body(response);
        } catch (SizeConvertException ex) {
            response.setErrorCode(ErrorCode.GET_SIZE);
            return ResponseEntity.badRequest().body(response);
        }

    }


}
