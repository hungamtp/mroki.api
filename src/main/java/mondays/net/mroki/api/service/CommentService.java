package mondays.net.mroki.api.service;

import mondays.net.mroki.api.dto.CommentDTO;
import mondays.net.mroki.api.entity.Comment;

import java.util.List;

public interface CommentService {
    void comment(Comment comment);

    List<CommentDTO> getComment(int page, Long productId);
}
