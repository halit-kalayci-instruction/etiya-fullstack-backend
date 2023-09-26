package com.northwind.etiya.product;

import com.northwind.etiya.category.Category;
import com.northwind.etiya.supplier.Supplier;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="products")
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_id")
    private int id;


    @Column(name="product_name")
    private String name;

    @Column(name="unit_price")
    private double unitPrice;

    @Column(name="units_in_stock")
    private int unitsInStock;

    @Column(name="discontinued")
    private int discontinued;

    @ManyToOne()
    @JoinColumn(name="category_id")
    private Category category;

    @ManyToOne()
    @JoinColumn(name="supplier_id")
    private Supplier supplier;
}
