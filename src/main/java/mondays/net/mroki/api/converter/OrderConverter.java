package mondays.net.mroki.api.converter;

import mondays.net.mroki.api.dto.PageDTO;
import mondays.net.mroki.api.dto.order.OrderInListDTO;
import mondays.net.mroki.api.dto.productDTO.ProductAdminDTO;
import mondays.net.mroki.api.dto.productDTO.ProductDTO;
import mondays.net.mroki.api.entity.Orders;
import mondays.net.mroki.api.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderConverter {

    public OrderInListDTO entityToDTO(Orders orders){
        return OrderInListDTO.builder()
                .id(orders.getId())
                .address(orders.getAddress())
                .createdDate(orders.getCreatedDate())
                .customerName(orders.getCustomerName())
                .phoneNumber(orders.getPhoneNumber())
                .totalBill(orders.getTotalBill())
                .build();
    }

    public List<OrderInListDTO> entityToDTO(List<Orders> orders){
        return   orders.stream()
                .map((product) -> entityToDTO(product))
                .collect(Collectors.toList());
    }

    public PageDTO entityToProductHomePageDTO(Page<Orders> orders) {

        List<OrderInListDTO> dto = orders.stream()
                .map((product -> entityToDTO(product)))
                .collect(Collectors.toList());

        return  PageDTO.builder()
                .totalPage(orders.getTotalPages())
                .totalElement(orders.getTotalElements())
                .data(dto)
                .build();
    }
}
