package mondays.net.mroki.api.filter;

import lombok.AllArgsConstructor;
import mondays.net.mroki.api.entity.Category;
import mondays.net.mroki.api.entity.Discount;
import mondays.net.mroki.api.entity.Product;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

@AllArgsConstructor
public class ProductSpecification implements Specification<Product> {
    private SearchCriteria criteria;


    @Override
    public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        if(criteria.getKey().equals("category")){
            Join<Product, Category> categoryJoin = root.join("category") ;
            return builder.equal(categoryJoin.get("id") ,Long.parseLong(criteria.getValue().toString()));
        }
        if(criteria.getKey().equals("discount")){
            Join<Product, Discount> discount = root.join("discount") ;
            return builder.equal(discount.get("id") ,Long.parseLong(criteria.getValue().toString()));
        }
        if(criteria.getKey().startsWith("price")){
            return builder.between(
                    root.<Float>get("price") ,
                    Float.parseFloat( criteria.getValue().toString().split("-")[0]) ,
                    Float.parseFloat(  criteria.getValue().toString().split("-")[1] ) );
        }
        if(criteria.getValue().equals("true") ){
            return  builder.isTrue( root.<Boolean>get(criteria.getKey()));
        }
        if(criteria.getValue().equals("false")){
            return  builder.isFalse( root.<Boolean>get(criteria.getKey()));
        }
        if (criteria.getOperation().equalsIgnoreCase(">")) {
            return builder.greaterThanOrEqualTo(
                    root.<String>get(criteria.getKey()), criteria.getValue().toString());
        } else if (criteria.getOperation().equalsIgnoreCase("<")) {
            return builder.lessThanOrEqualTo(
                    root.<String>get(criteria.getKey()), criteria.getValue().toString());
        } else if (criteria.getOperation().equalsIgnoreCase(":")) {
            if (root.get(criteria.getKey()).getJavaType() == String.class) {
                return builder.like(
                        builder.lower(root.<String>get(criteria.getKey())), "%" + criteria.getValue().toString().toLowerCase() + "%");
            } else {
                return builder.equal(root.get(criteria.getKey()), criteria.getValue());
            }
        }
        return null;
    }
}
