package mondays.net.mroki.api.converter;

import mondays.net.mroki.api.dto.PageDTO;
import mondays.net.mroki.api.dto.commentDTO.CommentAddDTO;
import mondays.net.mroki.api.dto.commentDTO.CommentDTO;
import mondays.net.mroki.api.entity.Comment;
import mondays.net.mroki.api.entity.Customer;
import mondays.net.mroki.api.entity.Product;
import org.springframework.data.domain.Page;
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

    public PageDTO entityToDto(Page<Comment> comments) {

        List<CommentDTO> dto =  comments.stream()
                .map((comment -> entityToDto(comment)))
                .collect(Collectors.toList());

        return PageDTO.builder()
                .totalPage(comments.getTotalPages())
                .totalElement(comments.getTotalElements())
                .data(dto)
                .build();


    }




}
