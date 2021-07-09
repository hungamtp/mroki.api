package mondays.net.mroki.api.service.impl;

import lombok.AllArgsConstructor;
import mondays.net.mroki.api.dto.CartDTO;
import mondays.net.mroki.api.entity.ArchiveBox;
import mondays.net.mroki.api.entity.Customer;
import mondays.net.mroki.api.entity.Orders;
import mondays.net.mroki.api.repository.ArchiveBoxRepository;
import mondays.net.mroki.api.repository.OrderRepository;
import mondays.net.mroki.api.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    @Autowired
    private final OrderRepository orderRepository;

    @Autowired
    private final ArchiveBoxRepository archiveBoxRepository;

    public void order(CartDTO cart) {

        // check quantity of product
        // add product to order
        // set archive box
//        Orders newOrder = new Orders(cart);
//        newOrder.setArchiveBox(ArchiveBox.builder().id((archiveBoxRepository.getBoxIdIsAvailable())).build());
//        newOrder.setReceived(false);
//        newOrder.setInArchiveBox(true);
//        newOrder.setShipping(false);
//        newOrder.setCreatedDate(LocalDate.now());

      //  orderRepository.save(newOrder);
    }

    public List<Orders> orders(Long customerId) {
        return orderRepository.findOrdersByCustomer(new Customer(customerId));
    }
}
