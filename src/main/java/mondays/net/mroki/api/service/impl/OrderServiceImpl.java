package mondays.net.mroki.api.service.impl;

import lombok.AllArgsConstructor;
import mondays.net.mroki.api.dto.ProductDTO;
import mondays.net.mroki.api.entity.ArchiveBox;
import mondays.net.mroki.api.entity.Customer;
import mondays.net.mroki.api.entity.Orders;
import mondays.net.mroki.api.entity.Product;
import mondays.net.mroki.api.repository.ArchiveBoxRepository;
import mondays.net.mroki.api.repository.OrderRepository;
import mondays.net.mroki.api.repository.ProductRepository;
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
    private final ArchiveBoxRepository archiveBoxRepository;

    @Autowired
    private final ProductRepository productRepository;

    public List<String> order(List<ProductDTO> cart, Long customerId) {

        List<String> listOfProductIsNotEnough = new ArrayList<>();

        Orders orders = Orders.builder()
                .createdDate(LocalDate.now())
                .customer(Customer.builder().id(customerId).build())
                .product(new ArrayList<>())
                .build();

        // check quantity of product ,
        // if product have enough quantity then add to order
        // otherwise add to list error
        cart.forEach((product) -> {

            if (!productRepository.checkQuantity(product.getQuantity(), product.getId()))
                listOfProductIsNotEnough.add(product.getId().toString());

            else
                orders.getProduct().add(Product.builder().id(product.getId()).build());

        });

        // if any product do not have enough then no order
        if (listOfProductIsNotEnough.size() != 0) return listOfProductIsNotEnough;
        else {
            // get available box then set to order
            Long boxId = archiveBoxRepository.getBoxIdIsAvailable();

            orders.setArchiveBox(ArchiveBox.builder().id(boxId).build());

            // reduce quantity

            // set archive box is not available
            archiveBoxRepository.updateIsNotAvailable(boxId);
            orderRepository.save(orders);

            return null;
        }

    }

    public List<Orders> orders(Long customerId) {
        return orderRepository.findOrdersByCustomer(new Customer(customerId));
    }
}
