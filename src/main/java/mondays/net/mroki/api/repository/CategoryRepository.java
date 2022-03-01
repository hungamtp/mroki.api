package mondays.net.mroki.api.repository;

import mondays.net.mroki.api.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findByParentIsNullAndIsDeletedFalse();
    List<Category> findByParentIsNull();

    Optional<Category> findByName(String name);


}
