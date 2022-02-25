package mondays.net.mroki.api.repository.impl;

import mondays.net.mroki.api.dto.productDTO.ProductDTO;
import mondays.net.mroki.api.dto.productDTO.ProductHomeDTO;
import mondays.net.mroki.api.entity.Product;
import mondays.net.mroki.api.entity.Size;
import mondays.net.mroki.api.repository.customRepo.ProductRepositoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.List;

public class ProductRepositoryImpl implements ProductRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Boolean isEnough(Long productId, String size, int quantity) throws Exception {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Boolean> criteriaQuery = criteriaBuilder.createQuery(Boolean.class);
        Root<Size> root = criteriaQuery.from(Size.class);
        Join<Product, Size> sizeJoin = root.join("product");

        Predicate productItEqual = criteriaBuilder.equal(sizeJoin.get("id") , productId);
        Predicate sizeEqual = criteriaBuilder.equal(root.get("size") , size);

        criteriaQuery.multiselect(
                criteriaBuilder.<Boolean>selectCase()
                        .when(criteriaBuilder.greaterThan(root.get("quantity"), quantity), true)
                        .otherwise(false));

        criteriaQuery.where(productItEqual , sizeEqual);

        try{
            return entityManager.createQuery(criteriaQuery).getResultList().get(0);
        }
        catch (Exception exception){
            throw new Exception("Wrong param");
        }

    }


}
