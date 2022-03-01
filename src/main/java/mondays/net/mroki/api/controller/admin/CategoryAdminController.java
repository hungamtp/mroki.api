package mondays.net.mroki.api.controller.admin;

import lombok.AllArgsConstructor;
import mondays.net.mroki.api.converter.CategoryConverter;
import mondays.net.mroki.api.dto.ResponseDTO;
import mondays.net.mroki.api.dto.categoryDTO.CategoryAddDTO;
import mondays.net.mroki.api.exception.CategoryConverterException;
import mondays.net.mroki.api.responseCode.ErrorCode;
import mondays.net.mroki.api.responseCode.SuccessCode;
import mondays.net.mroki.api.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin/category")
@AllArgsConstructor
@CrossOrigin
@PreAuthorize("hasRole('ADMIN')")
public class CategoryAdminController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryAdminController.class);

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryConverter converter;


    @GetMapping("/{id}")
    ResponseEntity<ResponseDTO> getCategoryById(@PathVariable String id) {
        ResponseDTO response = new ResponseDTO();

        response.setData(categoryService.getCategoryById(id));
        return ResponseEntity.ok().body(response);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> addCategory(@RequestBody CategoryAddDTO categoryDTO) {

        ResponseDTO response = new ResponseDTO();
        try {

            categoryService.save(categoryDTO);
            response.setSuccessCode(SuccessCode.ADD_CATEGORY_SUCCESS);

        } catch (CategoryConverterException exception) {
            throw new CategoryConverterException(ErrorCode.CONVERT_CATEGORY_ERROR);
        }

        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ResponseDTO> deleteCategory(@PathVariable Long categoryId) {

        ResponseDTO response = new ResponseDTO();

        try {
            response.setSuccessCode(SuccessCode.DELETE_CATEGORY_SUCCESS);
            categoryService.delete(categoryId);
            return ResponseEntity.ok().body(response);
        } catch (IllegalStateException e) {
            response.setErrorCode(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }

    }

//    @PutMapping
//    public ResponseEntity<ResponseDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
//
//        ResponseDTO response = new ResponseDTO();
//        try {
//            if (!categoryService.isExist(categoryDTO.getId())) {
//                response.setErrorCode(ErrorCode.ID_CATEGORY_NOT_FOUND);
//                return ResponseEntity.badRequest().body(response);
//            }
//
//            response.setSuccessCode(SuccessCode.UPDATE_CATEGORY_SUCCESS);
//            categoryService.update(converter.dtoToEntity(categoryDTO));
//
//            return ResponseEntity.ok().body(response);
//
//
//        } catch (Exception e) {
//            response.setErrorCode(ErrorCode.UPDATE_CATEGORY_FAIL);
//            return ResponseEntity.badRequest().body(response);
//        }
//
//
//    }
}
