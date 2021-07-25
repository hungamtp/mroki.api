package mondays.net.mroki.api.service;

import mondays.net.mroki.api.dto.PageDTO;
import mondays.net.mroki.api.dto.commentDTO.CommentTotalDTO;
import mondays.net.mroki.api.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface CommentService {
    void comment(Comment comment);

    PageDTO getComment(Pageable pageable, Long productId);

    CommentTotalDTO getTotalComment(Long ProductId);

    int getCountComment(Long productId);


}
