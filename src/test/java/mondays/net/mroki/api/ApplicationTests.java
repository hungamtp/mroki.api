package mondays.net.mroki.api;

import mondays.net.mroki.api.entity.Category;
import mondays.net.mroki.api.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ApplicationTests {

	@Autowired
	private CategoryRepository repo;

	@Test
	void contextLoads() {
	}
	@Test
	public void getALlCate(){
		List<Category> categories = repo.findByParentIsNullAndIsDeletedFalse();
		for (var cate : categories){
			System.out.println(cate.getName());
		}
	}

}
