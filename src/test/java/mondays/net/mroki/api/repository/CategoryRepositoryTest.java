package mondays.net.mroki.api.repository;

import mondays.net.mroki.api.entity.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CategoryRepositoryTest {


    @Autowired
    private  CategoryRepository repo;

    @Test
    public void isExist(){
        repo.save(Category.builder().id("C").name("CHECK").build());
        boolean  check = repo.checkExist("C");
        assertEquals(check , true);
    }

    @Test
    public void deleteCategory(){
        repo.save(Category.builder().id("C").name("CHECK").delete(false).build());
        repo.deleteCategoryById("C");
        assertEquals(true , repo.findById("C").get().isDelete());
    }


}