package mondays.net.mroki.api.repository;

import mondays.net.mroki.api.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {

    @Query(value = "SELECT id , name FROM category", nativeQuery = true)
    List<Object[]> findAllCategory();

    Optional<Category> findById(String id);

    @Query(value = "SELECT id FROM category WHERE id = ?1", nativeQuery = true)
    String getId(String id);
}
