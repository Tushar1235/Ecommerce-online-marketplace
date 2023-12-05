package com.ecommerce.Ecommerce.service;

import com.ecommerce.Ecommerce.Dto.ProductDto;
import com.ecommerce.Ecommerce.model.Category;
import com.ecommerce.Ecommerce.model.Product;
import com.ecommerce.Ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryService categoryService;
    public void createProduct(ProductDto productDto, Category category) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setImageUrl(productDto.getImageUrl());
        product.setPrice(productDto.getPrice());
        product.setCategory(category);
        productRepository.save(product);
    }

    public ProductDto getProductDto(Product product){
        ProductDto productDto = new ProductDto(product);
        return productDto;
    }

    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductDto> productDtos = products.stream().map(product -> getProductDto(product)).collect(Collectors.toList());
       /* for(Product product : products){
            productDtos.add(getProductDto(product));
        }*/
        return productDtos;
    }

    public void updateProduct(Integer id, ProductDto productDto){
        Category category = categoryService.getCategory(productDto.getCategoryId());
        Product product = productRepository.findById(id).orElseThrow(()-> new RuntimeException("Product not found"));
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setImageUrl(productDto.getImageUrl());
        product.setPrice(productDto.getPrice());
        product.setCategory(category);
        productRepository.save(product);
    }
}
