package com.rahul.demo2.controller;

import com.rahul.demo2.entity.Category;
import com.rahul.demo2.entity.Product;
import com.rahul.demo2.repository.CategoryRepository;
import com.rahul.demo2.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @PostMapping("/save")
    public String saveCategory(@RequestBody Category category)
    {
        categoryService.saveCategoryData(category);
        return "Data saved successfully";
    }
    @GetMapping("/show")
    public List<Category> showCategory()
    {
        return (List<Category>) categoryService.findAllCategoryData();
    }
    @GetMapping("/{id}")
    public Category showCategoryById(@PathVariable int id)
    {
        return categoryService.categoryById( id);
    }
    @DeleteMapping("/{id}")
    public  String deleteCategoryById(@PathVariable int id)
    {
        categoryService.deleteCategory(id);
        return "data deleted successfully";
    }
    @PutMapping("/{id}")
    public String productUpdate(@RequestBody Category category,@PathVariable int id)
    {
        categoryService.updateCategory(category ,id);
        return "data updated successfully";
    }


}
