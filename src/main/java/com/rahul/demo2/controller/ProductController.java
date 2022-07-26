package com.rahul.demo2.controller;
import com.rahul.demo2.entity.Product;
import com.rahul.demo2.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import javax.persistence.Id;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;
    @PostMapping("/save")
    public String saveProduct(@RequestBody Product product)
    {
        productService.saveData(product);
        return "Data saved Successfully";
    }
    @GetMapping("/view")
    public List<Product> showProduct(Product product)
    {
        return (List<Product>) productService.findAllData();
    }
    @GetMapping("/{id}")
    public Product showById(@PathVariable int id)
    {
        return productService.productById(id);
    }
    @DeleteMapping("/{id}")
    public String removeProduct(@PathVariable int id)
    {
         productService.deleteProduct(id);
        return "Data deleted successfully";
    }
    @PutMapping("/{id}")
    public String productUpdate(@RequestBody Product product,@PathVariable int id)
    {
        productService.updateProduct(product ,id);
        return "data updated successfully";
    }


}
