package com.example.fifthAssignment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;


@Entity
@Table(name = "categories")
@Data
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "name is required")
    private String name;

    public Category(String name) {
        this.name = name;
    }

    @ToString.Exclude
    @OneToMany(mappedBy = "category", orphanRemoval = true)
    @JsonIgnore
    private List<Product> products;
}
