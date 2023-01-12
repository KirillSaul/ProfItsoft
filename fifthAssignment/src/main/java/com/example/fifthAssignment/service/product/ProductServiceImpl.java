package com.example.fifthAssignment.service.product;

import com.example.fifthAssignment.model.Product;
import com.example.fifthAssignment.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    final private ProductRepository productRepository;

    @Override
    public List<Product> findAllProducts(Pageable pageable) {
        return null;
    }

    @Override
    public Product findProductByName(String id, Pageable pageable) {
        return null;
    }

    @Override
    public Product findProductByCategoryId(Long id, Pageable pageable) {
        return null;
    }

    @Override
    public Product findProductById(Long id) {
        return null;
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        return null;
    }

    @Override
    public Product deleteProductById(Long id) {
        return null;
    }
}
