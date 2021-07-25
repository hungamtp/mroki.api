package mondays.net.mroki.api.filter;


import mondays.net.mroki.api.entity.Product;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductSpecificationsBuilder {

    private final List<SearchCriteria> params;

    public ProductSpecificationsBuilder() {
        params = new ArrayList<SearchCriteria>();
    }

    public ProductSpecificationsBuilder with(String key, String operation, Object value) {

        if(key.startsWith("price")){
            value = key.replace("price" , "")  + "-" + value;
            key = "price";
        }
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public Specification<Product> build() {
        if (params.size() == 0) {
            return null;
        }

        List<Specification> specs = params.stream()
                .map(ProductSpecification::new)
                .collect(Collectors.toList());

        Specification result = specs.get(0);

        for (int i = 1; i < params.size(); i++) {
            result = Specification.where(result)
                    .and(specs.get(i));
        }
        return result;
    }
}