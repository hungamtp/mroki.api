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


    @Query(value = "SELECT CASE WHEN count(id) > 0 THEN true ELSE false END checkExist " +
            "FROM category where id = ?1  limit 1", nativeQuery = true)
    boolean checkExist(String id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE category SET delete = true WHERE id = ?1 ", nativeQuery = true)
    void deleteCategoryById(String id);

    @Query(value = "SELECT id , name , delete , parent_id FROM category "+
            "WHERE delete = false AND parent_id IS NULL" , nativeQuery = true)
    List<Category> findAllCate();


    @Query(value = "SELECT id , name , delete , parent_id FROM category "+
            "WHERE delete = false AND parent_id IS NOT NULL" , nativeQuery = true)
    List<Category> findAllSubCate();

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO category VALUES(?1 ,?2 , false , ?3)" ,nativeQuery = true)
    void addSubCategory(String categoryId , String name ,String parentId);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO category VALUES(?1 ,?2 , false )" ,nativeQuery = true)
    void addParentCategory(String categoryId , String name);


}
