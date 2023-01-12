package com.example.fifthAssignment.service.product;

import com.example.fifthAssignment.model.Product;

import java.awt.print.Pageable;
import java.util.List;

public interface ProductService {
    List<Product> findAllProducts(Pageable pageable);

    Product findProductByName(String id, Pageable pageable);

    Product findProductByCategoryId(Long id, Pageable pageable);

    Product findProductById(Long id);

    Product createProduct(Product product);

    Product updateProduct(Product product);

    Product deleteProductById(Long id);
}
