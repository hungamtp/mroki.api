package mondays.net.mroki.api.dto.categoryDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mondays.net.mroki.api.entity.Category;


import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParentCategory {

    private String id;

    private String name;

    Set<Category> subCategories = new HashSet<>();
}
