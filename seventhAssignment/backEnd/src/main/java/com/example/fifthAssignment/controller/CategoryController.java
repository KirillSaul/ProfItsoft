package com.example.fifthAssignment.controller;

import com.example.fifthAssignment.protocol.category.CategoryViewDto;
import com.example.fifthAssignment.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
@CrossOrigin("http://localhost:3000")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public List<CategoryViewDto> getAllCategories() {
        return categoryService.findAllCategoriesViewDto();
    }
}
