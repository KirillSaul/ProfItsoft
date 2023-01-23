package com.example.fifthAssignment.service.category;

import com.example.fifthAssignment.model.Category;
import com.example.fifthAssignment.protocol.category.CategoryViewDto;

import java.util.List;

public interface CategoryService {
    List<CategoryViewDto> findAllCategoriesViewDto();

    Category createCategory(Category category);

    Category findCategoryById(Long id);
}
