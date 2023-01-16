package com.example.fifthAssignment.service.category;

import com.example.fifthAssignment.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAllCategories();

    Category createCategory(Category category);

    Category findCategoryById(Long id);
}
