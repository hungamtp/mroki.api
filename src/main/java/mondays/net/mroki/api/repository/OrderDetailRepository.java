package mondays.net.mroki.api.repository;

import mondays.net.mroki.api.entity.OrderDetail;
import mondays.net.mroki.api.entity.Orders;
import org.hibernate.criterion.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    List<OrderDetail> findByOrder(Orders order);
}
