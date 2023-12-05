package com.ecommerce.Ecommerce.controller;

import com.ecommerce.Ecommerce.Dto.ProductDto;
import com.ecommerce.Ecommerce.model.Category;
import com.ecommerce.Ecommerce.repository.ProductRepository;
import com.ecommerce.Ecommerce.service.CategoryService;
import com.ecommerce.Ecommerce.service.ProductService;
import com.ecommerce.Ecommerce.view.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;
    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createProduct(@RequestBody ProductDto productDto){
        try{
            Category category = categoryService.getCategory(productDto.getCategoryId());
            productService.createProduct(productDto,category);
        } catch (Exception e){
            return  new ResponseEntity<>(new ApiResponse(false,e.getMessage()), HttpStatus.NOT_FOUND);
        }
        return  new ResponseEntity<>(new ApiResponse(true,"Product created"), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        List<ProductDto> productDtos = productService.getAllProducts();
        return new ResponseEntity<>(productDtos,HttpStatus.OK);
    }
    @PostMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateProduct(@RequestBody ProductDto productDto,@PathVariable Integer id){
        try{
            productService.updateProduct(id,productDto);
        } catch (Exception e){
            return  new ResponseEntity<>(new ApiResponse(false,e.getMessage()), HttpStatus.NOT_FOUND);
        }
        return  new ResponseEntity<>(new ApiResponse(true,"Product updated"), HttpStatus.OK);
    }

}
