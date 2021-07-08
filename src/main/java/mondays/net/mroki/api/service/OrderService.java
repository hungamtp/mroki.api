package mondays.net.mroki.api.service;

import mondays.net.mroki.api.dto.CartDTO;
import mondays.net.mroki.api.entity.Orders;

import java.util.List;

public interface OrderService {
    public void order(CartDTO cart);
    public List<Orders> orders (Long customerId);
}
