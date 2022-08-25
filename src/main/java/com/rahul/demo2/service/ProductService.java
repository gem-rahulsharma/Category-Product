package com.rahul.demo2.service;

import com.rahul.demo2.controller.CategoryController;
import com.rahul.demo2.entity.Product;
import com.rahul.demo2.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

    public String saveData(Product product) {
      //  Product product1=null;
        try {
          Product  product1 = productRepository.save(product);
        } catch (Exception e){
            logger.error("Not found method"+e.getMessage());
        }
        return "Product saved successfully";
    }

    public List<Product> findAllData() {
        List<Product> list=new ArrayList<>();
        try{
            list=productRepository.findAll();
        } catch (Exception e){
            logger.error("Not found findAllData method"+e.getMessage());
        }
        return list;
    }

    public Product productById(int id) {
        Product product =null;
        try {
             product = productRepository.findById(id).get();
        } catch (Exception e){
            logger.error("Error in method"+e.getMessage());
        }
        return product;
    }

    public String deleteProduct( int id) {
        try {
            productRepository.deleteById(id);
            return "Success";
        } catch (Exception e){
            logger.error("Error in deleteCourse() method"+e.getMessage());
            return "Not deleted";

        }
    }

    public Product updateProduct(Product product, int id) {
            Product product1 = productRepository.findById(id).get();
            product1.setProductName(product.getProductName());
            product1.setProductDescription(product.getProductDescription());
            product1.setPrice(product.getPrice());
            product1.setCreatedAt(product.getCreatedAt());
            product1.setUpdatedAt(product.getUpdatedAt());
            product1.setActive(product.isActive());
            product1.setDeleted(product.isDeleted());
        return productRepository.save(product1);
    }


}
