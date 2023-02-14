package com.example.fifthAssignment.protocol.category;

import com.example.fifthAssignment.model.Category;
import org.mapstruct.Mapper;

@Mapper
public interface CategoryViewMapper {
    CategoryViewDto toCategoryViewDto(Category category);
}
