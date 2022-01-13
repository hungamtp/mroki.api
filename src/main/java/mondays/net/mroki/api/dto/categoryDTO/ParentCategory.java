package mondays.net.mroki.api.dto.categoryDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mondays.net.mroki.api.entity.Category;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParentCategory {

    private Long id;

    private String name;

    List<Category> subCategories = new ArrayList<>();
}
