package mondays.net.mroki.api.converter;

import mondays.net.mroki.api.dto.CategoryDTO;
import mondays.net.mroki.api.entity.Category;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryConverter {

    public CategoryDTO entityToDto(Category category){

        return CategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    public List<CategoryDTO> entityToDto(List<Category> categories){

        return categories.stream()
                .map(category -> entityToDto(category))
                .collect(Collectors.toList());
    }

    public Category dtoToEntity(CategoryDTO dto){

        return Category.builder()
                .id(dto.getId())
                .name(dto.getName())
                .build();
    }

}
