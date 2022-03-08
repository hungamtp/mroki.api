package mondays.net.mroki.api.service;

import mondays.net.mroki.api.dto.discountDTO.AddDiscountDTO;
import mondays.net.mroki.api.dto.discountDTO.AddDiscountForProductDTO;
import mondays.net.mroki.api.dto.discountDTO.RemoveProductDiscountDTO;
import mondays.net.mroki.api.dto.discountDTO.UpdateDiscountDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DiscountService {
    void addDiscount(AddDiscountDTO addDiscountDTO);
    List<AddDiscountDTO> getAllDiscount();
    void addDiscountForProduct(Long productId , Long discountId);
    void updateDiscount(UpdateDiscountDTO discountDTO);
    void removeDiscountFromProduct(RemoveProductDiscountDTO dto);
}
