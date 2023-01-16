package com.example.fifthAssignment.criteria.product;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ProductFilter {
    private String productName;
    private Set<Long> categoryIds;

    @NotNull(message = "page number is required")
    @Min(value = 0,message = "0 is minimum page number")
    private Integer page;

    @NotNull(message = "page size is required")
    @Min(value = 10,message = "10 is minimum page size")
    private Integer pageSize;
}
