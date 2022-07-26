package com.rahul.demo2.service;

import com.rahul.demo2.entity.Category;
import com.rahul.demo2.entity.Product;
import com.rahul.demo2.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    
    public String saveCategoryData(Category category)
    {
        categoryRepository.save(category);
        return  "data saved successfully";
    }
    public List<Category> findAllCategoryData()
    {
        return (List<Category>) categoryRepository.findAll();
    }
    public Category categoryById(int Id)
    {
        Optional<Category> category=categoryRepository.findById(Id);
        return  category.get();
    }
    public List<Category> deleteCategory(int Id)
    {
        categoryRepository.findById(Id);
        return categoryRepository.findAll();
    }
    public void updateCategory(Category category,int Id)
    {
        Category category1=categoryRepository.findById( Id).get();
        category1.setCategoryName(category.getCategoryName());
        category1.setDescription(category.getDescription());
        category1.setCreateDate(category.getCreateDate());
        category1.setUpDate(category.getUpDate());
        category1.setActive(category.getActive());
        category1.setDeleted(category.getDeleted());
        categoryRepository.save(category1);
    }
}
