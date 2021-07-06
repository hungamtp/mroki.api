package mondays.net.mroki.api.controller.idol;

import lombok.AllArgsConstructor;
import mondays.net.mroki.api.entity.PRNew;
import mondays.net.mroki.api.service.impl.PRNewServiceImpl;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("idol")
@AllArgsConstructor
public class PRNewController {

    @Autowired
    private final PRNewServiceImpl prNewService;

    @PostMapping
    public void addPrNew(@RequestParam Long idolId , @RequestParam Long productId ) throws Exception {

        if(!Optional.ofNullable(productId).isPresent()){
            throw new Exception("productId is missing");
        }
        if(!Optional.ofNullable(idolId).isPresent()){
            throw new Exception("idolId is missing");
        }

        prNewService.addPrNew(idolId ,productId);

    }

//    @GetMapping
//    public Page<PRNew> getPrNew(@RequestParam(required = false) int page){
//        return prNewService.getAllPRNew(Optional.ofNullable(page).orElse(0));
//    }
}
