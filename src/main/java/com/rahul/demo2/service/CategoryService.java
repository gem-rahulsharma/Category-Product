package com.rahul.demo2.service;

import com.rahul.demo2.controller.CategoryController;
import com.rahul.demo2.entity.Category;
import com.rahul.demo2.repository.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

    public String saveCategoryData(Category category) {
        Category category1=null;
        try {
            category1 = categoryRepository.save(category);
        }catch (Exception e){
            logger.error("error in addCourse() method " +e.getMessage());
        }
        return "data saved successfully";
    }

    public List<Category> findAllCategoryData() {
        List<Category> category=new ArrayList<>();
        try{
            category=categoryRepository.findAll();
        } catch (Exception e) {
            logger.error("error in getAllCategory() method" + e.getMessage());
        }
        return category;
    }

    public Category categoryById(int Id) {
        Category category=null;
        try{
            category = categoryRepository.findById(Id).get();
        }catch (Exception e){
            logger.error("error in categoryByid() method" + e.getMessage());
        }
        return category;
    }

    public String deleteCategory(int Id) {
        try {
            categoryRepository.findById(Id);
            return "Success";
        }catch(Exception e){
            logger.error("Error in deleteCategory() method -" +e.getMessage());
            return "Failure";
        }
        }

    public Category updateCategory(Category category, int Id) {
        Category category1=null;
        try {
             category1 = categoryRepository.findById(Id).get();
            category1.setCategoryName(category.getCategoryName());
            category1.setDescription(category.getDescription());
            category1.setCreateDate(category.getCreateDate());
            category1.setUpDate(category.getUpDate());
            category1.setIsActive(category.getIsActive());
            category1.setIsDeleted(category.getIsDeleted());
        } catch (Exception e) {
            logger.error("Error in UpdateCategory() method"+e.getMessage());
    }
        return categoryRepository.save(category1);
    }
}
