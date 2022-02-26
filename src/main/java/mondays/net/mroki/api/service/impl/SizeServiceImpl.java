package mondays.net.mroki.api.service.impl;

import lombok.AllArgsConstructor;
import mondays.net.mroki.api.dto.sizeDTO.AddSizeDTO;
import mondays.net.mroki.api.entity.Product;
import mondays.net.mroki.api.entity.Size;
import mondays.net.mroki.api.repository.SizeRepository;
import mondays.net.mroki.api.responseCode.ErrorCode;
import mondays.net.mroki.api.service.SizeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SizeServiceImpl implements SizeService {

    final SizeRepository sizeRepository;


    public List<Size> findByProduct(Long productId){

       return sizeRepository.findByProduct(Product.builder().id(productId).build());

    }

    @Override
    public void updateSize(AddSizeDTO addSizeDTO) throws Exception {
        Size size = sizeRepository.findBySizeAndProduct(addSizeDTO.getSize(), new Product(addSizeDTO.getProductId()))
                .orElseThrow(() ->
                         new Exception(ErrorCode.SIZE_NOT_FOUND));

        size.setQuantity(addSizeDTO.getQuantity());
        sizeRepository.save(size);
    }
}
