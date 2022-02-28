package mondays.net.mroki.api.service.impl;

import lombok.AllArgsConstructor;
import mondays.net.mroki.api.dto.customerDTO.CustomerOrderDTO;
import mondays.net.mroki.api.dto.productDTO.ProductAddToCartDTO;
import mondays.net.mroki.api.entity.Customer;
import mondays.net.mroki.api.entity.OrderDetail;
import mondays.net.mroki.api.entity.Orders;
import mondays.net.mroki.api.entity.Product;
import mondays.net.mroki.api.repository.*;
import mondays.net.mroki.api.responseCode.ErrorCode;
import mondays.net.mroki.api.service.OrderService;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;

    private OrderDetailRepository orderDetailRepository;

    private ProductRepository productRepository;

    private CustomerRepository customerRepository;

    private SizeRepository sizeRepository;

    public List<String> order(List<ProductAddToCartDTO> cart, Long customerId, CustomerOrderDTO customerOrderDTO) {

        List<String> listOfProductIsNotEnough = new ArrayList<>();
        List<OrderDetail> orderDetails = new ArrayList<>();
        // check quantity of product ,
        // if product have enough quantity then add to order
        // otherwise add to list error
        cart.forEach((product) -> {
            try {
                if (!productRepository.isEnough(product.getId(), product.getSize(),  product.getQuantity()))
                    listOfProductIsNotEnough.add(product.getId().toString() + "-" + product.getSize());
                else
                    orderDetails.add(OrderDetail.builder()
                            .product(new Product(product.getId()))
                                    .size(product.getSize())
                                    .quantity(product.getQuantity())
                            .build());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // if any product do not have enough then no order
        if (listOfProductIsNotEnough.size() != 0) return listOfProductIsNotEnough;
        else {

            // reduce quantity

            Orders orders = null;
            if(customerId == null){
                 orders = Orders.builder()
                        .createdDate(LocalDate.now())
                        .orderDetails(orderDetails)
                         .customerName(customerOrderDTO.getCustomerName())
                         .address(customerOrderDTO.getAddress())
                         .phoneNumber(customerOrderDTO.getPhoneNumber())
                        .build();

            }else{

                 orders = Orders.builder()
                        .createdDate(LocalDate.now())
                        .customer(Customer.builder().id(customerId).build())
                        .orderDetails(orderDetails)
                         .customerName(customerOrderDTO.getCustomerName())
                         .address(customerOrderDTO.getAddress())
                         .phoneNumber(customerOrderDTO.getPhoneNumber())
                        .build();
            }

            Orders savedOrder =orderRepository.save(orders);
            orderDetails.forEach((orderDetail -> {
                orderDetail.setOrder(new Orders(savedOrder.getId()));
                orderDetailRepository.save(orderDetail);

            }));
            cart.forEach(productAddToCartDTO -> {
                productRepository.reduceQuantity(productAddToCartDTO.getId() , productAddToCartDTO.getQuantity(), productAddToCartDTO.getSize());
            });

            return null;
        }

    }

    public List<Orders> orders(Long customerId) {
        return orderRepository.findOrdersByCustomer(new Customer(customerId));
    }
}
