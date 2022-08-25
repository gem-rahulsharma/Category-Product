package com.rahul.demo2;

import com.rahul.demo2.entity.Category;
import com.rahul.demo2.entity.Product;
import com.rahul.demo2.repository.CategoryRepository;
import com.rahul.demo2.repository.ProductRepository;
import com.rahul.demo2.service.CategoryService;
import com.rahul.demo2.service.ProductService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ControllerServiceTests {
    @Mock
    CategoryRepository categoryRepository;

    @InjectMocks
    CategoryService categoryService;
    @Test
    @Order(1)
    public void test_getAllCategory() {
        List<Category> list = new ArrayList<Category>();
        Category category=new Category(3, "Honda", "Black", LocalDate.now(), LocalDate.now(), false, false);
        Category  category1=new Category(4, "Bajaj", "Black", LocalDate.now(), LocalDate.now(), false, false);
        list.add(category);
        list.add(category1);
        Mockito.when(categoryRepository.findAll()).thenReturn(list);//mocking
        List<Category> list1=categoryService.findAllCategoryData();
        assertEquals(2, list1.size());
    }
    @Test
    @Order(2)
    public void test_getCategory() {
        Category category=new Category(3, "Honda", "Black", LocalDate.now(), LocalDate.now(), false, false);
        when(categoryRepository.findById(any())).thenReturn(Optional.of(category));

        Category category1 = categoryService.categoryById(3);

        assertEquals(category.getCategoryId(), category1.getCategoryId());
    }
    @Test
    @Order(3)
    public void test_saveCategory() {
        Category category=new Category(3, "Honda", "Black", LocalDate.now(), LocalDate.now(), false, false);
        when(categoryRepository.save(any())).thenReturn(category);
        String category1 = categoryService.saveCategoryData(category);
        assertEquals(category1,"data saved successfully");

    }
    @Test
    @Order(4)
    public void test_UpdateCategory() {
        Category category=new Category(3, "Honda", "Black", LocalDate.now(), LocalDate.now(), false, false);

        when(categoryRepository.findById(any())).thenReturn(Optional.of(category));
        when(categoryRepository.save(any())).thenReturn(category);
        Category category1 = categoryService.categoryById(3);

        assertEquals(category,category1);
    }
    @Test
    @Order(5)
    public void test_deleteCategory() {
        Category category=new Category(3, "Honda", "Black", LocalDate.now(), LocalDate.now(), false, false);
        categoryService.deleteCategory(category.getCategoryId());
        Mockito.verify(categoryRepository, times(1)).findById(Mockito.anyInt());
    }
}
