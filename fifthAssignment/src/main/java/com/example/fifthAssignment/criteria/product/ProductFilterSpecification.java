package com.example.fifthAssignment.criteria.product;

import com.example.fifthAssignment.model.Product;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

@RequiredArgsConstructor
public class ProductFilterSpecification implements Specification<Product> {
    private final ProductFilter productFilter;

    @Override
    public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicateList = new ArrayList<>();
        if (nonNull(productFilter.getProductName())) {
            predicateList.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("name")), "%"+ productFilter.getProductName().toUpperCase()+"%"));
        }
        if (nonNull(productFilter.getCategoryIds()) && !productFilter.getCategoryIds().isEmpty()) {
            predicateList.add(criteriaBuilder.in(root.get("category").get("id")).value(productFilter.getCategoryIds()));
        }
        return criteriaBuilder.and(predicateList.toArray(new Predicate[0]));
    }
}
