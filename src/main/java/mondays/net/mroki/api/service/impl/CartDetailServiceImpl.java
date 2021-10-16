package mondays.net.mroki.api.service.impl;

import lombok.AllArgsConstructor;
import mondays.net.mroki.api.dto.cartDetailDto.AddCartDetailDTO;
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

import java.util.Optional;

@Service
@AllArgsConstructor
public class CartDetailServiceImpl implements CartDetailService {

    private CartDetailRepository cartDetailRepository;
    private CustomerRepository customerRepository;
    private CartRepository cartRepository;


    @Override
    public void addToCart(AddCartDetailDTO addCartDetailDTO , String username) {

        Customer customer = customerRepository.findByUsername(username);

        if(!cartRepository.isExist(customer.getId())){
            cartRepository.save(Cart.builder()
                    .customer(customer)
                    .build());
        }

        Optional<Cart> cart = cartRepository.findOneByCustomer(customer);

        if(cart.isPresent()) {
            CartDetail cartDetail = CartDetail.builder()
                    .cart(Cart.builder().id(cart.get().getId()).build())
                    .product(Product.builder().id(addCartDetailDTO.getProductId()).build())
                    .size(addCartDetailDTO.getSize())
                    .quantity(addCartDetailDTO.getSize())
                    .build();
             cartDetailRepository.save(cartDetail);
        }
        else
            throw new DataNotFoundException(ErrorCode.CART_NOT_FOUND);


    }
}
