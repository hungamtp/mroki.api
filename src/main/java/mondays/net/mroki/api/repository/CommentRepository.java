package mondays.net.mroki.api.repository;

import mondays.net.mroki.api.entity.Comment;
import mondays.net.mroki.api.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    Page<Comment> findByProduct(Pageable pageable, Product productId);
}
