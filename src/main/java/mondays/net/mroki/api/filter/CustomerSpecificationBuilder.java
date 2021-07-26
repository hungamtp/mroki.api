package mondays.net.mroki.api.filter;

import lombok.AllArgsConstructor;
import mondays.net.mroki.api.entity.Customer;
import mondays.net.mroki.api.entity.Product;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class CustomerSpecificationBuilder {
    private final List<SearchCriteria> params;

    public CustomerSpecificationBuilder() {
        params = new ArrayList<SearchCriteria>();
    }

    public CustomerSpecificationBuilder with(String key, String operation, Object value) {

        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public Specification<Customer> build() {
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
