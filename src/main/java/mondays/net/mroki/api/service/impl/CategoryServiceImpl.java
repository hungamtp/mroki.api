package mondays.net.mroki.api.service.impl;

import lombok.AllArgsConstructor;
import mondays.net.mroki.api.dto.categoryDTO.CategoryAddDTO;
import mondays.net.mroki.api.dto.categoryDTO.CategoryDTO;
import mondays.net.mroki.api.dto.categoryDTO.ParentCategory;
import mondays.net.mroki.api.dto.categoryDTO.UpdateCategoryDTO;
import mondays.net.mroki.api.entity.Category;
import mondays.net.mroki.api.repository.CategoryRepository;
import mondays.net.mroki.api.responseCode.ErrorCode;
import mondays.net.mroki.api.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public List<ParentCategory> getAllCategory() {
        List<Category> categories = categoryRepository.findByParentIsNullAndIsDeletedFalse();
        List<ParentCategory> parentCategories = new ArrayList<>();

        for(Category category : categories){
            parentCategories.add(new ParentCategory(category.getId() , category.getName(),category.isDeleted(), category.getSubCategories()));
        }
        return parentCategories;
    }

    @Override
    public List<ParentCategory> getAllCategoryAdmin() {
        List<Category> categories = categoryRepository.findByParentIsNull();
        List<ParentCategory> parentCategories = new ArrayList<>();

        for(Category category : categories){
            parentCategories.add(new ParentCategory(category.getId() , category.getName(),category.isDeleted() ,  category.getSubCategories()));
        }
        return parentCategories;
    }

    @Override
    public void save(CategoryAddDTO category) {
        Optional<Category> category2 = categoryRepository.findByName(category.getName());
        if(category2.isPresent()) {
            throw new IllegalStateException(ErrorCode.CATEGORY_NAME_EXIST);
        }
        Category category1 = new Category();
        category1.setName(category.getName());
        category1.setDeleted(false);
        categoryRepository.save(category1);
    }

    @Override
    public void unDelete(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        category.orElseThrow(
                () -> new IllegalStateException(ErrorCode.CATEGORY_NOT_FOUND)
        );
        Category category1 = category.get();
        category1.setDeleted(false);
        categoryRepository.save(category1);
    }

    @Override
    public void update(Long id, String name) {
        Optional<Category> category = categoryRepository.findById(id);
        category.orElseThrow(
                () -> new IllegalStateException(ErrorCode.CATEGORY_NOT_FOUND)
        );
        Category category1 = category.get();
        category1.setName(name);
        categoryRepository.save(category1);
    }


    @Override
    public void delete(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        category.orElseThrow(
                () -> new IllegalStateException(ErrorCode.CATEGORY_NOT_FOUND)
        );
        Category category1 = category.get();
        category1.setDeleted(true);
        categoryRepository.save(category1);
    }



    @Override
    public void update(UpdateCategoryDTO category) {
        Optional<Category> category1 = categoryRepository.findById(category.getId());
        category1.orElseThrow(
                () -> new IllegalStateException(ErrorCode.CATEGORY_NOT_FOUND)
        );
        Category category2 = category1.get();
        category2.setDeleted(category.isDeleted());
        category2.setName(category.getName());
        categoryRepository.save(category2);
    }

    @Override
    public boolean isExist(String categoryId) {
        return false;
    }

    @Override
    public List<CategoryDTO> getALlSubcategory() {
        return null;
    }

    @Override
    public CategoryDTO getCategoryById(String id) {
        return null;
    }
}
