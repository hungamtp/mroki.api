package mondays.net.mroki.api.converter;

import mondays.net.mroki.api.dto.CommentAddDTO;
import mondays.net.mroki.api.dto.CommentDTO;
import mondays.net.mroki.api.entity.Comment;
import mondays.net.mroki.api.entity.Customer;
import mondays.net.mroki.api.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommentConverter {

    public Comment dtoToEntity(CommentAddDTO commentAddDTO) {

        return Comment.builder().customer(Customer.builder().id(commentAddDTO.getCustomerId()).build())
                .product(Product.builder().id(commentAddDTO.getProductId()).build())
                .content(commentAddDTO.getContent())
                .rate(commentAddDTO.getRate())
                .build();
    }

    public CommentDTO entityToDto(Comment comment) {


        return CommentDTO.builder()
                .username(comment.getCustomer().getUsername())
                .avatar(comment.getCustomer().getAvatar())
                .content(comment.getContent())
                .rate(comment.getRate())
                .build();

    }

    public Page<CommentDTO> entityToDto(Page<Comment> comments) {
        Pageable pageable = comments.getPageable();

        List<CommentDTO> commentDTOS = comments.stream()
                .map((comment -> entityToDto(comment)))
                .collect(Collectors.toList());

        Page<CommentDTO> page = new PageImpl<>(commentDTOS , pageable , 9);
        return page;
    }


}
