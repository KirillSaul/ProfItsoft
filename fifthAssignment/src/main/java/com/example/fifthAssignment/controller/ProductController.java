package com.example.fifthAssignment.controller;

import com.example.fifthAssignment.protocol.product.ProductCreateDto;
import com.example.fifthAssignment.protocol.product.ProductFilter;
import com.example.fifthAssignment.protocol.product.ProductUpdateDto;
import com.example.fifthAssignment.protocol.product.ProductViewDto;
import com.example.fifthAssignment.service.product.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("/_filter")
    public Page<ProductViewDto> productFilter(@Valid @RequestBody ProductFilter productFilter) {
        return productService.findAllProductsViewDto(productFilter);
    }

    @GetMapping("/{id}")
    public ProductViewDto getProductById(@PathVariable Long id) {
        return productService.findProductViewDtoById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductViewDto createProduct(@Valid @RequestBody ProductCreateDto productCreateDto) {
        return productService.createProduct(productCreateDto);
    }

    @PutMapping
    public ProductViewDto updateProduct(@Valid @RequestBody ProductUpdateDto productUpdateDto) {
        return productService.updateProduct(productUpdateDto);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProductById(id);
    }
}
