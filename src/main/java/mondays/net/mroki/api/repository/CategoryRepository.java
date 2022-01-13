package mondays.net.mroki.api.repository;

import mondays.net.mroki.api.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;


@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {




}
