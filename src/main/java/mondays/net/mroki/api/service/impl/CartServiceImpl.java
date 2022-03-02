package mondays.net.mroki.api.service.impl;

import lombok.AllArgsConstructor;
import mondays.net.mroki.api.converter.CartConverter;
import mondays.net.mroki.api.converter.ProductConverter;
import mondays.net.mroki.api.dto.cartDTO.CartDTO;
import mondays.net.mroki.api.dto.cartDetailDto.ShowCartDetailDTO;
import mondays.net.mroki.api.dto.productDTO.ProductAddToCartDTO;
import mondays.net.mroki.api.entity.Cart;
import mondays.net.mroki.api.entity.CartDetail;
import mondays.net.mroki.api.entity.Customer;
import mondays.net.mroki.api.entity.Product;
import mondays.net.mroki.api.repository.CartDetailRepository;
import mondays.net.mroki.api.repository.CartRepository;
import mondays.net.mroki.api.responseCode.ErrorCode;
import mondays.net.mroki.api.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService {

    @Autowired
    private final CartRepository repo;

    @Autowired
    private CartDetailRepository cartDetailRepository;

    @Autowired
    private final CartConverter converter;

    @Autowired
    private final ProductConverter productConverter;


    public void addToCart(Long customerId, ProductAddToCartDTO product) {

//        if (repo.isProductInCart(product.getId(), customerId, product.getSize()))
//            repo.updateQuantity(product.getQuantity(), product.getId(), customerId, product.getSize());
//        else {
//            Long cartId = repo.getCartId(customerId);
//            repo.addToCart(cartId, product.getId(), product.getQuantity(), product.getSize());
//        }

    }

    @Override
    public void updateQuantity(int quantity, Long productId, Long customerId, int size) {
        repo.updateQuantity(quantity, productId, customerId, size);
    }

    public CartDTO getCart(Long customerId) {

        if (!isExist(customerId)) {
            Cart newCart = Cart.builder()
                    .customer(Customer.builder().id(customerId).build())
                    .build();
            repo.save(newCart);
            return null;
        }

        return CartDTO.builder()
                .customerId(customerId)
                .products(productConverter.dataToCartDto(repo.getProductInCart(customerId)))
                .build();

    }


    public Integer getIconData(Long customerId) {
        if (!isExist(customerId)) {
            Cart newCart = Cart.builder()
                    .customer(Customer.builder().id(customerId).build())
                    .build();
            repo.save(newCart);
        }
        return repo.getCountProductInCart(customerId);
    }

    public boolean isProductInCart(Long cartId, Long productId, int size) {
        return repo.isProductInCart(productId, cartId, size);
    }

    public boolean isExist(Long customerId) {
        return repo.isExist(customerId);
    }

    public void deleteProductInCart(Long productId , int size , Long userId){
        repo.deleteProductInCart(productId , size , userId);
    }

    public void addAllToCart(List<ProductAddToCartDTO> cartDTOS , Long customerId){
        Optional<Cart> foundCart = repo.findByCustomer(new Customer(customerId));
        List<CartDetail> cartDetails = new ArrayList<>();
        if(foundCart.isPresent()){
            for (var cart : cartDTOS){
                cartDetails.add(CartDetail.builder()
                        .size(cart.getSize())
                        .quantity(cart.getQuantity())
                        .product(new Product(cart.getId()))
                        .cart(foundCart.get())
                        .build());
            }
            cartDetailRepository.saveAll(cartDetails);

        }
        else{
            Cart newCart = new Cart();
            newCart.setCustomer(new Customer(customerId));
            Cart savedCart =repo.save(newCart);
            for (var cart : cartDTOS){
                cartDetails.add(CartDetail.builder()
                        .size(cart.getSize())
                        .quantity(cart.getQuantity())
                        .product(new Product(cart.getId()))
                        .cart(savedCart)
                        .build());
            }
            cartDetailRepository.saveAll(cartDetails);
        }

    }

    public List<ShowCartDetailDTO> getAllCartDetail(Long customerId){
        Optional<Cart>foundCart =repo.findByCustomer(new Customer(customerId));
        foundCart.orElseThrow(
                () -> new IllegalStateException(ErrorCode.NO_CART_DETAIL)
        );

        List<ShowCartDetailDTO> result = new ArrayList<>();

        for (var cart: foundCart.get().getCartDetails()){
            result.add(ShowCartDetailDTO.builder()
                            .productId(cart.getProduct().getId())
                            .image(cart.getProduct().getProductImage().getThumbnail())
                            .size(cart.getSize())
                            .quantity(cart.getQuantity())
                            .price(cart.getProduct().getPrice())
                    .build());
        }

        return result;
    }

}
