package com.rahul.demo2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.rahul.demo2.controller.CategoryController;
import com.rahul.demo2.controller.ProductController;
import com.rahul.demo2.entity.Category;
import com.rahul.demo2.entity.Product;
import com.rahul.demo2.service.CategoryService;
import com.rahul.demo2.service.ProductService;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ComponentScan(basePackages = "com.rahul.demo2")
@AutoConfigureMockMvc
@ContextConfiguration
@SpringBootTest(classes = {CategoryControllerTests.class})
public class CategoryControllerTests {

    @Autowired
    MockMvc mockMvc;

    @Mock
    CategoryService categoryService;

    @InjectMocks
    CategoryController categoryController;

    List<Category> my_categories;

    @BeforeEach
    public void setUp() {
        mockMvc= MockMvcBuilders.standaloneSetup(categoryController).build();
    }

    @Test
    @Order(1)
    public void test_showProduct() throws Exception {
        my_categories = new ArrayList<Category>();
        my_categories.add(new Category(3, "FourWheeler", "Black", LocalDate.now(), LocalDate.now(), false, false));

        when(categoryService.findAllCategoryData()).thenReturn(my_categories);//mocking

        this.mockMvc.perform(MockMvcRequestBuilders.get("/category/show"))
                .andExpect(status().isOk())
                .andDo(print());
    }
    @Test
    @Order(2)
    public void test_showProductById() throws Exception
    {
        Category category = new Category(3,"Honda","Black",LocalDate.now(),LocalDate.now(),false,false);
        int categoryId=3;
        when(categoryService.categoryById(categoryId)).thenReturn(category);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/category/{id}",categoryId))
                .andExpect(status().isOk())
                .andDo(print());
    }
    @Test
    @Order(3)
    public void test_saveProduct() throws Exception
    {
        Category category=new Category(3,"Honda","Black",LocalDate.now(),LocalDate.now(),false,false);

        when(categoryService.saveCategoryData(category)).thenReturn(String.valueOf(category));

        ObjectMapper mapper=new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        String jsonbody=mapper.writeValueAsString(category);
        this.mockMvc.perform(post("/category/save").content(jsonbody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }
    @Test
    @Order(4)
    public void test_deleteProduct() throws Exception
    {
        Category category = new Category(3,"Honda","Black",LocalDate.now(),LocalDate.now(),false,false);
        int categoryId=3;
        String response ="Success";

        when(categoryService.deleteCategory(categoryId)).thenReturn(response);

        this.mockMvc.perform(delete("/category/{id}",categoryId))
                .andExpect(status().isOk());

    }
    @Test
    @Order(5)
    public void test_updateProduct() throws Exception
    {
        Category category=new Category(3,"Honda","Black",LocalDate.now(),LocalDate.now(),false,false);
        int categoryId=3;
        when(categoryService.categoryById(categoryId)).thenReturn(category);
        when(categoryService.updateCategory(category,categoryId)).thenReturn(category);
        ObjectMapper mapper=new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        String jsonbody=mapper.writeValueAsString(category);
        this.mockMvc.perform(put("/category/{id}",categoryId).content(jsonbody).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }
}

