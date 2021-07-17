package mondays.net.mroki.api.converter;

import mondays.net.mroki.api.dto.SizeDTO;
import mondays.net.mroki.api.entity.Size;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SizeConverter {

    public SizeDTO entityToDto(Size size){

        return SizeDTO.builder()
                .productId(size.getProduct().getId())
                .size(size.getSize())
                .quantity(size.getQuantity())
                .build();
    }

    public List<SizeDTO> dtoToEntity(List<Size> sizes){
        return sizes.stream()
                .map((size ) -> entityToDto(size))
                .collect(Collectors.toList());
    }

}
