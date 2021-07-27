package mondays.net.mroki.api.converter;

import mondays.net.mroki.api.dto.categoryDTO.CategoryDTO;
import mondays.net.mroki.api.dto.categoryDTO.ParentCategory;
import mondays.net.mroki.api.entity.Category;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CategoryConverter {

    public CategoryDTO entityToDto(Category category) {

        return CategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    public List<CategoryDTO> entityToDto(List<Category> categories) {

        return categories.stream()
                .map(category -> entityToDto(category))
                .collect(Collectors.toList());
    }

    public Category dtoToEntity(CategoryDTO dto) {
        return Category.builder()
                .id(dto.getId())
                .name(dto.getName())
                .build();
    }

    public ParentCategory entityToParentDto(Category category) {
        Set<Category> subCate = new HashSet<>();

       List<Category> categories =category.getSubCategories().stream().filter((cate) -> !cate.isDelete()).collect(Collectors.toList());

       categories.stream().forEach((category1 -> subCate.add(category1)));

        return ParentCategory.builder()
                .id(category.getId())
                .subCategories(subCate)
                .name(category.getName())
                .build();
    }

    public List<ParentCategory> entityTOParentDto(List<Category> categories) {
        return categories.stream().
                map((category -> entityToParentDto(category)))
                .collect(Collectors.toList());
    }


}
