package com.example.fifthAssignment.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Table(name = "categories")
@Data
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idCategory;
    private String name;

    public Category(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
