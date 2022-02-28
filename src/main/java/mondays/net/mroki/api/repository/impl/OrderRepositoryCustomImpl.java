package mondays.net.mroki.api.repository.impl;

import mondays.net.mroki.api.dto.customerDTO.CustomerOrderDTO;
import mondays.net.mroki.api.dto.orderDetail.OrderDetailDTO;
import mondays.net.mroki.api.repository.customRepo.OrderRepositoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class OrderRepositoryCustomImpl implements OrderRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

//    public List<O> getOrderDetailByPhoneNumber (String phoneNumber , Long customerId , CustomerOrderDTO customerOrderDTO){
//
//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<OrderDetailDTO> query = criteriaBuilder.createQuery(OrderDetailDTO.class);
//        Root<OrderDetailDTO>
//    }
}
