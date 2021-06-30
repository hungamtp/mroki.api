package mondays.net.mroki.api.service;

import lombok.AllArgsConstructor;
import mondays.net.mroki.api.entity.Category;
import mondays.net.mroki.api.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {

    @Autowired
    private final CategoryRepository categoryRepository;

    public List<Category> getAllCategory(){
        return categoryRepository.findAll();
    }
}
