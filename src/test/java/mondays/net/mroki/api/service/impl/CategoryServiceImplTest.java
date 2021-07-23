package mondays.net.mroki.api.service.impl;

import mondays.net.mroki.api.entity.Category;
import mondays.net.mroki.api.repository.CategoryRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class CategoryServiceImplTest {

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Mock
    private CategoryRepository repository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void save(){
        Category data = Category.builder()
                .id("D")
                .name("DEMO")
                .build();

        Category expect = Category.builder()
                .id("D")
                .name("DEMO")
                .delete(false)
                .build();

        when(repository.save(data)).thenReturn(expect);

        assertEquals(categoryService.save(data) , expect);
    }
    @Test
    public void saveFail(){
        Category data = Category.builder()
                .id("D")
                .name("DEMO")
                .build();

        Category expect = Category.builder()
                .id("D")
                .name("DEMO")
                .delete(false)
                .build();

        when(repository.save(data)).thenReturn(expect);

        assertEquals(categoryService.save(data) , expect);
    }


}