package mondays.net.mroki.api.service.impl;


import lombok.AllArgsConstructor;
import mondays.net.mroki.api.converter.CommentConverter;
import mondays.net.mroki.api.dto.CommentDTO;
import mondays.net.mroki.api.entity.Comment;
import mondays.net.mroki.api.entity.Product;
import mondays.net.mroki.api.repository.CommentRepository;
import mondays.net.mroki.api.service.CommentService;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {

    @Autowired
    private final CommentRepository commentRepository;

    public void comment(Comment comment) {

        commentRepository.save(comment);
    }

    public Page<Comment> getComment(Pageable pageable, Long productId) {

        Product product = Product.builder()
                .id(productId)
                .build();

        return commentRepository.findByProduct(pageable , product);

    }

}
