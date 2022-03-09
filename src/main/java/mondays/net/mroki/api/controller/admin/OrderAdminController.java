package mondays.net.mroki.api.controller.admin;

import lombok.AllArgsConstructor;
import mondays.net.mroki.api.dto.ResponseDTO;
import mondays.net.mroki.api.entity.Orders;
import mondays.net.mroki.api.entity.Product;
import mondays.net.mroki.api.filter.OrderSpecification;
import mondays.net.mroki.api.filter.OrderSpecificationBuilder;
import mondays.net.mroki.api.filter.ProductSpecificationsBuilder;
import mondays.net.mroki.api.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("admin/order")
@AllArgsConstructor
@CrossOrigin
@PreAuthorize("hasRole('Admin')")
public class OrderAdminController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<ResponseDTO> getAllProduct(@RequestParam Integer page,
                                                     @RequestParam Integer size,
                                                     @RequestParam String sort,
                                                     @RequestParam String search) {
        ResponseDTO response = new ResponseDTO();

        if (!Optional.ofNullable(sort).isPresent()) sort = "id";
        Pageable pageable = PageRequest.of(Optional.ofNullable(page).orElse(0), size, Sort.by(sort));

        OrderSpecificationBuilder builder = new OrderSpecificationBuilder();
        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
        Matcher matcher = pattern.matcher(search + ",");
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
        }

        Specification<Orders> spec = builder.build();

        response.setData(orderService.findAllOrder(pageable , spec));

        return ResponseEntity.ok().body(response);
    }
}
