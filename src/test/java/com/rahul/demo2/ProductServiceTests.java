package com.rahul.demo2;
import com.rahul.demo2.entity.Product;
import com.rahul.demo2.repository.ProductRepository;
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
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductServiceTests {

    @Mock
    ProductRepository productRepository1;

    @InjectMocks
    ProductService productService;


    @Test
    @Order(1)
    public void test_getAllProducts() {
        List<Product> list = new ArrayList<Product>();
        Product product=new Product(3, "Honda", "Black", 250000, LocalDate.now(), LocalDate.now(), false, false);
        Product  product1=new Product(4, "Bajaj", "Black", 450000, LocalDate.now(), LocalDate.now(), false, false);
        list.add(product);
        list.add(product1);
        Mockito.when(productRepository1.findAll()).thenReturn(list);//mocking
        List<Product> list1=productService.findAllData();
        assertEquals(2, list1.size());
    }
    @Test
    @Order(2)
    public void test_getProduct() {
        Product product=new Product(3, "Honda", "Black", 250000, LocalDate.now(), LocalDate.now(), false, false);
        when(productRepository1.findById(any())).thenReturn(Optional.of(product));

        Product product1 = productService.productById(3);

        assertEquals(product.getProductId(), product1.getProductId());
    }
    @Test
    @Order(3)
    public void test_saveProduct() {
        Product product=new Product(3, "Honda", "Black", 250000, LocalDate.now(), LocalDate.now(), false, false);
        when(productRepository1.save(any())).thenReturn(product);
        String product2 = productService.saveData(product);
        assertEquals(product2,"Product saved successfully");

    }
    @Test
    @Order(4)
    public void test_UpdateProduct() {
        Product product=new Product(3, "Honda", "Black", 250000, LocalDate.now(), LocalDate.now(), false, false);

        when(productRepository1.findById(any())).thenReturn(Optional.of(product));
        when(productRepository1.save(any())).thenReturn(product);
        Product product1 = productService.productById(3);

        assertEquals(product,product1);
    }
    @Test
    @Order(5)
    public void test_deleteProduct() {
        Product product=new Product(3, "Honda", "Black", 250000, LocalDate.now(), LocalDate.now(), false, false);
        productService.deleteProduct(product.getProductId());
        Mockito.verify(productRepository1, times(1)).deleteById(Mockito.anyInt());



    }


}

