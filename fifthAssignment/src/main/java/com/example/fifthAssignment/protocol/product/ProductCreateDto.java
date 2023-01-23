package com.example.fifthAssignment.protocol.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ProductCreateDto {
    @NotBlank(message = "name is required")
    private String name;
    @NotNull(message = "category is required")
    private Long categoryId;
}
