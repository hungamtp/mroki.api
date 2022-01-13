package mondays.net.mroki.api.service;

import mondays.net.mroki.api.dto.PageDTO;
import mondays.net.mroki.api.dto.commentDTO.CommentTotalDTO;
import mondays.net.mroki.api.entity.Rate;
import org.springframework.data.domain.Pageable;


public interface CommentService {
    void comment(Rate rate);

    PageDTO getComment(Pageable pageable, Long productId);

    CommentTotalDTO getTotalComment(Long ProductId);

    int getCountComment(Long productId);


}
