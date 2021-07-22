package mondays.net.mroki.api.dto;

import lombok.Data;
import mondays.net.mroki.api.entity.Category;

import java.util.List;
import java.util.ArrayList;

@Data
public class ParentCategory {

    private Long id;

    private String name;

    List<Category> subCategories = new ArrayList<>();
}
