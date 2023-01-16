package com.example.fifthAssignment.criteria.product;


import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

import static java.util.Objects.nonNull;

@NoArgsConstructor
@Getter
public class ProductFilter {
    private String productName;
    private Set<Long> categoriesId;
    private Integer page = 0;
    private Integer pageSize = 10;

    public ProductFilter(String productName, Set<Long> categoriesId, Integer page, Integer pageSize) {
        this.productName = productName;
        this.categoriesId = categoriesId;
        if (nonNull(page)) {
            this.page = page;
        } else {
            this.page = 0;
        }
        if (nonNull(pageSize)) {
            this.pageSize = pageSize;
        } else {
            this.pageSize = 10;
        }
    }
}
