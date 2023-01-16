package com.example.fifthAssignment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Table(name = "categories")
@Getter
@Setter
@EqualsAndHashCode
@ToString(exclude = "products")
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Category(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "category",orphanRemoval = true)
    @JsonIgnore
    private List<Product> products;
}
