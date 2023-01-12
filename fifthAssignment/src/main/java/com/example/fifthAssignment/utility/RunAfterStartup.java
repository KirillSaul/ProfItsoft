package com.example.fifthAssignment.utility;

import com.example.fifthAssignment.model.Category;
import com.example.fifthAssignment.model.Product;
import com.example.fifthAssignment.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RunAfterStartup {
    final private ProductService productService;

    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() {
        for (int i = 0; i < 20; i++) {
            productService.createProduct(new Product("product" + i, new Category("Category" + i)));
        }
    }
}
