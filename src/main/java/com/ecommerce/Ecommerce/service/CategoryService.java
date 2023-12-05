package com.ecommerce.Ecommerce.service;

import com.ecommerce.Ecommerce.model.Category;
import com.ecommerce.Ecommerce.repository.CategoryRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public void createCategory(Category category){
        categoryRepository.save(category);
    }

    public List<Category> getAllCategory(){
        return categoryRepository.findAll();
    }

    public Category getCategory(Integer id){
        return  categoryRepository.findById(id).orElseThrow(()-> new RuntimeException("Category not found"));
    }

    public void updateCategory(Integer id, Category category) {
        Category updatedCategory = getCategory(id);
        updatedCategory.setCategoryName(category.getCategoryName());
        updatedCategory.setImageUrl(category.getImageUrl());
        categoryRepository.save(updatedCategory);
    }
}
