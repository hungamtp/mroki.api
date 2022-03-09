package mondays.net.mroki.api.filter;

import mondays.net.mroki.api.entity.Customer;
import mondays.net.mroki.api.entity.Orders;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderSpecificationBuilder {
    private final List<SearchCriteria> params;

    public OrderSpecificationBuilder() {
        params = new ArrayList<SearchCriteria>();
    }

    public OrderSpecificationBuilder with(String key, String operation, Object value) {

        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public Specification<Orders> build() {
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
