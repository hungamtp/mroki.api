package mondays.net.mroki.api.service.impl;

import lombok.AllArgsConstructor;
import mondays.net.mroki.api.dto.productDTO.ProductAddToCartDTO;
import mondays.net.mroki.api.dto.productDTO.ProductDTO;
import mondays.net.mroki.api.entity.ArchiveBox;
import mondays.net.mroki.api.entity.Customer;
import mondays.net.mroki.api.entity.Orders;
import mondays.net.mroki.api.entity.Product;
import mondays.net.mroki.api.repository.ArchiveBoxRepository;
import mondays.net.mroki.api.repository.OrderRepository;
import mondays.net.mroki.api.repository.ProductRepository;
import mondays.net.mroki.api.repository.SizeRepository;
import mondays.net.mroki.api.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    @Autowired
    private final OrderRepository orderRepository;

    @Autowired
    private final SizeRepository sizeRepository;

    public List<String> order(List<ProductAddToCartDTO> cart, Long customerId) {

        List<String> listOfProductIsNotEnough = new ArrayList<>();

        Orders orders = Orders.builder()
                .createdDate(LocalDate.now())
                .customer(Customer.builder().id(customerId).build())
                .build();

        List<Product> productOrder = new ArrayList<>();
        // check quantity of product ,
        // if product have enough quantity then add to order
        // otherwise add to list error
        cart.forEach((product) -> {
            if(!sizeRepository.isEnough(product.getId(), product.getQuantity(), product.getSize()))
                listOfProductIsNotEnough.add(product.getId().toString());
            else
                productOrder.add(Product.builder().id(product.getId()).build());


        });
            orders.setProduct(productOrder);
        // if any product do not have enough then no order
        if (listOfProductIsNotEnough.size() != 0) return listOfProductIsNotEnough;
        else {
            // get available box then set to order

            // reduce quantity

            orderRepository.save(orders);

            return null;
        }

    }

    public List<Orders> orders(Long customerId) {
        return orderRepository.findOrdersByCustomer(new Customer(customerId));
    }
}
