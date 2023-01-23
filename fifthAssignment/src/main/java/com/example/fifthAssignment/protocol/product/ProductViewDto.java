package com.example.fifthAssignment.protocol.product;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ProductViewDto {
    private Long id;
    private String name;
    private Long categoryId;
}
