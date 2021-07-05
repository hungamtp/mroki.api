package mondays.net.mroki.api.service;

import mondays.net.mroki.api.entity.Cart;
import mondays.net.mroki.api.entity.Orders;

import java.util.List;

public interface OrderService {
    public void order(Cart cart);
    public List<Orders> orders (Long customerId);
}
