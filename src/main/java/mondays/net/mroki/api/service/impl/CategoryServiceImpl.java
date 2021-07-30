package mondays.net.mroki.api.service.impl;

import lombok.AllArgsConstructor;
import mondays.net.mroki.api.converter.CategoryConverter;
import mondays.net.mroki.api.dto.categoryDTO.CategoryDTO;
import mondays.net.mroki.api.dto.categoryDTO.ParentCategory;
import mondays.net.mroki.api.entity.Category;
import mondays.net.mroki.api.exception.DataNotFoundException;
import mondays.net.mroki.api.exception.DuplicatedDataException;
import mondays.net.mroki.api.repository.CategoryRepository;
import mondays.net.mroki.api.responseCode.ErrorCode;
import mondays.net.mroki.api.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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


    public void save(CategoryDTO category) {

        if (isExist(category.getId())) {
            throw new DuplicatedDataException(ErrorCode.CATEGORY_ID_NOT_AVAILABLE);
        }

        if (category.getParentId() == null)
            categoryRepository.addParentCategory(category.getId(), category.getName());
        else
            categoryRepository.addSubCategory(category.getId(), category.getName(), category.getParentId());

    }

    public boolean isExist(String categoryId) {
        return categoryRepository.checkExist(categoryId);
    }

    @Override
    public List<CategoryDTO> getALlSubcategory() {
        return converter.entityToDto(categoryRepository.findAllSubCate());
    }

    @Override
    public CategoryDTO getCategoryById(String id) {
        Optional<Category> category = categoryRepository.findById(id);

        if(!category.isPresent())
            throw new DataNotFoundException(ErrorCode.CATEGORY_NOT_FOUND);

        return converter.entityToDto(category.get());
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
