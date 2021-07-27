package mondays.net.mroki.api.service;

import mondays.net.mroki.api.dto.categoryDTO.CategoryDTO;
import mondays.net.mroki.api.dto.categoryDTO.ParentCategory;
import mondays.net.mroki.api.entity.Category;

import java.util.List;


public interface CategoryService {

    List<ParentCategory> getAllCategory();

    void save(CategoryDTO category);

    void delete(String id);

    Category update(Category category);

    boolean isExist(String categoryId);

    List<CategoryDTO> getALlSubcategory();

    CategoryDTO getCategoryById(String id);



}
