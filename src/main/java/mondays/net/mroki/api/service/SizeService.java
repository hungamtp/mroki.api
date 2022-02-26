package mondays.net.mroki.api.service;

import mondays.net.mroki.api.dto.sizeDTO.AddSizeDTO;
import mondays.net.mroki.api.dto.sizeDTO.UpdateSizeDTO;
import mondays.net.mroki.api.entity.Size;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SizeService {

    List<Size> findByProduct(Long productId);
    void updateSize(UpdateSizeDTO addSizeDTO) throws Exception;
    void addSize(AddSizeDTO addSizeDTO);
}
