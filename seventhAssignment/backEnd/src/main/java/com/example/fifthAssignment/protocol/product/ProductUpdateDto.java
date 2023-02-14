package com.example.fifthAssignment.protocol.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ProductUpdateDto {
    @NotNull(message = "id is required")
    private Long id;
    @NotBlank(message = "name is required")
    private String name;
    @NotNull(message = "category is required")
    private Long categoryId;
}
