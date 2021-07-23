package mondays.net.mroki.api.service;

import mondays.net.mroki.api.dto.productDTO.ProductDTO;
import mondays.net.mroki.api.entity.Orders;

import java.util.List;


public interface OrderService {
    public List<String> order(List<ProductDTO> cart , Long customerId);

    public List<Orders> orders(Long customerId);
}
