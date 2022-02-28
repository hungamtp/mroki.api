package mondays.net.mroki.api.repository.impl;

import mondays.net.mroki.api.dto.customerDTO.CustomerOrderDTO;
import mondays.net.mroki.api.dto.order.OrderInListDTO;
import mondays.net.mroki.api.dto.orderDetail.OrderDetailDTO;
import mondays.net.mroki.api.entity.Orders;
import mondays.net.mroki.api.repository.customRepo.OrderRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class OrderRepositoryCustomImpl implements OrderRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    public List<OrderInListDTO> getOrderDetailByPhoneNumber (String phoneNumber){

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<OrderInListDTO> query = criteriaBuilder.createQuery(OrderInListDTO.class);
        Root<Orders> root = query.from(Orders.class);
        Predicate phoneNumberEqual = criteriaBuilder.equal(root.get("phoneNumber") , phoneNumber);
        query.multiselect(
                root.get("id"),
                root.get("customerName"),
                root.get("address"),
                root.get("phoneNumber"),
                root.get("createdDate"),
                root.get("totalBill"));

        query.where(phoneNumberEqual);
        return entityManager.createQuery(query).getResultList();
    }
}
