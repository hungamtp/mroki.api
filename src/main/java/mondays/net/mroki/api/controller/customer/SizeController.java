package mondays.net.mroki.api.controller.customer;

import lombok.AllArgsConstructor;
import mondays.net.mroki.api.converter.SizeConverter;
import mondays.net.mroki.api.dto.size.SizeDTO;
import mondays.net.mroki.api.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user/size")
@AllArgsConstructor
@CrossOrigin
public class SizeController {

    @Autowired
    private final SizeService service;

    @Autowired
    private final SizeConverter converter;


    @GetMapping("/{productId}")
    public List<SizeDTO> getSize(@PathVariable Long productId){
        return converter.entityToDTO(service.findByProduct(productId));
    }
}
