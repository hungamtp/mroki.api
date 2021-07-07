package mondays.net.mroki.api.service;

import mondays.net.mroki.api.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {
    public List<CategoryDTO> getAllCategory();
    public void save(CategoryDTO categoryDTO);
}
