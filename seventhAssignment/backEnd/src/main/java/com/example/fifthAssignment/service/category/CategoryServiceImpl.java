package com.example.fifthAssignment.service.category;

import com.example.fifthAssignment.exception.NotFoundException;
import com.example.fifthAssignment.model.Category;
import com.example.fifthAssignment.protocol.category.CategoryViewDto;
import com.example.fifthAssignment.protocol.category.CategoryViewMapper;
import com.example.fifthAssignment.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryViewMapper categoryViewMapper;

    @Override
    public List<CategoryViewDto> findAllCategoriesViewDto() {
        return findAllCategories().stream().map(categoryViewMapper::toCategoryViewDto).collect(Collectors.toList());
    }

    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category findCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new NotFoundException("can`t find category with id = " + id));
    }

    private List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }
}
