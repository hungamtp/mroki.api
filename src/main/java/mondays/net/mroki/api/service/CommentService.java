package mondays.net.mroki.api.service;

import mondays.net.mroki.api.entity.Comment;
import org.springframework.data.domain.Page;

public interface CommentService {
    public void comment(Comment comment);
    public Page<Comment> getComment(int page , Long productId);
}
