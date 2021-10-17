package mondays.net.mroki.api.service.impl;

import lombok.AllArgsConstructor;
import mondays.net.mroki.api.converter.CartDetailConverter;
import mondays.net.mroki.api.dto.cartDetailDto.AddCartDetailDTO;
import mondays.net.mroki.api.dto.cartDetailDto.CartDetailDTO;
import mondays.net.mroki.api.entity.Cart;
import mondays.net.mroki.api.entity.CartDetail;
import mondays.net.mroki.api.entity.Customer;
import mondays.net.mroki.api.entity.Product;
import mondays.net.mroki.api.exception.DataNotFoundException;
import mondays.net.mroki.api.repository.CartDetailRepository;
import mondays.net.mroki.api.repository.CartRepository;
import mondays.net.mroki.api.repository.CustomerRepository;
import mondays.net.mroki.api.responseCode.ErrorCode;
import mondays.net.mroki.api.service.CartDetailService;
import mondays.net.mroki.api.service.CartService;
import mondays.net.mroki.api.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CartDetailServiceImpl implements CartDetailService {

    private CartDetailRepository cartDetailRepository;
    private CustomerRepository customerRepository;
    private CartRepository cartRepository;
    private CartDetailConverter cartDetailConverter;


    @Override
    public void addToCart(AddCartDetailDTO addCartDetailDTO, String username) {

        Customer customer = customerRepository.findByUsername(username);

        if (!cartRepository.isExist(customer.getId())) {
            cartRepository.save(Cart.builder()
                    .customer(customer)
                    .build());
        }

        Optional<Cart> cart = cartRepository.findOneByCustomer(customer);


        if (cart.isPresent()) {
            Optional<CartDetail> cartDetail = cartDetailRepository.findOneByProductAndSizeAndCart(
                    Product.builder().id(addCartDetailDTO.getProductId()).build(),
                    addCartDetailDTO.getSize(),
                    cart.get());
            if (cartDetail.isPresent()) {

                CartDetail newCartDetail = cartDetail.get();
                newCartDetail.setQuantity(newCartDetail.getQuantity());
                cartDetailRepository.save(newCartDetail);
            } else {
                CartDetail newCartDetail = CartDetail.builder()
                        .cart(Cart.builder().id(cart.get().getId()).build())
                        .product(Product.builder().id(addCartDetailDTO.getProductId()).build())
                        .size(addCartDetailDTO.getSize())
                        .quantity(addCartDetailDTO.getQuantity())
                        .build();
                cartDetailRepository.save(newCartDetail);
            }

        } else
            throw new DataNotFoundException(ErrorCode.CART_NOT_FOUND);


    }

    @Override
    public List<CartDetailDTO> getCartDetails(Long customerId) {
        Long cartId = cartRepository.getCartId(customerId);
        return cartDetailConverter.entityToDtos(
                cartDetailRepository.findByCart(
                        Cart.builder().id(cartId).build()));
    }
}
