package mondays.net.mroki.api.service;


import lombok.AllArgsConstructor;
import mondays.net.mroki.api.entity.Cart;
import mondays.net.mroki.api.entity.Customer;
import mondays.net.mroki.api.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@AllArgsConstructor
public class CartService {

    @Autowired
    private final CartRepository cartRepository;

    public Cart getCart(Long customerId){
        Customer customer = new Customer(customerId);

        if(!Optional.ofNullable(cartRepository.getCartId(customerId)).isPresent()){
            Cart cart = new Cart();
            cart.setCustomer(customer);
            cartRepository.save(cart);
        }

        return cartRepository.findCartByCustomer(customer);
    }

    public String addProductToCart(Long cartId , Long productId){

        //just add product to cart if not exists
        if(!Optional.ofNullable(cartRepository.productId(productId , cartId)).isPresent()){
            cartRepository.addProductToCart(cartId   , productId);
            return "Add successfully";
        }
        else{
            return "Product was already in cart";
        }

    }
}
