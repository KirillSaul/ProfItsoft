package com.example.fifthAssignment.service.product;

import com.example.fifthAssignment.criteria.product.ProductFilter;
import com.example.fifthAssignment.criteria.product.ProductFilterSpecification;
import com.example.fifthAssignment.model.Product;
import com.example.fifthAssignment.repository.CategoryRepository;
import com.example.fifthAssignment.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Page<Product> findAllProducts(ProductFilter productFilter) {
        return productRepository.findAll(new ProductFilterSpecification(productFilter), PageRequest.of(productFilter.getPage(), productFilter.getPageSize()));
    }

    @Override
    public Product findProductById(Long id) {
        return productRepository.findById(id).orElseThrow();
    }

    @Override
    @Transactional
    public Product createProduct(Product product) {
        if (nonNull(product.getCategory().getId())) {
            product.setCategory(categoryRepository.findById(product.getCategory().getId()).orElseThrow());
        }
        return productRepository.save(product);
    }

    @Override
    @Transactional
    public Product updateProduct(Product newProduct) {
        Product oldProduct = productRepository.findById(newProduct.getId()).orElseThrow();
        oldProduct.setName(newProduct.getName());
        oldProduct.setCategory(newProduct.getCategory());
        return oldProduct;
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }
}
