package com.example.fifthAssignment.controller;

import com.example.fifthAssignment.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController("/product")
@RequiredArgsConstructor
public class ProductController {
    final private ProductService productService;

}
