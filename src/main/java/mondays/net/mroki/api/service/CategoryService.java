package mondays.net.mroki.api.service;

import mondays.net.mroki.api.dto.CategoryDTO;
import mondays.net.mroki.api.entity.Category;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CategoryService {

    List<CategoryDTO> getAllCategory();

    Category save(Category category);

    void delete(String id);

    Category update(Category category);
    boolean isExist(String categoryId);

}
