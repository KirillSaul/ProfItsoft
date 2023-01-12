package com.example.fifthAssignment.controller;

import com.example.fifthAssignment.model.Category;
import com.example.fifthAssignment.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/category")
@RequiredArgsConstructor
public class CategoryController {
    final private CategoryService categoryService;

    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.findAllCategories();
    }
}
