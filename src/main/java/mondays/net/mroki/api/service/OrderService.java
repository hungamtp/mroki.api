package mondays.net.mroki.api.service;

import mondays.net.mroki.api.dto.PageDTO;
import mondays.net.mroki.api.dto.customerDTO.CustomerOrderDTO;
import mondays.net.mroki.api.dto.order.OrderInListDTO;
import mondays.net.mroki.api.dto.orderDetail.OrderDetailDTO;
import mondays.net.mroki.api.dto.productDTO.ProductAddToCartDTO;
import mondays.net.mroki.api.dto.productDTO.ProductDTO;
import mondays.net.mroki.api.entity.Orders;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    List<String> order(List<ProductAddToCartDTO> cart , Long customerId , CustomerOrderDTO customerOrderDTO);

    List<Orders> orders(Long customerId);

    List<OrderInListDTO> findAllOrderByPhoneNumber(String phoneNumber , boolean isPhone);
    List<OrderInListDTO> findAllOrderByCustomerId(Long customerId);
    List<OrderDetailDTO> getOrderDetails(Long orderId);
    PageDTO findAllOrder(Pageable pageable, Specification specification);
}
