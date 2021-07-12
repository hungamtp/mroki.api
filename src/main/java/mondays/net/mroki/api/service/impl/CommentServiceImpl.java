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

    private final int COMMENT_PAGE_SIZE = 5;

    @Autowired
    private final CommentRepository commentRepository;

    @Autowired
    private final CommentConverter converter;

    public void comment(Comment comment) {

        commentRepository.save(comment);
    }

    public List<CommentDTO> getComment(int page, Long productId) {

        Pageable pageable = PageRequest.of(page, COMMENT_PAGE_SIZE);

        Product product = Product.builder().id(productId).build();

        List<CommentDTO> result = converter.entityToDto(commentRepository.findByProduct(pageable, product).getContent());

        return result;

    }

}
