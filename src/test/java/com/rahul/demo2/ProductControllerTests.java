package com.rahul.demo2;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.rahul.demo2.controller.ProductController;
import com.rahul.demo2.entity.Product;
import com.rahul.demo2.service.ProductService;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.print.attribute.standard.Media;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ComponentScan(basePackages = "com.rahul.demo2")
@AutoConfigureMockMvc
@ContextConfiguration
@SpringBootTest(classes = {ProductControllerTests.class})
public class ProductControllerTests {
    @Autowired
    MockMvc mockMvc;

    @Mock
    ProductService productService;

    @InjectMocks
    ProductController productController;

    List<Product> my_products;

    @BeforeEach
    public void setUp() {
        mockMvc= MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    @Order(1)
    public void test_showProduct() throws Exception
    {
        my_products=new ArrayList<Product>();
        my_products.add(new Product(3,"Honda","Black",25000,LocalDate.now(),LocalDate.now(),false,false));

        when(productService.findAllData()).thenReturn(my_products);//mocking

        this.mockMvc.perform(MockMvcRequestBuilders.get("/product/view"))
                .andExpect(status().isOk())
                .andDo(print());

    }
    @Test
    @Order(2)
    public void test_showProductById() throws Exception
    {
       Product product = new Product(3,"Honda","Black",25000,LocalDate.now(),LocalDate.now(),false,false);
        int productId=3;
        when(productService.productById(productId)).thenReturn(product);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/product/{id}",productId))
                .andExpect(status().isOk())
                .andDo(print());
    }
    @Test
    @Order(3)
    public void test_saveProduct() throws Exception
    {
       Product product=new Product(3,"Honda","Black",25000,LocalDate.now(),LocalDate.now(),false,false);

        when(productService.saveData(product)).thenReturn(String.valueOf(product));

        ObjectMapper mapper=new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        String jsonbody=mapper.writeValueAsString(product);
        this.mockMvc.perform(post("/product/save").content(jsonbody)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }
    @Test
    @Order(4)
    public void test_deleteProduct() throws Exception
    {
       Product product = new Product(3,"Honda","Black",25000,LocalDate.now(),LocalDate.now(),false,false);
        int productId=3;

        String response="Success";
        when(productService.deleteProduct(productId)).thenReturn(response);
        this.mockMvc.perform(delete("/product/{id}",productId))
                .andExpect(status().isOk());

    }
    @Test
    @Order(5)
    public void test_updateProduct() throws Exception
    {
        Product product=new Product(3,"Honda","Black",25000,LocalDate.now(),LocalDate.now(),false,false);
        int productId=3;
        when(productService.productById(productId)).thenReturn(product);
        when(productService.updateProduct(product,productId)).thenReturn(product);
        ObjectMapper mapper=new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        String jsonbody=mapper.writeValueAsString(product);
        this.mockMvc.perform(put("/product/{id}",productId).content(jsonbody).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

}
