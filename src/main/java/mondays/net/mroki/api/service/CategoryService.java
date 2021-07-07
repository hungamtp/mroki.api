package mondays.net.mroki.api.service;

import mondays.net.mroki.api.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {

    List<CategoryDTO> getAllCategory();

    void save(CategoryDTO categoryDTO);

    void delete(String id);

    void update(CategoryDTO categoryDTO);

}
