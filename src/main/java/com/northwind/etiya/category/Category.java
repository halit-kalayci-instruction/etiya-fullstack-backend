package com.northwind.etiya.category;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.northwind.etiya.product.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data // getter+setter
@Table(name="categories")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @Column(name="category_id")
    private int categoryId;

    @Column(name="category_name")
    private String categoryName;

    @Column(name="description")
    private String description;

    @OneToMany(mappedBy = "category")
    @JsonIgnore()
    private List<Product> products;
}