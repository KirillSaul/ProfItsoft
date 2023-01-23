package com.example.fifthAssignment.protocol.product;

import com.example.fifthAssignment.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ProductViewMapper {
    @Mapping(target = "categoryId", source = "category.id")
    ProductViewDto toProductViewDto(Product product);
}
