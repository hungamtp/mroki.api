package mondays.net.mroki.api.controller.admin;

import lombok.AllArgsConstructor;
import mondays.net.mroki.api.dto.CategoryDTO;
import mondays.net.mroki.api.entity.Category;
import mondays.net.mroki.api.service.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("admin/category")
@AllArgsConstructor
public class CategoryAdmin {

    @Autowired
    private final CategoryServiceImpl categoryService;

    @PostMapping
    public void addCategory(@Valid @RequestBody CategoryDTO category){

        categoryService.save(category);

    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable String id){

        if(!Optional.ofNullable(id).isPresent()){
            throw new RuntimeException("Id is null");
        }

        categoryService.delete(id);

    }

    @PutMapping
    public void updateCategory(@Valid @RequestBody CategoryDTO categoryDTO){

        categoryService.update(categoryDTO);

    }
}
