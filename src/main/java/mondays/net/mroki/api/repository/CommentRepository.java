package mondays.net.mroki.api.repository;

import mondays.net.mroki.api.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(value = "SELECT id ,content , customer_id , product_id ,rate FROM comment WHERE product_id =?1", nativeQuery = true)
    Page<Comment> findCommentByProduct(Pageable pageable, Long productId);
}
