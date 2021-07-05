package mondays.net.mroki.api.service.impl;


import lombok.AllArgsConstructor;
import mondays.net.mroki.api.entity.Comment;
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

    public void comment(Comment comment){

        if(!Optional.of(comment.getCustomer().getId()).isPresent()) {
            throw new IllegalIdentifierException("comment must have userId");
        }
        else if(!Optional.of(comment.getProduct().getId()).isPresent()){
            throw new IllegalIdentifierException("comment must have productId");
        }
        else if(!Optional.of(comment.getRate()).isPresent()){
            throw new IllegalIdentifierException("comment must have rate");
        }

        commentRepository.save(comment);
    }

    public Page<Comment> getComment(int page , Long productId){

        Pageable pageable = PageRequest.of(page , COMMENT_PAGE_SIZE);

        return commentRepository.findCommentByProduct(pageable , productId);

    }

}
