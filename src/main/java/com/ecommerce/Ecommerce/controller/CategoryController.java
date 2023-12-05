package com.ecommerce.Ecommerce.controller;

import com.ecommerce.Ecommerce.model.Category;
import com.ecommerce.Ecommerce.service.CategoryService;
import com.ecommerce.Ecommerce.view.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createCategroy(@RequestBody Category category){
        categoryService.createCategory(category);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true,"Category created"),HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public Category getCategory(@PathVariable Integer id){
        return  categoryService.getCategory(id);
    }

    @GetMapping
    public List<Category> getAllCategory(){
        return  categoryService.getAllCategory();
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateCategory(@RequestBody Category category,@PathVariable Integer id){

        try{
            categoryService.updateCategory(id,category);
        }catch (Exception e){
            return new ResponseEntity<>(new ApiResponse(false,e.getMessage()),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ApiResponse(true,"Category updated."),HttpStatus.OK);
    }
}
