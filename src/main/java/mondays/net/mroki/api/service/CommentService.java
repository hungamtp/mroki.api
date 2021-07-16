package mondays.net.mroki.api.service;

import mondays.net.mroki.api.dto.CommentDTO;
import mondays.net.mroki.api.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommentService {
    void comment(Comment comment);

    Page<Comment> getComment(Pageable pageable, Long productId);
}
