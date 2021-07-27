package mondays.net.mroki.api.controller.customer;


import lombok.AllArgsConstructor;
import mondays.net.mroki.api.dto.ResponseDTO;
import mondays.net.mroki.api.dto.categoryDTO.CategoryDTO;
import mondays.net.mroki.api.dto.categoryDTO.ParentCategory;
import mondays.net.mroki.api.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user/category")
@AllArgsConstructor
@CrossOrigin
public class CategoryController {

    @Autowired
    private final CategoryService categoryService;

    @GetMapping
    public List<ParentCategory> getAllCategory() {
        return categoryService.getAllCategory();
    }

    @GetMapping("/sub")
    public ResponseEntity<ResponseDTO> getAllSubcategory(){
        ResponseDTO response = new ResponseDTO();
        response.setData(categoryService.getALlSubcategory());
        return ResponseEntity.ok().body(response);
    }
}
