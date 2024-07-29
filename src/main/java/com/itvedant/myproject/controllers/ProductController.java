package com.itvedant.myproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.itvedant.myproject.services.ProductService;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @PutMapping("/products/{id}/upload")
    public ResponseEntity<?> upload(@PathVariable Integer id,@RequestParam("file")MultipartFile file)
        {
            return ResponseEntity.ok(productService.storeFile(id,file));
        }
    
}
