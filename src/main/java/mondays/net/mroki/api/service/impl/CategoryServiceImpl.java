package mondays.net.mroki.api.service.impl;

import lombok.AllArgsConstructor;
import mondays.net.mroki.api.dto.CategoryDTO;
import mondays.net.mroki.api.entity.Category;
import mondays.net.mroki.api.repository.CategoryRepository;
import mondays.net.mroki.api.service.CategoryService;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private final CategoryRepository categoryRepository;

    public List<CategoryDTO> getAllCategory() {
        return convertEntityToCategoryDTO(categoryRepository.findAllCategory());
    }

    private List<CategoryDTO> convertEntityToCategoryDTO(List<Object[]> categories) {
        List<CategoryDTO> result = new ArrayList<>();

        categories.stream().forEach((category) -> {
            String id = (String) category[0];
            String name = (String) category[1];
            result.add(CategoryDTO.builder().id(id).name(name).build());
        });

        return result;
    }

    public void save(CategoryDTO categoryDTO) {

        Category category = Category.builder()
                .id(categoryDTO.getId())
                .name(categoryDTO.getName())
                .build();

        categoryRepository.save(category);

    }

    @Override
    public void delete(String id) {

        if (categoryRepository.findAllCategory().isEmpty()) {
            throw new IllegalIdentifierException("Id is not exist");
        }

        categoryRepository.deleteById(id);
    }

    @Override
    public void update(CategoryDTO categoryDTO) {

        if (!Optional.ofNullable(categoryRepository.getId(categoryDTO.getId())).isPresent())
            throw new IllegalIdentifierException("ID is not exist");

        Category category = Category.builder()
                .id(categoryDTO.getId())
                .name(categoryDTO.getName())
                .build();

        categoryRepository.save(category);

    }
}
