package mondays.net.mroki.api.converter;

import mondays.net.mroki.api.dto.PageDTO;
import mondays.net.mroki.api.dto.customerDTO.CustomerDTO;
import mondays.net.mroki.api.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerConverter {

    private CustomerDTO entityToDTo(Customer customer){
        return CustomerDTO.builder()
                .id(customer.getId())
                .username(customer.getUsername())
                .email(customer.getEmail())
                .avatar(customer.getAvatar())
                .isVerifiedEmail(customer.isVerifiedEmail())
                .roleName(customer.getRole().getRoleName())
                .phone(customer.getPhone())
                .build();
    }

    public PageDTO entityToDto(Page<Customer> products){
        List<CustomerDTO> dto = new ArrayList<>();

       dto= products.stream().map((customer) ->entityToDTo(customer)).collect(Collectors.toList());

       return PageDTO.builder()
               .totalPage(products.getTotalPages())
               .totalElement(products.getTotalElements())
               .data(dto)
               .build();
    }
}
