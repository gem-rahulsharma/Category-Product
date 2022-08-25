package com.rahul.demo2.controller;

import com.rahul.demo2.entity.Category;
import com.rahul.demo2.entity.Product;
import com.rahul.demo2.service.CategoryService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);
    @ApiOperation(value = "Saving category using saveCategory() method",response = Category.class)
    @PostMapping("/save")
    public ResponseEntity<String> saveCategory(@RequestBody Category category) {
        logger.info("Calling and starting save Category");
        String category1=categoryService.saveCategoryData(category);
        if(category1 == null){
            logger.error("unable to fetch Category");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return  new ResponseEntity<>("data saved successfully",HttpStatus.OK);

    }
    @ApiOperation(value = "Showing all categories using showCategory() method",response = Category.class)
    @GetMapping("/show")
    public ResponseEntity<List<Category>> showCategory() {
        logger.info("Calling getAllCategories");
        List<Category> list= categoryService.findAllCategoryData();
        if (list == null ){
            logger.error("unable to fetch courses");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list,HttpStatus.OK);
    }
    @ApiOperation(value = "Showing only one category using saveCategoryById() method",response = Category.class)
    @GetMapping("/{id}")
    public ResponseEntity<Category> showCategoryById(@PathVariable int id) {
        logger.info("calling and starting showCategoryById");
        Category category = categoryService.categoryById(id);
        if (category == null) {
            logger.error("Unable to access category");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return new ResponseEntity<>(category,HttpStatus.OK);
    }
    @ApiOperation(value = "Deleting category using deleteCategoryById() method",response = Category.class)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategoryById(@PathVariable int id) {
        logger.info("Calling and Starting deleteCategoryById");
        String response=categoryService.deleteCategory(id);
        if(response == "Success"){
            return new ResponseEntity<>("Category deleted Successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @ApiOperation(value = "Updating category using updateCategory() method",response = Category.class)
    @PutMapping("/{id}")
    public ResponseEntity<Category> productUpdate(@RequestBody Category category, @PathVariable int id) {
        logger.info("Calling put() method");
        Category category1=categoryService.updateCategory(category,id);
        if(category1 == null){
            logger.error("unable to update");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(category1,HttpStatus.OK);
        }

    }



