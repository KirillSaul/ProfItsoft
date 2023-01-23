package com.example.fifthAssignment.utility;

import com.example.fifthAssignment.model.Category;
import com.example.fifthAssignment.protocol.product.ProductCreateDto;
import com.example.fifthAssignment.service.category.CategoryService;
import com.example.fifthAssignment.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RunAfterStartup {
    private final ProductService productService;
    private final CategoryService categoryService;

    @EventListener(ApplicationReadyEvent.class)
    public void fillDataBase() {
        Category category;
        for (int i = 0; i < 20; i++) {
            category = categoryService.createCategory(new Category("Category" + i));
            productService.createProduct(new ProductCreateDto("Product" + i, category.getId()));
        }
    }
}
