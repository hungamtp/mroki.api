package mondays.net.mroki.api.converter;

import mondays.net.mroki.api.dto.discountDTO.AddDiscountDTO;
import mondays.net.mroki.api.dto.productDTO.ProductAdminDTO;
import mondays.net.mroki.api.entity.Discount;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DiscountConverter {

    public AddDiscountDTO entityToDto(Discount discount){
        return AddDiscountDTO.builder()
                .saleOff(discount.getSaleOff())
                .startDate(discount.getStartDate())
                .endDate(discount.getEndDate())
                .build();
    }

    public List<AddDiscountDTO> entitiesToDtos (List<Discount> discounts){
        return   discounts.stream()
                .map((discount) -> entityToDto(discount))
                .collect(Collectors.toList());
    }
}
