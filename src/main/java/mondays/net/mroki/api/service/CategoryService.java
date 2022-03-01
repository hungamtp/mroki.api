package mondays.net.mroki.api.service;

import mondays.net.mroki.api.dto.categoryDTO.CategoryAddDTO;
import mondays.net.mroki.api.dto.categoryDTO.CategoryDTO;
import mondays.net.mroki.api.dto.categoryDTO.ParentCategory;
import mondays.net.mroki.api.entity.Category;

import java.util.List;


public interface CategoryService {

    List<ParentCategory> getAllCategory();

    void save(CategoryAddDTO category);

    void delete(Long id);

    Category update(Category category);

    boolean isExist(String categoryId);

    List<CategoryDTO> getALlSubcategory();

    CategoryDTO getCategoryById(String id);



}
