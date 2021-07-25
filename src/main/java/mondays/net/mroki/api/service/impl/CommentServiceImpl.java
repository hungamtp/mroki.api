package mondays.net.mroki.api.service.impl;


import lombok.AllArgsConstructor;
import mondays.net.mroki.api.converter.CommentConverter;
import mondays.net.mroki.api.dto.PageDTO;
import mondays.net.mroki.api.dto.commentDTO.CommentTotalDTO;
import mondays.net.mroki.api.entity.Comment;
import mondays.net.mroki.api.entity.Product;
import mondays.net.mroki.api.exception.CommentConvertException;
import mondays.net.mroki.api.repository.CommentRepository;
import mondays.net.mroki.api.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {

    @Autowired
    private final CommentRepository commentRepository;

    @Autowired
    private final CommentConverter converter;

    public void comment(Comment comment) {

        commentRepository.save(comment);
    }

    public PageDTO getComment(Pageable pageable, Long productId) {

        Product product = Product.builder()
                .id(productId)
                .build();

        return converter.entityToDto(commentRepository.findByProduct(pageable, product));

    }

    public CommentTotalDTO getTotalComment(Long productId) {
        List<Object[]> totalAndAvgRate = commentRepository.totalComment(productId);

        List<Integer> listRate = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            listRate.add(commentRepository.getCountRate(productId, i));
        }

        try {
            if (totalAndAvgRate.size() == 0) {
                CommentTotalDTO commentTotalDTO = CommentTotalDTO.builder()
                        .rate(0)
                        .count(0)
                        .countRate1(listRate.get(0))
                        .countRate2(listRate.get(1))
                        .countRate3(listRate.get(2))
                        .countRate4(listRate.get(3))
                        .countRate5(listRate.get(4))
                        .build();
                return commentTotalDTO;
            }
            CommentTotalDTO commentTotalDTO = CommentTotalDTO.builder()
                    .rate(((BigDecimal) totalAndAvgRate.get(0)[1]).floatValue())
                    .count(((BigInteger) totalAndAvgRate.get(0)[0]).intValue())
                    .countRate1(listRate.get(0))
                    .countRate2(listRate.get(1))
                    .countRate3(listRate.get(2))
                    .countRate4(listRate.get(3))
                    .countRate5(listRate.get(4))
                    .build();
            return commentTotalDTO;
        } catch (CommentConvertException ex) {

            System.out.println(ex.getMessage());
            return null;

        }

    }

    public int getCountComment(Long productId) {
        return commentRepository.getCountComment(productId);
    }

}
