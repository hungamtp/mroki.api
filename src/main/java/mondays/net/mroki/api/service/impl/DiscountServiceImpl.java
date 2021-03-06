package mondays.net.mroki.api.service.impl;

import lombok.AllArgsConstructor;
import mondays.net.mroki.api.converter.DiscountConverter;
import mondays.net.mroki.api.dto.discountDTO.AddDiscountDTO;
import mondays.net.mroki.api.dto.discountDTO.AddDiscountForProductDTO;
import mondays.net.mroki.api.dto.discountDTO.RemoveProductDiscountDTO;
import mondays.net.mroki.api.dto.discountDTO.UpdateDiscountDTO;
import mondays.net.mroki.api.entity.Discount;
import mondays.net.mroki.api.entity.Product;
import mondays.net.mroki.api.exception.DataNotFoundException;
import mondays.net.mroki.api.repository.DiscountRepository;
import mondays.net.mroki.api.repository.ProductRepository;
import mondays.net.mroki.api.responseCode.ErrorCode;
import mondays.net.mroki.api.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DiscountServiceImpl implements DiscountService {

    private DiscountRepository discountRepository;
    private ProductRepository productRepository;
    private DiscountConverter discountConverter;

    @Override
    public void addDiscount(AddDiscountDTO addDiscountDTO){
        Optional<Discount> discount= discountRepository.findBySaleOff(addDiscountDTO.getSaleOff());

        if(discount.isPresent()){
            throw new IllegalStateException(ErrorCode.DISCOUNT_IS_EXIST);
        }

        if(addDiscountDTO.getStartDate().isAfter(addDiscountDTO.getEndDate())){
            throw new IllegalStateException(ErrorCode.DATE_WRONG);
        }

        Discount newDiscount = Discount.builder()
                .saleOff(addDiscountDTO.getSaleOff())
                .endDate(addDiscountDTO.getEndDate())
                .startDate(addDiscountDTO.getStartDate())
                .build();

        discountRepository.save(newDiscount);
    }

    @Override
    public List<AddDiscountDTO> getAllDiscount() {
        return discountConverter.entitiesToDtos(discountRepository.findAll());
    }

    @Override
    public void addDiscountForProduct(Long productId , Long discountId ) {
        Optional<Product> product = Optional.ofNullable(productRepository.findById(productId).orElseThrow(()
                -> new DataNotFoundException(ErrorCode.PRODUCT_NOT_FOUND)));

        discountRepository.findById(discountId).orElseThrow(
                () -> new DataNotFoundException(ErrorCode.DISCOUNT_NOT_FOUND)
        );

        Product updateProduct = product.get();
        updateProduct.setDiscount(new Discount(discountId));
        productRepository.save(updateProduct);
    }

    @Override
    public void removeDiscountFromProduct(RemoveProductDiscountDTO dto) {
        Optional<Product> product = Optional.ofNullable(productRepository.findById(dto.getProductId()).orElseThrow(()
                -> new DataNotFoundException(ErrorCode.PRODUCT_NOT_FOUND)));

        Product updateProduct = product.get();
        updateProduct.setDiscount(null);
        productRepository.save(updateProduct);
    }

    public void updateDiscount(UpdateDiscountDTO discountDTO){
        Optional<Discount> discount = discountRepository.findById(discountDTO.getId());

        discount.orElseThrow(
                () -> new IllegalStateException(ErrorCode.DISCOUNT_NOT_FOUND)
        );

        Discount updatedDiscount = discount.get();
        updatedDiscount.setSaleOff(discountDTO.getSaleOff());
        updatedDiscount.setStartDate(discountDTO.getStartDate());
        updatedDiscount.setEndDate(discountDTO.getEndDate());
        discountRepository.save(updatedDiscount);
    }
}
