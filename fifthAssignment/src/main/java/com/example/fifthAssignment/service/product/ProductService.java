package com.example.fifthAssignment.service.product;

import com.example.fifthAssignment.protocol.product.ProductCreateDto;
import com.example.fifthAssignment.protocol.product.ProductFilter;
import com.example.fifthAssignment.protocol.product.ProductUpdateDto;
import com.example.fifthAssignment.protocol.product.ProductViewDto;
import org.springframework.data.domain.Page;

public interface ProductService {
    Page<ProductViewDto> findAllByFilterProductsViewDto(ProductFilter productFilter);

    ProductViewDto findProductViewDtoById(Long id);

    ProductViewDto createProduct(ProductCreateDto productCreateDto);

    ProductViewDto updateProduct(ProductUpdateDto productUpdateDto);

    void deleteProductById(Long id);
}
