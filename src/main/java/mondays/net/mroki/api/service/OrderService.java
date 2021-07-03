package mondays.net.mroki.api.service;

import lombok.AllArgsConstructor;
import mondays.net.mroki.api.entity.ArchiveBox;
import mondays.net.mroki.api.entity.Cart;
import mondays.net.mroki.api.entity.Customer;
import mondays.net.mroki.api.entity.Orders;
import mondays.net.mroki.api.repository.ArchiveBoxRepository;
import mondays.net.mroki.api.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {

    @Autowired
    private final OrderRepository orderRepository;

    @Autowired
    private final ArchiveBoxRepository archiveBoxRepository;

    public void order(Cart cart){
        Orders newOrder = new Orders(cart);
        newOrder.setArchiveBox(new ArchiveBox(archiveBoxRepository.getBoxIdIsAvailable()));
        newOrder.setReceived(false);
        newOrder.setInArchiveBox(true);
        newOrder.setShipping(false);

        orderRepository.save(newOrder);

    }

    public List<Orders> orders (Long customerId){

        return orderRepository.findOrdersByCustomer(new Customer(customerId));
    }
}
