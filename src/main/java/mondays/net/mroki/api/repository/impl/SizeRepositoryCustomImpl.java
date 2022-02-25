package mondays.net.mroki.api.repository.impl;

import mondays.net.mroki.api.dto.productDTO.ProductAddToCartDTO;
import mondays.net.mroki.api.entity.Product;
import mondays.net.mroki.api.entity.Size;
import mondays.net.mroki.api.repository.customRepo.SizeRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;


@Repository
public class SizeRepositoryCustomImpl implements SizeRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    public void reduceQuantity(List<ProductAddToCartDTO> carts){
        for(var cart : carts) {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Size> criteriaQuery = criteriaBuilder.createQuery(Size.class);
            Root<Size> root = criteriaQuery.from(Size.class);
            Join<Size , Product> productJoin = root.join("product");
            Predicate productItEqual = criteriaBuilder.equal(productJoin.get("id"), cart.getId());
            Predicate sizeEqual = criteriaBuilder.equal(root.get("size"), cart.getSize());
            criteriaQuery.select(root);
            criteriaQuery.where(productItEqual , sizeEqual);

            TypedQuery<Size> schoolQuery = entityManager.createQuery(criteriaQuery);

            Size size = schoolQuery.getSingleResult();
            size.setQuantity(size.getQuantity() - cart.getQuantity());

        }
    }
}
