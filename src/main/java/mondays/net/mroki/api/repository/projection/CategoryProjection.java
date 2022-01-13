package mondays.net.mroki.api.repository.projection;

import mondays.net.mroki.api.dto.categoryDTO.SubCategoryDTO;

import java.util.List;

public class CategoryProjection {
    private Long id;
    private String name;
    List<SubCategoryDTO> subCategoryDTOS;
}
