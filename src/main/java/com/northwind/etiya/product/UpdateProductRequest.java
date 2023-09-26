package com.northwind.etiya.product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class UpdateProductRequest {
    private int id;
    private String productName;
    private int supplierId;
    private int categoryId;
}
