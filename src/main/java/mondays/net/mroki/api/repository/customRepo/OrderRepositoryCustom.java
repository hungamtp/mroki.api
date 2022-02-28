package mondays.net.mroki.api.repository.customRepo;

import mondays.net.mroki.api.dto.order.OrderInListDTO;

import java.util.List;

public interface OrderRepositoryCustom {
    List<OrderInListDTO> getOrderDetailByPhoneNumber (String phoneNumber);
}
