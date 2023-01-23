package com.example.fifthAssignment.protocol.product;

import com.example.fifthAssignment.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ProductCreateMapper {
    @Mapping(target = "category.id", source = "categoryId")
    Product toProduct(ProductCreateDto productCreateDto);
}
