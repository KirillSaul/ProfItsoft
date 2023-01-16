package com.example.fifthAssignment.service.product;

import com.example.fifthAssignment.criteria.product.ProductFilter;
import com.example.fifthAssignment.model.Product;
import org.springframework.data.domain.Page;

public interface ProductService {
    Page<Product> findAllProducts(ProductFilter productFilter);

    Product findProductById(Long id);

    Product createProduct(Product product);

    Product updateProduct(Product newProduct);

    void deleteProductById(Long id);
}
