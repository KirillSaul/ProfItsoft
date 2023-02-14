package com.example.fifthAssignment.service.product;

import com.example.fifthAssignment.exception.NotFoundException;
import com.example.fifthAssignment.protocol.product.*;
import com.example.fifthAssignment.specification.product.ProductFilterSpecification;
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
    private final ProductViewMapper productViewMapper;
    private final ProductCreateMapper productCreateMapper;

    @Override
    @Transactional(readOnly = true)
    public Page<ProductViewDto> findAllByFilterProductsViewDto(ProductFilter productFilter) {
        return productRepository
                .findAll(new ProductFilterSpecification(productFilter), PageRequest.of(productFilter.getPage(), productFilter.getPageSize()))
                .map(productViewMapper::toProductViewDto);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductViewDto findProductViewDtoById(Long id) {
        return productViewMapper.toProductViewDto(findProductById(id));
    }

    @Override
    @Transactional
    public ProductViewDto createProduct(ProductCreateDto productCreateDto) {
        if (nonNull(productCreateDto.getCategoryId())) {
            Product product = productCreateMapper.toProduct(productCreateDto);
            product.setCategory(categoryService.findCategoryById(product.getCategory().getId()));
            return productViewMapper.toProductViewDto(productRepository.save(product));
        }
        else
        {
            throw new NotFoundException("category can`t be null");
        }
    }

    @Override
    @Transactional
    public ProductViewDto updateProduct(ProductUpdateDto productUpdateDto) {
        Product oldProduct = productRepository
                .findById(productUpdateDto.getId()).orElseThrow(() -> new NotFoundException("can`t find product with id = " + productUpdateDto.getId()));
        oldProduct.setName(productUpdateDto.getName());
        oldProduct.setCategory(categoryService.findCategoryById(productUpdateDto.getCategoryId()));
        return productViewMapper.toProductViewDto(oldProduct);
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    private Product findProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new NotFoundException("can`t find product with id = " + id));
    }
}
