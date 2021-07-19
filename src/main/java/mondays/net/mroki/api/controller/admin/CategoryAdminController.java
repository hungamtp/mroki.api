package mondays.net.mroki.api.controller.admin;

import lombok.AllArgsConstructor;
import mondays.net.mroki.api.converter.CategoryConverter;
import mondays.net.mroki.api.dto.CategoryDTO;
import mondays.net.mroki.api.dto.ResponseDTO;
import mondays.net.mroki.api.entity.Category;
import mondays.net.mroki.api.exception.CategoryConverterException;
import mondays.net.mroki.api.responseCode.ErrorCode;
import mondays.net.mroki.api.responseCode.SuccessCode;
import mondays.net.mroki.api.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("admin/category")
@AllArgsConstructor
@CrossOrigin
public class CategoryAdminController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryConverter converter;



    @PostMapping
    public ResponseEntity<ResponseDTO> addCategory(@Valid @RequestBody CategoryDTO categoryDTO) {

        ResponseDTO response = new ResponseDTO();

        if (categoryService.isExist(categoryDTO.getId())) {
            response.setErrorCode(ErrorCode.ID_IS_EXISTS);
            return ResponseEntity.badRequest().body(response);
        }

        Category category;

        try {

            category = converter.dtoToEntity(categoryDTO);
            categoryService.save(category);
            response.setSuccessCode(SuccessCode.ADD_CATEGORY);

        } catch (CategoryConverterException exception) {
            throw new CategoryConverterException("CONVERT CATEGORY ERROR");
        }

        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ResponseDTO> deleteCategory(@PathVariable String categoryId) {

        ResponseDTO response = new ResponseDTO();

        if (!Optional.ofNullable(categoryId).isPresent())
            throw new RuntimeException("Id is null");


        if (!categoryService.isExist(categoryId)) {
            response.setErrorCode(ErrorCode.ID_CATEGORY_NOT_FOUND);
            return ResponseEntity.badRequest().body(response);
        } else {
            response.setSuccessCode(SuccessCode.DELETE_CATEGORY);
            categoryService.delete(categoryId);
            return ResponseEntity.ok().body(response);
        }

    }

    @PutMapping
    public ResponseEntity<ResponseDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO) {

        ResponseDTO response = new ResponseDTO();

        if (!categoryService.isExist(categoryDTO.getId())) {
            response.setErrorCode(ErrorCode.ID_CATEGORY_NOT_FOUND);
            return ResponseEntity.badRequest().body(response);
        }

        response.setSuccessCode(SuccessCode.UPDATE_CATEGORY);
        categoryService.update(converter.dtoToEntity(categoryDTO));

        return ResponseEntity.ok().body(response);


    }

}
