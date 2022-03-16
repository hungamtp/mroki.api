package mondays.net.mroki.api.service.impl;

import lombok.AllArgsConstructor;
import mondays.net.mroki.api.dto.sizeDTO.AddSizeDTO;
import mondays.net.mroki.api.dto.sizeDTO.UpdateSizeDTO;
import mondays.net.mroki.api.entity.Product;
import mondays.net.mroki.api.entity.Size;
import mondays.net.mroki.api.exception.DataNotFoundException;
import mondays.net.mroki.api.repository.SizeRepository;
import mondays.net.mroki.api.responseCode.ErrorCode;
import mondays.net.mroki.api.service.SizeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SizeServiceImpl implements SizeService {

    final SizeRepository sizeRepository;


    public List<Size> findByProduct(Long productId){

       return sizeRepository.findByProduct(Product.builder().id(productId).build());

    }

    @Override
    public void updateSize(UpdateSizeDTO addSizeDTO) throws Exception {
        Size size = sizeRepository.findBySizeAndProduct(addSizeDTO.getSize(), new Product(addSizeDTO.getProductId()))
                .orElseThrow(() ->
                         new Exception(ErrorCode.SIZE_NOT_FOUND));

        size.setQuantity(addSizeDTO.getQuantity());
        sizeRepository.save(size);
    }

    @Override
    public void addSize(AddSizeDTO addSizeDTO) {
        Optional<Size> size = sizeRepository.findBySizeAndProduct(addSizeDTO.getSize()
                ,new Product(addSizeDTO.getProductId()));
        if(size.isPresent()){
            throw new DataNotFoundException(ErrorCode.SIZE_IS_EXIST);
        }
        Size newSize = Size.builder()
                        .product(new Product(addSizeDTO.getProductId()))
                                .size(addSizeDTO.getSize())
                                        .quantity(addSizeDTO.getQuantity())
                                                .build();

        sizeRepository.save(newSize);
    }

    @Override
    public void delete(Long id) {
        sizeRepository.findById(id).orElseThrow(
            () -> new IllegalStateException(ErrorCode.SIZE_NOT_FOUND)
        );
        sizeRepository.deleteById(id);
    }
}
