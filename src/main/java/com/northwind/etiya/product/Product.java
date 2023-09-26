package com.northwind.etiya.product;

import com.northwind.etiya.category.Category;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_id")
    private int id;


    @Column(name="product_name")
    private String name;

    @Column(name="unit_price")
    private double unitPrice;

    @ManyToOne()
    @JoinColumn(name="category_id")
    private Category category;
}
