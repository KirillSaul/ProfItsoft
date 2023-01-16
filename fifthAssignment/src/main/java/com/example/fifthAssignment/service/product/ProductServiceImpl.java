package com.example.fifthAssignment.service.product;

import com.example.fifthAssignment.exception.NotFoundException;
import com.example.fifthAssignment.criteria.product.ProductFilter;
import com.example.fifthAssignment.criteria.product.ProductFilterSpecification;
import com.example.fifthAssignment.model.Product;
import com.example.fifthAssignment.repository.ProductRepository;
import com.example.fifthAssignment.service.category.CategoryService;
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
    private final CategoryService categoryService;

    @Override
    public Page<Product> findAllProducts(ProductFilter productFilter) {
        return productRepository.findAll(new ProductFilterSpecification(productFilter), PageRequest.of(productFilter.getPage(), productFilter.getPageSize()));
    }

    @Override
    public Product findProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new NotFoundException("can`t find product with id = " + id));
    }

    @Override
    @Transactional
    public Product createProduct(Product product) {
        if (nonNull(product.getCategory().getId())) {
            product.setCategory(categoryService.findCategoryById(product.getCategory().getId()));
        }
        return productRepository.save(product);
    }

    @Override
    @Transactional
    public Product updateProduct(Product newProduct) {
        Product oldProduct = findProductById(newProduct.getId());
        oldProduct.setName(newProduct.getName());
        oldProduct.setCategory(categoryService.findCategoryById(newProduct.getCategory().getId()));
        return oldProduct;
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }
}
