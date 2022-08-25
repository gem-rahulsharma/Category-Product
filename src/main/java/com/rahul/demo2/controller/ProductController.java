package com.rahul.demo2.controller;

import com.rahul.demo2.entity.Product;
import com.rahul.demo2.service.ProductService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/product")
public class    ProductController {
    @Autowired
    ProductService productService;
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @ApiOperation(value = "Saving product using saveProduct() method",response = Product.class)
    @PostMapping("/save")
    public ResponseEntity<String> saveProduct(@RequestBody Product product) {
        logger.info("Calling saveProduct() method");
        String product1=productService.saveData(product);
        if (product1 == null) {
            logger.error("Unable to fetch product");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Product saved successfully",HttpStatus.OK);
    }
    @ApiOperation(value = "Showing all products using showProduct() method",response = Product.class)
    @GetMapping("/view")
   public ResponseEntity<List<Product>> showProduct() {
        logger.info("Calling showAllProduct() method");
        List<Product> list=productService.findAllData();
        if (list == null){
            logger.error("Unable to fetch");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list,HttpStatus.OK);
    }
    @ApiOperation(value = "Showing only one product using showById() method",response = Product.class)
    @GetMapping("/{id}")
    public ResponseEntity<Product> showById(@PathVariable int id) {
        logger.info("Calling showById() method");
        Product product=productService.productById(id);
        if(product==null){
            logger.error("Unable to fetch");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(product,HttpStatus.OK);
    }
    @ApiOperation(value = "Deleting product using removeProduct() method",response = Product.class)
    @DeleteMapping("/{id}")
    public ResponseEntity <String> removeProduct( @PathVariable int id) {
        logger.info("Calling removeProduct() method");
        String message=productService.deleteProduct(id);
        if (message != "Success"){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Product deleted successfully",HttpStatus.OK);
    }
    @ApiOperation(value = "Updating existing product using updateProduct() method",response = Product.class)
    @PutMapping("/{id}")
    public ResponseEntity<Product> productUpdate(@RequestBody Product product, @PathVariable int id) {
        logger.info("Calling productUpdate() method");
        try{
            this.productService.updateProduct(product,id);
            return ResponseEntity.ok().body(product);
        } catch (Exception e) {
            logger.error("error in productUpdate() method"+e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        }


}
