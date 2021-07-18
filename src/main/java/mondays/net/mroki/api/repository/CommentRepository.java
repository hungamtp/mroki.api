package mondays.net.mroki.api.repository;

import mondays.net.mroki.api.entity.Comment;
import mondays.net.mroki.api.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    Page<Comment> findByProduct(Pageable pageable, Product productId);

    @Query(value = "SELECT count(id) as count, avg(rate) as rate "+
            "FROM comment WHERE product_id =?1 "+
            "GROUP BY product_id" , nativeQuery = true)
    List<Object[]> totalComment(Long productId);

    @Query(value = "SELECT count(id) as count  FROM comment WHERE product_id = ? AND rate = ?" , nativeQuery = true)
    int getCountRate(Long productId , int rate);
}
