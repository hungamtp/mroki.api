package mondays.net.mroki.api.repository;

import mondays.net.mroki.api.entity.CartDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CartDetailRepository extends JpaRepository<CartDetail , Long> {

    @Query(value = "SELECT CASE WHEN count(id) > 0 THEN true ELSE false END checkExist " +
            "FROM cart where customer_id =?1 limit 1", nativeQuery = true)
    boolean isCartExist(Long customerId);

}
