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


    @Override
    public List<ParentCategory> getAllCategory() {
        return null;
    }

    @Override
    public void save(CategoryDTO category) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Category update(Category category) {
        return null;
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
