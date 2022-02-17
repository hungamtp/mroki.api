package mondays.net.mroki.api.repository.impl;

import mondays.net.mroki.api.dto.productDTO.ProductDTO;
import mondays.net.mroki.api.dto.productDTO.ProductHomeDTO;
import mondays.net.mroki.api.entity.Product;
import mondays.net.mroki.api.repository.customRepo.ProductRepositoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class ProductRepositoryImpl implements ProductRepositoryCustom {

//    @PersistenceContext
//    private EntityManager entityManager;
//
//    public List<ProductHomeDTO> getAllProductList(){
//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<ProductHomeDTO> criteriaQuery = criteriaBuilder.createQuery(ProductHomeDTO.class);
//        Root<Product> root = criteriaQuery.from(Product.class);
//
//    }
}
