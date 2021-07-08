package mondays.net.mroki.api.controller.customer;


import lombok.AllArgsConstructor;
import mondays.net.mroki.api.dto.CategoryDTO;
import mondays.net.mroki.api.service.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("user/category")
@AllArgsConstructor
@CrossOrigin
public class CategoryController {

    @Autowired
    private final CategoryServiceImpl categoryService;

    @GetMapping
    public List<CategoryDTO> getAllCategory() {
        return categoryService.getAllCategory();
    }
}
