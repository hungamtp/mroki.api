package mondays.net.mroki.api.controller.admin;

import lombok.AllArgsConstructor;
import mondays.net.mroki.api.dto.CategoryDTO;
import mondays.net.mroki.api.service.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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






}
