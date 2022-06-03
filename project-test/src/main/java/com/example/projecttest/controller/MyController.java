package com.example.projecttest.controller;

import com.example.projecttest.model.unidirection.Category;
import com.example.projecttest.model.unidirection.Product;
import com.example.projecttest.repository.CategoryRepository;
import com.example.projecttest.repository.ProductRepository;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class MyController {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;
    @GetMapping("/test")
    public ResponseEntity<?> category(){
        Category cate = new Category("Công Nghệ");
        Product pd1 = new Product("Laptop");
        Product pd2 = new Product("Mobile");
        cate.addProduct(pd1);
        cate.addProduct(pd2);
        categoryRepository.save(cate);
        productRepository.save(pd1);
        productRepository.save(pd2);
        return ResponseEntity.ok("OKOK");
    }

    @GetMapping("/xoa")
    public ResponseEntity<?> deleteCategory(){
        categoryRepository.deleteById(1L);
        return ResponseEntity.ok("OKOK");
    }
}
