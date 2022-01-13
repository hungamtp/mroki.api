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



}