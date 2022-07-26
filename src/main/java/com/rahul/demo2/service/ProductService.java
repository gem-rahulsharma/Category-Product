package com.rahul.demo2.service;

import com.rahul.demo2.entity.Product;
import com.rahul.demo2.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public String saveData(Product product)
    {
        productRepository.save(product);
        return  "data saved successfully";
    }
    public List<Product> findAllData()
    {
        return (List<Product>) productRepository.findAll();
    }
    public Product productById(int id)
    {
        Optional<Product> product=productRepository.findById(id);
        return  product.get();
    }
    public List<Product> deleteProduct(int id)
    {
        productRepository.deleteById(id);
        return productRepository.findAll();
    }
    public void updateProduct(Product product,int id)
    {
      Product product1=productRepository.findById( id).get();
      product1.setProductName(product.getProductName());
      product1.setProductDescription(product.getProductDescription());
      product1.setPrice(product.getPrice());
      product1.setCreatedAt(product.getCreatedAt());
      product1.setUpdatedAt(product.getUpdatedAt());
      product1.setActive(product.isActive());
      product1.setDeleted(product.isDeleted());
      productRepository.save(product1);
    }



}
