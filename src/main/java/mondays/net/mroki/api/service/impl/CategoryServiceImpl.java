package mondays.net.mroki.api.service.impl;

import lombok.AllArgsConstructor;
import mondays.net.mroki.api.converter.CategoryConverter;
import mondays.net.mroki.api.dto.categoryDTO.CategoryDTO;
import mondays.net.mroki.api.dto.categoryDTO.ParentCategory;
import mondays.net.mroki.api.entity.Category;
import mondays.net.mroki.api.repository.CategoryRepository;
import mondays.net.mroki.api.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private final CategoryRepository categoryRepository;

    @Autowired
    private final CategoryConverter converter;

    public List<ParentCategory> getAllCategory() {
        return converter.entityTOParentDto(categoryRepository.findAllCate());
    }


    public Category save(Category category) {

        category.setDelete(true);
        return categoryRepository.save(category);

    }

    public boolean isExist(String categoryId) {
        return categoryRepository.checkExist(categoryId);
    }



    @Override
    public void delete(String id) {

        categoryRepository.deleteCategoryById(id);
    }

    @Override
    public Category update(Category category) {
        
        return categoryRepository.save(category);
    }

}
