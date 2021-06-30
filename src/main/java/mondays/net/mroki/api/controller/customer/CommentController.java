package mondays.net.mroki.api.controller.customer;

import lombok.AllArgsConstructor;
import mondays.net.mroki.api.entity.Comment;
import mondays.net.mroki.api.entity.Customer;
import mondays.net.mroki.api.entity.Product;
import mondays.net.mroki.api.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user/comment")
@AllArgsConstructor
public class CommentController {

    @Autowired
    private final CommentService commentService;

    @PostMapping
    public void comment(@RequestBody Comment comment ,
                        @RequestParam Long productId ,
                        @RequestParam Long customerId){

        comment.setProduct(new Product(productId));
        comment.setCustomer(new Customer(customerId));

        commentService.comment(comment);

    }


}
